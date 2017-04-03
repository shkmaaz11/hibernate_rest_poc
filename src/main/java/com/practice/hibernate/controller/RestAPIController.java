package com.practice.hibernate.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.hibernate.entities.Employee;

@RestController
@RequestMapping("/employee")
public class RestAPIController {
	public static final Logger logger = LoggerFactory
			.getLogger(RestAPIController.class);

	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployee() {

		List<Employee> empList = sessionFactory.openSession()
				.createQuery("from Employee").list();

		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ResponseEntity<String> addEmployee() {
		logger.info("addEMployee---------------->");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee emp = new Employee();
		emp.setFirstName("Sunil");
		emp.setLastName("Grover");
		logger.info("addEMployee---------------->" + emp.getFirstName());
		session.save(emp);
		logger.info("Inserted Successfully");
		session.getTransaction().commit();
		return new ResponseEntity<String>("Inserted Successfully",
				HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(
			@PathVariable("userid") int id) {
		logger.info("Id------------->" + id);
		Session session = sessionFactory.openSession();

		Employee emp = session.get(Employee.class, id);

		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public ResponseEntity<String> createEmployee(@RequestBody Employee emp) {
		logger.info("createEmployee---------------->");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		logger.info("createEmployee---------------->" + emp.getFirstName());
		session.persist(emp);
		logger.info("New Employee Inserted Successfully");
		session.getTransaction().commit();
		return new ResponseEntity<String>("New Employee Inserted Successfully",
				HttpStatus.OK);
	}
}
