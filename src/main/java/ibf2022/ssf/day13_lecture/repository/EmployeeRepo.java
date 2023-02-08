package ibf2022.ssf.day13_lecture.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import ibf2022.ssf.day13_lecture.model.Employee;

@Repository
public class EmployeeRepo {

    private List<Employee> employees;

    public EmployeeRepo() throws ParseException{
        if (employees == null) {
            employees = new ArrayList<>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse("1991-06-15");
        Employee emp = new Employee("Derrick", "Tan", "derrick@gmail.com", 
        "91710922", 7500, dt, "10 Ghim Moh", 272210);
        employees.add(emp);

        dt = df.parse("1991-04-06");
        emp = new Employee("Andy", "Lau", "andy@gmail.com", 
        "92994399", 8500, dt, "28 Ghim Moh", 272228);
        employees.add(emp);
    }
    
    public List<Employee> findAll() {

        return employees;
    }

    public Boolean save(Employee employee) {
        Boolean result = employees.add(employee);
        return result;
    }

    public Boolean delete(Employee employee) {
        // Employee e = employees.stream().filter(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail())).
        // findFirst().get();

        Boolean result = false;
        int employeeIndex = employees.indexOf(employee);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            result = true;
        }

        return result;
    }

}
