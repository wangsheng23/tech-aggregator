package com.wsheng.aggregator.spring.mvcrest.bean.vo.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDS {

	private static Map<Long, Employee> allEmployees;
	static {
		allEmployees = new HashMap<Long, Employee>();
		Employee e1 = new Employee(1L, "Huang Yi Ming", "huangyim@cn.ibm.com");
		Employee e2 = new Employee(2L, "Wu Dong Fei", "wudongf@cn.ibm.com");
		allEmployees.put(e1.getId(), e1);
		allEmployees.put(e2.getId(), e2);
	}
	
	public void add(Employee e) {
		allEmployees.put(e.getId(), e);
	}

	public Employee get(long id) {
		return allEmployees.get(id);
	}

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		
		for (Map.Entry<Long, Employee> entry : allEmployees.entrySet()) {
		    employees.add(entry.getValue());
		}
		
		return employees;
	}

	public void remove(long id) {
		allEmployees.remove(id);
	}

	public void update(Employee e) {
		allEmployees.put(e.getId(), e);
	}

}
