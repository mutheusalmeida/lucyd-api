package lucyd.api.policy;

import java.util.List;

import lucyd.api.ifstatement.IfStatementResponsePayload;

public record PolicyGetResponsePayload(
			Long id,
			List<IfStatementResponsePayload> ifStatements
		) {
	
	public PolicyGetResponsePayload(Policy policy) {
		this(policy.getId(), policy.getIfStatements().stream().map(IfStatementResponsePayload::new).toList());
	}

}
