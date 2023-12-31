package lucyd.api.domain.ifstatement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IfStatementRequestPayload(
		
		@Size(min = 1, message = "Variable cannot be blank")
		@NotNull(message = "Variable is required")
		String variable,
		
		@Size(min = 1, message = "Value cannot be blank")
		@NotNull(message = "Value is required")
		String value,
		
		@NotNull(message = "Comparison operator is required")
		ComparisonOperator comparisonOperator,
		
		String elseBlock,
		
		String thenBlock
		
		) {

}
