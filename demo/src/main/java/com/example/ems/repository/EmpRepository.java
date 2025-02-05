package com.example.ems.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ems.entity.Employee;


public interface EmpRepository extends JpaRepository<Employee, Long> {

}
