package lucyd.api.ifstatement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lucyd.api.policy.Policy;

@Table(name = "if_statements")
@Entity(name = "IfStatement")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class IfStatement {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String variable;
	private String value;
	
	@Enumerated(EnumType.STRING)
	@Column(name =  "comparison_operator")
	private ComparisonOperator comparisonOperator;
	
	@Column(name =  "else_block")
	private Boolean elseBlock;
	
	@Column(name =  "then_block")
	private Boolean thenBlock;
	
	private Boolean last;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Policy policy;
	
	
	public IfStatement(IfStatementRequestPayload req) {
		this.variable = req.variable();
		this.value = req.value();
		this.comparisonOperator = req.comparisonOperator();
		this.elseBlock = req.elseBlock();
		this.thenBlock = req.thenBlock();
		this.last = true;
	}


	public void addPolicy(Policy policy) {
		this.policy = policy;
	}
}
