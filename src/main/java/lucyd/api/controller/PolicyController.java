package lucyd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lucyd.api.policy.PolicyRepository;

@RestController
@RequestMapping("policies")
public class PolicyController {

	@Autowired
	private PolicyRepository policyRepository;
	
	@PostMapping
	@Transactional
	public void register() {
		System.out.println("Hello");
	}
}
