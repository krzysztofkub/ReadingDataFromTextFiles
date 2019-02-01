package pl.britenet.dao;

import pl.britenet.model.Contact;

import java.sql.*;

/**
 * Data access object
 */
public class ContactDao {

    /**
     * String SQL query
     */
    private static final String CREATE_CUSTOMER = "INSERT INTO contacts(id_customer,type,contact) VALUES (?,?,?)";

    /**
     * Saves contact to db
     * @param contact
     */
    public void create(Contact contact) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/britenet" + "?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {
            PreparedStatement insertStm = conn.prepareStatement(CREATE_CUSTOMER);
            insertStm.setInt(1, contact.getCustomer().getId());
            insertStm.setInt(2, contact.getType());
            insertStm.setString(3, contact.getContact());
            insertStm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
