package lucyd.api.domain.policy;

import java.util.List;

import lucyd.api.domain.ifstatement.IfStatementResponsePayload;

public record PolicyResponsePayload(
			Long id,
			List<IfStatementResponsePayload> ifStatements
		) {
	
	public PolicyResponsePayload(Policy policy) {
		this(policy.getId(), policy.getIfStatements().stream().map(IfStatementResponsePayload::new).toList());
	}

}
