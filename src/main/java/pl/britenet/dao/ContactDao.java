package pl.britenet.dao;

import pl.britenet.model.Contact;
import pl.britenet.utils.DbUtil;

import java.sql.*;

/**
 * Data access object
 */
public class ContactDao {

    /**
     * String SQL query
     */
    private static final String CREATE_CONTACT = "INSERT INTO contacts(id_customer,type,contact) VALUES (?,?,?)";

    /**
     * Saves contact to db
     * @param contact
     */
    public static void save(Contact contact, int customer_id) {
        try (Connection conn = DbUtil.getConnection(); PreparedStatement insertStm = conn.prepareStatement(CREATE_CONTACT, PreparedStatement.RETURN_GENERATED_KEYS))  {
            insertStm.setInt(1, customer_id);
            insertStm.setInt(2, contact.getType());
            insertStm.setString(3, contact.getContact());
            insertStm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
