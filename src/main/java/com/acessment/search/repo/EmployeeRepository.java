package com.acessment.search.repo;

import com.acessment.search.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

      List<Employee> findByNameLike(String regex);
}
