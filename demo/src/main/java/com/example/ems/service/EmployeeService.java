package com.example.ems.service;


 import com.example.ems.entity.Employee;
 import com.example.ems.repository.EmpRepository;
 import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.Optional;


@Service // by anotation we declared this class as service layer
public class EmployeeService{

 private final EmpRepository empRepository;//Instance variable
// Dependency Injection with Spring
// Spring Boot automatically detects and injects the EmployeeRepository instance because it is annotated with @Service, and EmployeeRepository extends JpaRepository. This technique is called Constructor-based Dependency Injection.

 public EmployeeService(EmpRepository empRepository){// Method parameter
    this.empRepository = empRepository;
    //this keyword Java me current class ka reference hota hai. Jab hume instance variables aur method ke parameters me confusion ho jata hai (dono ka naam same ho), tab this keyword ka use karke hum instance variable ko refer karte hain.
 }

//Add a new employee
public Employee addEmployee(Employee employee) {
        // Business logic: Ensure required fields are not empty
        if (employee.getName() == null ) {
            throw new IllegalArgumentException("Employee name cannot be  null");
        }
         else if ( employee.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Employee email cannot be empty");

        }

        // Save to the database
        return empRepository.save(employee);
         // repository ko bolra ki data ko ja DB mai store kar
    }

 // Get all employees
    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
        //Imagine you're the class monitor, and your teacher asks for the list of all students in your class. You open the class register (which is like the employeeRepository), and you copy all the names from the book (using findAll()) and give the list back to the teacher (return).
    }
// Update an employee

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        // Business logic: Check if the employee exists
        Optional<Employee> existingEmployee = empRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            // Update only non-null fields
            if (updatedEmployee.getName() != null) {
                employee.setName(updatedEmployee.getName());
            }
            if (updatedEmployee.getEmail() != null) {
                employee.setEmail(updatedEmployee.getEmail());
            }
            if (updatedEmployee.getDepartment() != null) {
                employee.setDepartment(updatedEmployee.getDepartment());
            }
            return empRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }
// Pehle, code check karta hai ki diya gaya employee ID database mein maujood hai ya nahi.
// Agar employee milta hai, toh uske non-null fields (name, email, department) ko update kar leta hai.
// Agar employee nahi milta, toh ek error throw karta hai ki "Employee not found".

// Delete an employee by ID
    public void deleteEmployee(Long id) {
        // Business logic: Ensure employee exists before deleting
        if (!empRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        empRepository.deleteById(id);
    }
// existsById(id) check karta hai ki diya gaya employee ID database mein hai ya nahi.
// Agar employee nahi milta, toh RuntimeException throw hota hai ki "Employee not found".
// Agar employee milta hai, toh deleteById(id) use karke employee ko database se delete kar diya jata hai.    

}

