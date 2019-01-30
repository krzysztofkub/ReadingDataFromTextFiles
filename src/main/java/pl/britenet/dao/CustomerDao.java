package pl.britenet.dao;

import pl.britenet.model.Customer;
import pl.britenet.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDao {

    private static final String CREATE_CUSTOMER = "INSERT INTO customers(name, surname, age) VALUES (?,?,?)";

    public Customer create(Customer customer) {
        try (Connection connection = DbUtil.getConnection(); PreparedStatement insertStm = connection.prepareStatement(CREATE_CUSTOMER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, customer.getName());
            insertStm.setString(2, customer.getSurname());
            insertStm.setInt(3, customer.getAge());
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    customer.setId(generatedKeys.getInt(1));
                    return customer;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
