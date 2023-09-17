package lucyd.api.domain.policy;

import java.util.ArrayList;
import java.util.List;

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
import lucyd.api.domain.ifstatement.IfStatement;

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
	
	private String name;

	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
	private List<IfStatement> ifStatements = new ArrayList<IfStatement>();
	
	public Policy(PolicyRequestPayload req) {
		this.name = req.name();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void update(PolicyRequestPayload req) {
		this.name = req.name();
	}
}
