package lucyd.api.policy;

import java.util.List;

import lucyd.api.ifstatement.IfStatementGetResponsePayload;

public record PolicyListGetResponsePayload(
			Long id,
			List<IfStatementGetResponsePayload> ifStatements
		) {
	
	public PolicyListGetResponsePayload(Policy policy) {
		this(policy.getId(), policy.getIfStatements().stream().map(IfStatementGetResponsePayload::new).toList());
	}

}
