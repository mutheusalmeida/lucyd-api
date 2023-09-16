package lucyd.api.domain.ifstatement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IfStatementPostRequestPayload(
		
		@Size(min = 1, message = "Variable cannot be blank")
		@NotNull(message = "Variable is required")
		String variable,
		
		@Size(min = 1, message = "Value cannot be blank")
		@NotNull(message = "Value is required")
		String value,
		
		@NotNull(message = "Comparison operator is required")
		ComparisonOperator comparisonOperator,
		
		@NotNull(message = "Else block is required")
		Boolean elseBlock,
		
		@NotNull(message = "Then block is required")
		Boolean thenBlock
		
		) {

}
