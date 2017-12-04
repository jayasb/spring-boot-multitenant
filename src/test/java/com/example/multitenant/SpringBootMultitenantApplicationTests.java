package com.example.multitenant;

import com.example.multitenant.domain.Emp;
import com.example.multitenant.domain.EmpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMultitenantApplicationTests {

	@Autowired
	private EmpRepository empRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findAllEmployees() {
		List<Emp> employees = empRepository.findAll();
		assertNotNull(employees);
		assertTrue(!employees.isEmpty());
	}

}
