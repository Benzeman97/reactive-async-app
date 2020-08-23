package com.benz.reactive.controller;

import com.benz.reactive.dao.EmployeeRepository;
import com.benz.reactive.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpController {

    private EmployeeRepository empRepo;

    @Autowired
    public EmpController(EmployeeRepository empRepo)
    {
        this.empRepo=empRepo;
    }

    @PostMapping("/save")
    @Consumes({MediaType.APPLICATION_JSON})
    public void saveEmp(Employee emp)
    {
            if(emp.getId()!=0 && !emp.getEname().trim().isEmpty())
                empRepo.saveEmp(emp);
            else
                throw new NullPointerException();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Employee emp)
    {
        if(emp.getId()!=0)
            empRepo.deleteEmp(emp.getId());
        else
            throw new NullPointerException();
    }

    @PutMapping("/update")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(@RequestBody Employee emp)
    {
        if(emp.getId()!=0 && !emp.getEname().trim().isEmpty())
            empRepo.updateEmp(emp);
        else
            throw new NullPointerException();
    }

    @PostMapping("/get")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Employee getEmployee(Employee emp)
    {
        if(emp.getId()!=0)
           return empRepo.findByEid(emp.getId());
        else
            throw new NullPointerException();
    }

    @GetMapping("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Map<Long,Employee> getEmployees()
    {
          return empRepo.findAll();
    }

}
