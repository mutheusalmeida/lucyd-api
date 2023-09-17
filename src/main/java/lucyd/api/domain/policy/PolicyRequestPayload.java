package lucyd.api.domain.policy;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PolicyRequestPayload(
		
		@Size(min = 1, message = "Name cannot be blank")
		@NotNull(message = "Name is required")
		String name
		) {

}
