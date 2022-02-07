package jp.co.sample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository

public class EmployeeRepository {

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i)->{
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristic"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	public List<Employee> findAll(){
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristic, department_count"
				+ "FROM employees ORDER BY hire_date;";

		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		if(employeeList.size() == 0) {
			return null;
		}
		return employeeList;
	}

	public Employee load(Integer id) {
		String sql ="SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristic, department_count\r\n"
				+ "FROM employees WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		try {
			return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		} catch (Exception e) {
			return null;
		}
	}

	public void update(Employee employee) {
		String sql="UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hire_date, mail_address=:mail_address,zip_code=:zip_code,address=:address,telephone=:telephone,salary=:salary,characteristic=:characteristic,dependents_count=:dependents_count"
				+ "WHERE NOT id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql, param);
		
	}
	
	
	
	
}
