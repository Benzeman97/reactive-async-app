package com.benz.reactive.controller;

import com.benz.reactive.dao.EmployeeRepository;
import com.benz.reactive.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
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
    public void saveEmp(@RequestBody Mono<Employee> emp)
    {
             emp.subscribe(e-> {
                 e.setDate(new Date());
                 empRepo.saveEmp(e);
             });
    }

    @PostMapping("/save/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public void saveEmpAll(@RequestBody Flux<Employee> emps)
    {
         emps.collectMap(e->e.getId(),emp->emp)
         .subscribe(map->empRepo.saveEmpAll((List<Employee>) map));
    }

  /*  @DeleteMapping("/delete")
    public void delete(@RequestBody Employee emp)
    {
        if(emp.getId()!=0)
            empRepo.deleteEmp(emp.getId());
        else
            throw new NullPointerException();
    }

    @DeleteMapping("/delete/all")
    public void delete(@RequestBody List<Employee> emps)
    {
        if(emps.size()!=0)
            emps.forEach(emp->empRepo.deleteEmp(emp.getId()));
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
    }*/

}
