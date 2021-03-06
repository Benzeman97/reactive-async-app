package com.benz.reactive.dao;

import com.benz.reactive.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository implements EmployeeDAO {

    private HashOperations<String,Long,Employee> hashOperations;

    @Autowired
    public EmployeeRepository(@Qualifier("redisTemplate") RedisTemplate<String,Employee> redisTemplate)
    {
              this.hashOperations=redisTemplate.opsForHash();
    }

    @Override
    public void saveEmp(Employee emp) {

        hashOperations.put("EMP",emp.getId(),emp);

    }

    @Override
    public void saveEmpAll(List<Employee> emps) {

        Map<Long,Employee> employees=new HashMap<>();

        emps.forEach(emp->{
            emp.setDate(new Date());
            employees.put(emp.getId(),emp);
        });
        hashOperations.putAll("EMP",employees);
    }


    @Override
    public void deleteEmp(long eid) {
        hashOperations.delete("EMP",eid);

    }

    @Override
    public void updateEmp(Employee emp) {
          emp.setDate(new Date());
          hashOperations.put("EMP",emp.getId(),emp);
    }

    @Override
    public Employee findByEid(long eid) {
         return hashOperations.get("EMP",eid);
    }

    @Override
    public Map<Long, Employee> findAll() {
        return hashOperations.entries("EMP");
    }
}
