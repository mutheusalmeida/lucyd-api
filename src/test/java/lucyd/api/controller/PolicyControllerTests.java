package lucyd.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import lucyd.api.domain.ifstatement.IfStatementResponsePayload;
import lucyd.api.domain.policy.Policy;
import lucyd.api.domain.policy.PolicyRepository;
import lucyd.api.domain.policy.PolicyRequestPayload;
import lucyd.api.domain.policy.PolicyResponsePayload;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PolicyControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	JacksonTester<PolicyRequestPayload> policyRequestJson;
	
	@Autowired
	JacksonTester<PolicyResponsePayload> policyResponseJson;
	
	@MockBean
	PolicyRepository policyRepository;
	
	@Test
	@DisplayName("Should return an HTTP 400 error when invalid information is provided.")
	void register_scenario1() throws Exception {
		var response = mockMvc.perform(post("/policies")).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	@DisplayName("Should return an HTTP 201 created when valid information is provided.")
	void register_scenario2() throws Exception {
		var policyRequest = new PolicyRequestPayload("Policy test");
		var policy = new Policy(policyRequest);
		List<IfStatementResponsePayload> ifStatements = new ArrayList<IfStatementResponsePayload>();
		
		when(policyRepository.save(any())).thenReturn(policy);
		
		var response = mockMvc
				.perform(post("/policies")
					.contentType(MediaType.APPLICATION_JSON)
					.content(policyRequestJson.write(policyRequest).getJson()))
				.andReturn().getResponse();
		
		var policyResponse = new PolicyResponsePayload(
				null, 
				policy.getName(), 
				ifStatements
				);
		
		var expectedJson = policyResponseJson.write(policyResponse).getJson();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString()).isEqualTo(expectedJson);
	}
}
