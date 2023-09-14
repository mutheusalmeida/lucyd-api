package lucyd.api.policy;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lucyd.api.ifstatement.IfStatement;

@Table(name = "policies")
@Entity(name = "Policy")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
	private List<IfStatement> ifStatements = new ArrayList<IfStatement>();

	public void setId(Long id) {
		this.id = id;
	}

	public void addIfStatement(IfStatement ifStatement) {
		ifStatement.addPolicy(this);
		this.ifStatements.add(ifStatement);
	}

	public PolicyDecisionResponsePayload executeDecision(String req) {
		JSONObject jsonObject = new JSONObject(req);
		PolicyDecisionResponsePayload decision = null;
		
		for (IfStatement ifStatement : ifStatements) {
			Long value = Long.parseLong(ifStatement.getValue());
			Long variable = jsonObject.getLong(ifStatement.getVariable());
			Boolean elseBlock = ifStatement.getElseBlock();
			Boolean thenBlock = ifStatement.getThenBlock();
			String comparisonOperator = ifStatement.getComparisonOperator();
			
			Boolean expression = null;
			
			switch (comparisonOperator) {
				case ">":
					expression = variable > value;
					break;
				case "=":
					expression = variable.equals(value);
					break;
				case "<":
					expression = variable < value;
					break;
				case "<=":
					expression = variable <= value;
					break;
				case ">=":
					expression = variable >= value;
					break;
			}
			
			if (expression) {
				decision = new PolicyDecisionResponsePayload(thenBlock);
				
				if (!thenBlock) {
					break;
				}
			} else {				
				decision = new PolicyDecisionResponsePayload(elseBlock);
				
				if (!elseBlock) {
					break;
				}
			}
			
		}

		return decision;
	}
}
