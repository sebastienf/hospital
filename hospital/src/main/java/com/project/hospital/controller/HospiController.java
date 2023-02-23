package com.project.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
