package dao;

import MyDb.DbConnection;
import entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    public int getNumberEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DbConnection.getCon()) {
            String query = "SELECT * FROM employee";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("id"));
                    employees.add(employee);
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return employees.size();
    }
}
