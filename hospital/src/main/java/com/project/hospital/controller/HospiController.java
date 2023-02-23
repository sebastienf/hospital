package com.project.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * Hospital project Controller
 *
 */
public class HospiController {
	
    @RequestMapping("/hello")
    @ResponseBody
 
    // Method
    public String helloWorld()
    {
        // Print statement
        return "Hello World!";
    }
    
    @GetMapping("/")
    public String index(Model model) {
        //model.addAttribute("eventName", "FIFA 2018");
        return "index";
    }
    
    
    @RequestMapping("/diagnose")
    @ResponseBody
 
    // Method
    public String getDiagnostic(@RequestParam String patientIndex)
    {
        // Print statement
        return "Hello World!";
    }
}
