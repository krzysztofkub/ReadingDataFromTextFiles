package pl.britenet.dao;

import pl.britenet.model.Contact;


import java.sql.*;

public class ContactsDao {

    private static final String CREATE_CUSTOMER = "INSERT INTO contacts(id_customer,type,contact) VALUES (?,?,?)";

    public Contact create(Contact contact) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/britenet" + "?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {
            String generatedColumns[] = { "ID" };
            PreparedStatement insertStm = conn.prepareStatement(CREATE_CUSTOMER, generatedColumns);
            insertStm.setInt(1, contact.getCustomer().getId());
            insertStm.setInt(2, contact.getType());
            insertStm.setString(3, contact.getContact());
            insertStm.executeUpdate();
            ResultSet rs = insertStm.getGeneratedKeys();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
