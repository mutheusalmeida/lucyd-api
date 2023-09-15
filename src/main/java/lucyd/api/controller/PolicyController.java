package lucyd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lucyd.api.ifstatement.IfStatement;
import lucyd.api.ifstatement.IfStatementPostRequestPayload;
import lucyd.api.ifstatement.IfStatementPostResponsePayload;
import lucyd.api.policy.Policy;
import lucyd.api.policy.PolicyDecisionPostResponsePayload;
import lucyd.api.policy.PolicyListGetResponsePayload;
import lucyd.api.policy.PolicyRepository;

@RestController
@RequestMapping("policies")
public class PolicyController {

	@Autowired
	private PolicyRepository policyRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> register() {
		policyRepository.save(new Policy());
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<PolicyListGetResponsePayload>> getPolicies(@PageableDefault(size = 10) Pageable pagination) {
		var<?> policies = policyRepository.findAll(pagination).map(PolicyListGetResponsePayload::new);
		
		return ResponseEntity.ok(policies);
	}
	
	@PostMapping("/{id}/if_statements")
	@Transactional
	public ResponseEntity<IfStatementPostResponsePayload> addIfStatement(@PathVariable Long id, @RequestBody @Valid IfStatementPostRequestPayload req, UriComponentsBuilder uriBuilder) {
		var policy = policyRepository.getReferenceById(id);
		var ifStatement = new IfStatement(req);
		policy.addIfStatement(ifStatement);
		
		System.out.println(ifStatement.getId());
		
		var uri = uriBuilder.path("/policies/{id}/if_statements").buildAndExpand(id).toUri();
		
		return ResponseEntity.created(uri).body(new IfStatementPostResponsePayload(ifStatement));
	}
	
	@PostMapping("/{id}/decision")
	@Transactional
	public ResponseEntity<PolicyDecisionPostResponsePayload> executeDecision(@PathVariable Long id, @RequestBody String req) {
		var policy = policyRepository.getReferenceById(id);
		
		return ResponseEntity.ok(policy.executeDecision(req));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		policyRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
