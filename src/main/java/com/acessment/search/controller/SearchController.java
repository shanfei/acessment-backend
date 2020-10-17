package com.acessment.search.controller;

import com.acessment.search.model.Employee;
import com.acessment.search.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class SearchController {

       private Logger logger = LoggerFactory.getLogger(this.getClass());

       @Autowired
       EmployeeService employeeService;

       @CrossOrigin(origins = "*")
       @GetMapping("/search")
       public List<Employee> search(@RequestParam("keyword") String keyword,
                                    @RequestParam("startFrom") @DateTimeFormat(pattern="yyyy-MM-dd") Date  startFromDate,
                                    @RequestParam("endAt") @DateTimeFormat(pattern="yyyy-MM-dd") Date endAtDate) {

              logger.debug("search for: " + keyword + " and startFrom:" + startFromDate + " endAt: " + endAtDate);

              return employeeService.searchForEmployee(keyword, startFromDate, endAtDate);

       }

       @CrossOrigin(origins = "*")
       @PostMapping("/")
       public Employee create(@RequestBody Employee employee) {
              logger.debug("create employee: " + employee.toString());
              return employeeService.create(employee);
       }
}
