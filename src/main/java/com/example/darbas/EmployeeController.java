package com.example.darbas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employee-list";
    }

    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.getEmployeeById(id));
        return "employee-edit";
    }

    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "redirect:/employees";
    }
}