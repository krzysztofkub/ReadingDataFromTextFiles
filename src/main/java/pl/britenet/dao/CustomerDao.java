package pl.britenet.dao;

import pl.britenet.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Data access object
 */
public class CustomerDao {

    /**
     * String SQL query
     */
    private static final String CREATE_CUSTOMER = "INSERT INTO customers(name, surname, age) VALUES (?,?,?)";

    /**
     * Saves customer to db
     * @param customer
     * @return customer with filled id
     */
    public Customer create(Customer customer) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/britenet" + "?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {
            String generatedColumns[] = {"ID"};
            PreparedStatement insertStm = conn.prepareStatement(CREATE_CUSTOMER, generatedColumns);
            insertStm.setString(1, customer.getName());
            insertStm.setString(2, customer.getSurname());
            insertStm.setString(3, customer.getAge());
            insertStm.executeUpdate();
            ResultSet rs = insertStm.getGeneratedKeys();
            int id;
            if (rs.next()) {
                id = rs.getInt(1);
                customer.setId(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
}
