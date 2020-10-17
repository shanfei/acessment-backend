package com.acessment.search.service;

import com.acessment.search.model.Employee;
import com.acessment.search.repo.EmployeeRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    public void setUp() throws Exception {
        employeeRepository.deleteAll();
        employeeRepository.save(new Employee("alex", 28, "Engineer", null, null));
        employeeRepository.save(new Employee("bob", 28, "Engineer",
                new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-10"), null));
        employeeRepository.save(new Employee("marry", 28, "Engineer",
                new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-10"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-10")));
    }

    @AfterEach
    public void tearDown() throws Exception {
        employeeRepository.deleteAll();
    }

    @Test
    public void shouldBeNotEmpty() throws Exception {
        assertThat(employeeService.searchForEmployee("a",null,null).size(), equalTo(2));
        assertThat(employeeService.searchForEmployee("alex",null,null).size(), equalTo(1));

        Date searchStart = new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-10");
        assertThat(employeeService.searchForEmployee("a", searchStart,null).size(), equalTo(0));

        searchStart = new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-12");
        assertThat(employeeService.searchForEmployee("", searchStart,null).size(), equalTo(2));
    }
}
