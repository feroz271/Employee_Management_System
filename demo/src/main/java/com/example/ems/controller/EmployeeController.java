package com.example.ems.controller;

 import com.example.ems.entity.Employee;
 import com.example.ems.service.EmployeeService;
 import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
    public class EmployeeController{

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

@PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {  //i will be getting below Json data in request body 
        return employeeService.addEmployee(employee);
        
    }
  
@GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
        //Yeh code browser se GET request aane par sabhi employees ki list return karta hai. @GetMapping HTTP request ko handle karta hai, aur employeeService.getAllEmployees() database se data laata hai.
    }

@PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    
// @PutMapping("/{id}"): HTTP PUT request ko handle karta hai aur id URL se lekar method mein pass karta hai.
// @PathVariable Long id: URL se employee ka id read karta hai.
// @RequestBody Employee employee: Request body se updated employee data ko read karta hai.
// employeeService.updateEmployee(id, employee): Service layer mein employee update karne ke liye call karta hai.
}

@DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

// @DeleteMapping("/{id}"): Yeh DELETE request ko handle karta hai aur id ko URL se get karta hai.
// @PathVariable Long id: URL se employee ka ID fetch karke method mein pass karta hai.
// employeeService.deleteEmployee(id): Service layer ko call karke employee ko database se delete karta hai.



}

//{
//     "name": "Feroz",
//     "email":"dudeferoz786@gmail.com",
//     "department": "developer"

// }