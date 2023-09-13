package lucyd.api.policy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@Column(name =  "comparison_operator")
	private String comparisonOperator;
	
	@Column(name =  "else")
	private Boolean elseBlock;
	private Boolean then;
	private Boolean last;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Policy policy;
	
	
	public IfStatement(IfStatementRegisterPayload req) {
		this.variable = req.variable();
		this.value = req.value();
		this.comparisonOperator = req.comparisonOperator();
		this.elseBlock = req.elseBlock();
		this.then = req.then();
		this.last = req.last();
		this.policy = req.policy();
	}
}
