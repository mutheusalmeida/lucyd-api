package lucyd.api.policy;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lucyd.api.ifstatement.IfStatementRequestPayload;

public record PolicyItemRequestPayload(
		
		@NotNull @Valid
		IfStatementRequestPayload ifStatement
		) {

}
