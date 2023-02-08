package ibf2022.ssf.day13_lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.ssf.day13_lecture.model.Employee;
import ibf2022.ssf.day13_lecture.repository.EmployeeRepo;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/home")
    public String employeeListPage(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);

        return "employeesList";
    }

    @GetMapping("/addnew")
    public String addPage(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employeeadd";
    }

    @PostMapping("/addnew")
    // @ModelAttribute bind the data from the Form and the object from the Form is "employee"
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult binding, Model model) {

        if (binding.hasErrors()) {
            return "employeeadd";  // display the error on the form
        }

        Boolean bResult = false;
        bResult = empRepo.save(employeeForm);

        // shows the list of employees (with the newly added one)
        return "redirect:/employees/home";
    }

    // Cannot use @DeleteMapping. Must use @GetMapping as it is displaying a page
    @GetMapping("/deleteEmployee/{email}")
    public String delEmployee(@PathVariable("email") String email) {

        Employee emp = empRepo.findByEmailId(email);

        Boolean bResult = empRepo.delete(emp);

        return "redirect:/employees/home";
    }

    @GetMapping("/updateEmployee/{email}")
    public String updEmployee(@PathVariable("email") String email, Model model) {
        Employee emp = empRepo.findByEmailId(email);

        model.addAttribute("employee", emp);

        return "employeeupdate";
    }

    @PostMapping("/updateEmp")
    public String updEmployeeProcess(@ModelAttribute("employee") Employee emp, BindingResult binding, Model model) {
        
        if (binding.hasErrors()) {
            return "employeeupdate";
        }

        empRepo.updateEmployee(emp);
        return "redirect:/employees/home";
    }
}
