package lucyd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lucyd.api.domain.ifstatement.IfStatement;
import lucyd.api.domain.ifstatement.IfStatementRepository;
import lucyd.api.domain.ifstatement.IfStatementResponsePayload;
import lucyd.api.domain.ifstatement.IfStatementUpdateRequestPayload;

@RestController
@RequestMapping("if_statements")
public class IfStatementController {

	@Autowired
	private IfStatementRepository ifStatementRepository;
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		ifStatementRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<IfStatementResponsePayload> update(@PathVariable Long id, @RequestBody IfStatementUpdateRequestPayload req) {
		IfStatement ifStatement = ifStatementRepository.getReferenceById(id);
		ifStatement.update(req);
		
		return ResponseEntity.ok(new IfStatementResponsePayload(ifStatement));
	}
}
