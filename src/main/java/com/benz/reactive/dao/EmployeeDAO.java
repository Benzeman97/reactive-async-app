package com.benz.reactive.dao;

import com.benz.reactive.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {

    void saveEmp(Employee emp);
    void saveEmpAll(List<Employee> emps);
    void deleteEmp(long eid);
    void updateEmp(Employee emp);
    Employee findByEid(long eid);
    Map<Long,Employee> findAll();
}
