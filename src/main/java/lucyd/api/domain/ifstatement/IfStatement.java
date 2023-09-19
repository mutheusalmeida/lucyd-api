package lucyd.api.domain.ifstatement;

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
import lucyd.api.domain.policy.Policy;

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
	private String elseBlock;
	
	@Column(name =  "then_block")
	private String thenBlock;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Policy policy;
	
	
	public IfStatement(IfStatementRequestPayload req, Policy policy) {
		this.variable = req.variable();
		this.value = req.value();
		this.comparisonOperator = req.comparisonOperator();
		this.elseBlock = req.elseBlock();
		this.thenBlock = req.thenBlock();
		this.policy = policy;
	}

	public void update(IfStatementUpdateRequestPayload req) {
		if (req.variable() != null) {
			this.variable = req.variable();
		}
		
		if (req.value() != null) {
			this.value = req.value();
		}
		
		if (req.comparisonOperator() != null) {
			this.comparisonOperator = req.comparisonOperator();
		}
		
		if (req.elseBlock() != null) {
			this.elseBlock = req.elseBlock();
		}
		
		if (req.thenBlock() != null) {
			this.thenBlock = req.thenBlock();
		}
		
	}
}
