package lucyd.api.domain.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucyd.api.domain.ifstatement.IfStatement;
import lucyd.api.domain.ifstatement.IfStatementRepository;
import lucyd.api.domain.ifstatement.IfStatementRequestPayload;
import lucyd.api.domain.ifstatement.IfStatementResponsePayload;

@Service
public class PolicyService {

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	IfStatementRepository ifStatementRepository;
	
	public IfStatementResponsePayload addIfStatement(Long id, IfStatementRequestPayload req) {
		var policy = policyRepository.getReferenceById(id);
		
		if (!policyRepository.existsById(id)) {
			throw new RuntimeException("Policy does not exist");
		}
		
		var ifStatement = new IfStatement(req, policy);
		ifStatementRepository.save(ifStatement);
		
		return new IfStatementResponsePayload(ifStatement);	
	}
}
