package lucyd.api.domain.policy;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucyd.api.domain.ifstatement.ComparisonOperator;
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
	
	public DecisionResponsePayload executePolicy(Policy policy, String req) {
		JSONObject jsonObject = new JSONObject(req);
		DecisionResponsePayload decision = null;
		
		if (policy.getIfStatements().isEmpty()) {
			throw new RuntimeException("Add at least one if-statement first");
		}
		
		for (IfStatement ifStatement : policy.getIfStatements()) {
			Long value = Long.parseLong(ifStatement.getValue());
			Long variable = jsonObject.getLong(ifStatement.getVariable());
			
			if (ifStatement.getElseBlock() == null || ifStatement.getThenBlock() == null) {
				throw new RuntimeException("Add the end blocks");
			}
			
			Boolean elseBlock = ifStatement.getElseBlock().equals("false") ? false : true;
			Boolean thenBlock = ifStatement.getThenBlock().equals("false") ? false : true;
			
			ComparisonOperator comparisonOperator = ifStatement.getComparisonOperator();
			
			Boolean expression = null;
			
			switch (comparisonOperator) {
				case EQ:
					expression = variable.equals(value);
					break;
				case LT:
					expression = variable < value;
					break;
				case LTE:
					expression = variable <= value;
					break;
				case GT:
					expression = variable > value;
					break;
				case GTE:
					expression = variable >= value;
					break;
			}
			
			if (expression) {
				decision = new DecisionResponsePayload(thenBlock);
				
				if (!thenBlock) {
					break;
				}
			} else {				
				decision = new DecisionResponsePayload(elseBlock);
				
				if (!elseBlock) {
					break;
				}
			}
			
		}

		return decision;
	}
}
