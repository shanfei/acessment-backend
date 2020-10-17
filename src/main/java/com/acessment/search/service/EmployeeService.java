package com.acessment.search.service;

import com.acessment.search.model.Employee;
import com.acessment.search.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

       private Logger logger = LoggerFactory.getLogger(this.getClass());

       @Autowired
       EmployeeRepository employeeRepository;

       @Autowired
       MongoTemplate mongoTemplate;

       public List<Employee> searchForEmployee(String keyword, Date startFrom, Date endAt) {

                String regex = ".*" + keyword + ".*";
                Criteria criteria = Criteria.where("name").regex(regex);

                if (startFrom != null) {
                    criteria.and("startFrom").gte(startFrom);
                }

                if (endAt != null) {
                    criteria.and("endAt").lt(endAt);
                }

                Query query = Query.query(criteria);

                return mongoTemplate.find(query, Employee.class);

       }


       public Employee create(Employee employee) {
              logger.debug("create Employee : " + employee.toString());
              return employeeRepository.save(employee);
       }

}
