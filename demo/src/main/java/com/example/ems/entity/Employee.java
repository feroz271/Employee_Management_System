package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.Data;

 
@Entity
@Data
public class Employee{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="EmployeeName")  
    private String name;

    @Column(name="EmployeeEmail")
    private String email;

    @Column(name="EmployeeDepartment")
    private String department;

     

}
//{
//     "name": "Feroz",
//     "email":"dudeferoz786@gmail.com",
//     "department": "developer"

// }