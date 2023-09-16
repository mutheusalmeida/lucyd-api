package lucyd.api.domain.ifstatement;

public record IfStatementResponsePayload(
		Long id,
		String variable,
		String value,
		ComparisonOperator comparisonOperator,
		Boolean elseBlock,
		Boolean thenBlock
		) {

	public IfStatementResponsePayload(IfStatement ifStatement) {
		this(
				ifStatement.getId(), 
				ifStatement.getVariable(), 
				ifStatement.getValue(), 
				ifStatement.getComparisonOperator(), 
				ifStatement.getElseBlock(), 
				ifStatement.getThenBlock()
				);
	}
}
