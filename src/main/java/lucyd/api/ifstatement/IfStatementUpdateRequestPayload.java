package lucyd.api.ifstatement;

public record IfStatementUpdateRequestPayload(
		String variable,
		String value,
		ComparisonOperator comparisonOperator,
		Boolean elseBlock,
		Boolean thenBlock
		) {

}
