package com.project.hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.hospital.model.DiagnosticResponse;
import com.project.hospital.model.enumeration.PathologyType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospitalApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TestRestTemplate restTemplate;
	
    @Autowired
    private ObjectMapper objectMapper;

	@Test
	public void testDiagnose() throws Exception {
		
        // Test data for result CARDIOLOGIE
        String patientIndex = "33";

        // Calls the service /diagnose
        ResponseEntity<String> response = restTemplate.exchange("/diagnose?patientIndex={patientIndex}",
                HttpMethod.GET, null, String.class, patientIndex);

        // Check response is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Convert object response to objet DiagnosticResponse
        DiagnosticResponse actualResponse = objectMapper.readValue(response.getBody(), DiagnosticResponse.class);

        // Vérifier que le résultat est correct
        List<PathologyType> expectedPathologies = Arrays.asList(PathologyType.CARDIOLOGIE);
        DiagnosticResponse expectedResponse = new DiagnosticResponse(expectedPathologies);
        assertEquals(expectedResponse, actualResponse);
		

        // Test data for result TRAUMATOLOGIE
        patientIndex = "55";

        response = restTemplate.exchange("/diagnose?patientIndex={patientIndex}",
                HttpMethod.GET, null, String.class, patientIndex);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        actualResponse = objectMapper.readValue(response.getBody(), DiagnosticResponse.class);

        expectedPathologies = Arrays.asList(PathologyType.TRAUMATOLOGIE);
        expectedResponse = new DiagnosticResponse(expectedPathologies);
        assertEquals(expectedResponse, actualResponse);


        // Test data for result CARDIOLOGIE AND TRAUMATOLOGIE
        patientIndex = "15";

        response = restTemplate.exchange("/diagnose?patientIndex={patientIndex}",
                HttpMethod.GET, null, String.class, patientIndex);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        actualResponse = objectMapper.readValue(response.getBody(), DiagnosticResponse.class);

        expectedPathologies = Arrays.asList(PathologyType.CARDIOLOGIE, PathologyType.TRAUMATOLOGIE);
        expectedResponse = new DiagnosticResponse(expectedPathologies);
        assertEquals(expectedResponse, actualResponse);


        // Données de test pour résultat CARDIOLOGIE ET TRAUMATOLOGIE
        patientIndex = "X";

        // Appeler le service /diagnose
        response = restTemplate.exchange("/diagnose?patientIndex={patientIndex}",
                HttpMethod.GET, null, String.class, patientIndex);

        // Check response is KO
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
