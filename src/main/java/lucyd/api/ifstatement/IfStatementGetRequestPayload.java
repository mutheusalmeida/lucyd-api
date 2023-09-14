package lucyd.api.ifstatement;

public record IfStatementGetRequestPayload(
		String variable,
		String value,
		ComparisonOperator comparisonOperator,
		Boolean elseBlock,
		Boolean thenBlock
		) {

}
