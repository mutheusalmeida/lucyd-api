package lucyd.api.ifstatement;

public record IfStatementPostResponsePayload(
		String variable,
		String value,
		ComparisonOperator comparisonOperator,
		Boolean elseBlock,
		Boolean thenBlock
		) {
	public IfStatementPostResponsePayload(IfStatement ifStatement) {
		this(
				ifStatement.getVariable(), 
				ifStatement.getValue(), 
				ifStatement.getComparisonOperator(), 
				ifStatement.getElseBlock(), 
				ifStatement.getThenBlock()
				);
	}
}
