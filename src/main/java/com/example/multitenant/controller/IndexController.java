package com.example.multitenant.controller;

import com.example.multitenant.domain.EmpRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private EmpRepository empRepository;

    public IndexController(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    @RequestMapping("/employees")
    public String getEmployees(@RequestParam(value="zone", required=false, defaultValue="east") String zone, Model model) {
        model.addAttribute("employees", empRepository.findAll());
        return "employees";
    }
}
