package lucyd.api.ifstatement;

public record IfStatementGetResponsePayload(
		
		Long id,
		
		String variable,
		
		String value,
		
		ComparisonOperator comparisonOperator,
	
		Boolean elseBlock,
	
		Boolean thenBlock
		
		) {

	public IfStatementGetResponsePayload(IfStatement ifStatement) {
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
