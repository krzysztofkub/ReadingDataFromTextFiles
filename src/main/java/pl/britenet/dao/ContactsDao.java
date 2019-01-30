package pl.britenet.dao;

import pl.britenet.model.Contact;
import pl.britenet.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContactsDao {

    private static final String CREATE_CUSTOMER = "INSERT INTO contacts(id_customer,type,contact) VALUES (?,?,?)";

    public Contact create(Contact contact) {
        try (Connection connection = DbUtil.getConnection(); PreparedStatement insertStm = connection.prepareStatement(CREATE_CUSTOMER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, contact.getCustomer().getId());
            insertStm.setInt(2, contact.getType());
            insertStm.setString(3, contact.getContact());
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    contact.setId(generatedKeys.getInt(1));
                    return contact;
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
