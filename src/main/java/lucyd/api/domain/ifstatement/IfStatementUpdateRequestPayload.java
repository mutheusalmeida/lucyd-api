package lucyd.api.domain.ifstatement;

import jakarta.validation.constraints.Size;

public record IfStatementUpdateRequestPayload(
		
		@Size(min = 1, message = "Variable cannot be blank")
		String variable,
		
		@Size(min = 1, message = "Value cannot be blank")
		String value,
		
		ComparisonOperator comparisonOperator,
		
		String elseBlock,
		String thenBlock
		) {

}
