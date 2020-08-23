package com.benz.reactive.dao;

import com.benz.reactive.model.Employee;

import java.util.Map;

public interface EmployeeDAO {

    void saveEmp(Employee emp);
    void deleteEmp(long eid);
    void updateEmp(Employee emp);
    Employee findByEid(long eid);
    Map<Long,Employee> findAll();
}
