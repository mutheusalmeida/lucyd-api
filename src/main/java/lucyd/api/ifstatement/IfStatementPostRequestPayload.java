package lucyd.api.ifstatement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IfStatementPostRequestPayload(
		
		@NotBlank
		String variable,
		
		@NotBlank
		String value,
		
		@NotNull
		ComparisonOperator comparisonOperator,
		
		@NotNull
		Boolean elseBlock,
		
		@NotNull
		Boolean thenBlock
		
		) {

}
