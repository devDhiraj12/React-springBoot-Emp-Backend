package com.thyleaf.project.Controller;

import com.thyleaf.project.Entity.Employee;
import com.thyleaf.project.Repository.EmployeeRepo;
import com.thyleaf.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeCont {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping
    public String getAllEmployees(Model model) {
        List<Employee> list = employeeService.getAllEmployees();
        model.addAttribute("employees",list);
        return "add-employee";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
  {
        if (id.isPresent()) {
            Employee employee = employeeService.getEmployeeById(id.get());
            model.addAttribute("employee", employee);
        } else {
            model.addAttribute("employee", new Employee());
        }
        return "add-edit-employee";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmp(Employee employee)
    {
        employeeService.createOrUpdateEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{id}")

    public String deleteEmp(Model model, @PathVariable("id") Long id)
    {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }


}
