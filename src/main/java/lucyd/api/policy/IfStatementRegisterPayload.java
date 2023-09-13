package lucyd.api.policy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IfStatementRegisterPayload(
		
		@NotBlank
		String variable,
		
		@NotBlank
		String value,
		
		@NotBlank
		String comparisonOperator,
		
		@NotNull
		Boolean elseBlock,
		
		@NotNull
		Boolean then,
		
		@NotNull
		Boolean last,
		
		@NotBlank
		Policy policy
		) {

}
