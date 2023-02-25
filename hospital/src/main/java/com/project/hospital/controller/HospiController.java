package com.project.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.hospital.model.DiagnosticResponse;
import com.project.hospital.model.enumeration.PathologyType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Controller
/**
 * Hospital project Controller
 *
 */
public class HospiController {
	
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    
    
    @RequestMapping("/diagnose")
    @ResponseBody
    @ApiOperation(value = "Returns patient diagnosis for a given patient index",
    response = DiagnosticResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Diagnose returned with success"),
		@ApiResponse(code = 400, message = "Bad request")
	})
 
    // Method
    public ResponseEntity<DiagnosticResponse> getDiagnosis(@RequestParam String patientIndex)
    {
        try {
            int index = Integer.parseInt(patientIndex);
        	DiagnosticResponse diagnostic = new DiagnosticResponse();
        	List<PathologyType> patho = new ArrayList<>();
        	
        	// Patient index is a multiple of 3
        	if (index % 3 == 0) {
        		patho.add(PathologyType.CARDIOLOGIE);
        	}

        	// Patient index is a multiple of 5
        	if (index % 5 == 0) {
            	patho.add(PathologyType.TRAUMATOLOGIE);
        	}
        	
        	// Unknown patient index
        	if (patho.isEmpty()) {
        		return ResponseEntity.badRequest().body(null);	
        	}

        	diagnostic.setPathologies(patho);
            return ResponseEntity.ok(diagnostic);
        } catch (NumberFormatException e) {
        	// Patient index is not an integer
            return ResponseEntity.badRequest().body(null);
        }
        

    }
}
