package lucyd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lucyd.api.ifstatement.IfStatement;
import lucyd.api.ifstatement.IfStatementGetRequestPayload;
import lucyd.api.ifstatement.IfStatementRepository;

@RestController
@RequestMapping("if_statements")
public class IfStatementController {

	@Autowired
	private IfStatementRepository ifStatementRepository;
	
	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		ifStatementRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public void update(@PathVariable Long id, @RequestBody IfStatementGetRequestPayload req) {
		IfStatement ifStatement = ifStatementRepository.getReferenceById(id);
		ifStatement.update(req);
	}
}
