package lucyd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lucyd.api.ifstatement.IfStatement;
import lucyd.api.ifstatement.IfStatementRequestPayload;
import lucyd.api.policy.Policy;
import lucyd.api.policy.PolicyDecisionResponsePayload;
import lucyd.api.policy.PolicyListResponsePayload;
import lucyd.api.policy.PolicyRepository;

@RestController
@RequestMapping("policies")
public class PolicyController {

	@Autowired
	private PolicyRepository policyRepository;
	
	@PostMapping
	@Transactional
	public void register() {
		policyRepository.save(new Policy());
	}
	
	@GetMapping
	public Page<PolicyListResponsePayload> getPolicies(@PageableDefault(size = 10) Pageable pagination) {
		return policyRepository.findAll(pagination).map(PolicyListResponsePayload::new);
	}
	
	@PostMapping("/{id}/if_statements")
	@Transactional
	public void addIfStatement(@PathVariable Long id, @RequestBody @Valid IfStatementRequestPayload req) {
		var policy = policyRepository.getReferenceById(id);
		policy.addIfStatement(new IfStatement(req));
	}
	
	@PostMapping("/{id}/decision")
	@Transactional
	public PolicyDecisionResponsePayload executeDecision(@PathVariable Long id, @RequestBody String req) {
		var policy = policyRepository.getReferenceById(id);
		return policy.executeDecision(req);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		policyRepository.deleteById(id);
	}
}
