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
@CrossOrigin(origins = "*")

public class EmployeeCont {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping("/list")
    @ResponseBody
    public List<Employee> getAllEmployees() {
        List<Employee> list = employeeService.getAllEmployees();

        return list;
    }
@ResponseBody
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public Employee editEmployeeById( @PathVariable("id") Long id) {
            Employee employee = employeeService.getEmployeeById(id);
            return employee;

    }
    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String createOrUpdateEmp( @RequestBody Employee employee) {
              employeeService.createOrUpdateEmployee(employee);
              return "save sucessfully";
    }

    @RequestMapping(path = "/delete/{id}")
    public Employee deleteEmp( @PathVariable("id") Long id) {
      return   employeeService.deleteEmployeeById(id);
    }


}
