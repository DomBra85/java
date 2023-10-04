package com.example.darbas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setFirstName(rs.getString("firstName"));
        employee.setLastName(rs.getString("lastName"));
        employee.setEmail(rs.getString("email"));
        employee.setPosition(rs.getString("position"));
        return employee;
    };

    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, employeeRowMapper);
    }

    public Employee findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, employeeRowMapper);
    }

    public int save(Employee employee) {
        String sql = "INSERT INTO employees (firstName, lastName, email, position) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getEmail(),
                employee.getPosition());
    }

    public int update(Employee employee) {
        String sql = "UPDATE employees SET firstName = ?,  lastName = ?, email = ?, position = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getEmail(),
                employee.getPosition(), employee.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
