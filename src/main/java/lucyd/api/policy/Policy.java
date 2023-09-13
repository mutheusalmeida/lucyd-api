package lucyd.api.policy;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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

	public PolicyDecisionResponsePayload executeDecision(@Valid String req) {
		Boolean decision = false;

		System.out.println(req);

		return new PolicyDecisionResponsePayload(decision);
	}
}
