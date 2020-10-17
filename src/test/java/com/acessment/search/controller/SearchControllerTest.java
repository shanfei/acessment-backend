package com.acessment.search.controller;

import com.acessment.search.model.Employee;
import com.acessment.search.service.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SearchController.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        this.employeeList = new ArrayList<>();
        this.employeeList.add(new Employee("alex", 28, "Engineer", new Date(), null));
    }

    @Test
    void shouldReturnAllEmployees() throws Exception {

         String keyword = "alex";
         Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-22");
         Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-22");
         given(employeeService.searchForEmployee(keyword,fromDate,endDate)).willReturn(this.employeeList);

         this.mockMvc.perform(get("/api/v1/employee/search"))
                     .andExpect(status().isOk());

    }
}
