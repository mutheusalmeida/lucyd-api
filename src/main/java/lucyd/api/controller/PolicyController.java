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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lucyd.api.domain.ifstatement.IfStatementRequestPayload;
import lucyd.api.domain.ifstatement.IfStatementResponsePayload;
import lucyd.api.domain.policy.Policy;
import lucyd.api.domain.policy.DecisionResponsePayload;
import lucyd.api.domain.policy.PolicyResponsePayload;
import lucyd.api.domain.policy.PolicyService;
import lucyd.api.domain.policy.PolicyRepository;
import lucyd.api.domain.policy.PolicyRequestPayload;

@RestController
@RequestMapping("policies")
public class PolicyController {

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private PolicyService policyService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PolicyResponsePayload> register(@RequestBody @Valid PolicyRequestPayload req, UriComponentsBuilder uriBuilder) {
		var policy = new Policy(req);
		policyRepository.save(policy);
		
		var uri = uriBuilder.path("/policies/{id}").buildAndExpand(policy.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PolicyResponsePayload(policy));
	}
	
	@GetMapping
	public ResponseEntity<Page<PolicyResponsePayload>> getPolicies(@PageableDefault(size = 10) Pageable pagination) {
		var policies = policyRepository.findAll(pagination).map(PolicyResponsePayload::new);
		
		return ResponseEntity.ok(policies);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PolicyResponsePayload> getPolicy(@PathVariable Long id) {
		var policy = policyRepository.getReferenceById(id);
		
		return ResponseEntity.ok(new PolicyResponsePayload(policy));
	}
	
	@PostMapping("/{id}/if_statements")
	@Transactional
	public ResponseEntity<IfStatementResponsePayload> addIfStatement(@PathVariable Long id, @RequestBody @Valid IfStatementRequestPayload req, UriComponentsBuilder uriBuilder) {
		var ifStatement = policyService.addIfStatement(id, req);
		
		var uri = uriBuilder.path("/if_statements/{id}").buildAndExpand(ifStatement.id()).toUri();
		
		return ResponseEntity.created(uri).body(ifStatement);
	}
	
	@PostMapping("/{id}/decision")
	@Transactional
	public ResponseEntity<DecisionResponsePayload> executeDecision(@PathVariable Long id, @RequestBody String req) {
		var policy = policyRepository.getReferenceById(id);
		var decision = policyService.executePolicy(policy, req);
		
		return ResponseEntity.ok(decision);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PolicyResponsePayload> update(@PathVariable Long id, @RequestBody PolicyRequestPayload req) {
		var policy = policyRepository.getReferenceById(id);
		policy.update(req);
		
		return ResponseEntity.ok(new PolicyResponsePayload(policy));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		policyRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
