package pl.britenet.parser;

import pl.britenet.model.Contact;
import pl.britenet.model.ContactType;
import pl.britenet.model.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses CSV file into POJOs and saves to db
 */
public class CsvParser implements Parser {

    public List<Customer> getCustomers(File file) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Customer customer = new Customer();
                String[] lineArr = line.split(",");
                customer.setName(lineArr[0]);
                customer.setSurname(lineArr[1]);
                if (!lineArr[2].equals("")) {
                    try {
                        customer.setAge(lineArr[2]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                List<Contact> contacts = new ArrayList<>();
                for (int i = 4; i < lineArr.length; i++) {
                    Contact contact = new Contact();
                    contact.setContactType(checkType(lineArr[i]));
                    contact.setContact(lineArr[i]);
                    contacts.add(contact);
                }
                customer.setContacts(contacts);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Jabber regex needs improving.
     *
     * @param contact
     * @return type of contact [0 - unknown, 1 - email, 2 - phone, 3- jabber]
     */
    private ContactType checkType(String contact) {
        //check if email
        String emailStringPattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
        Pattern pattern = Pattern.compile(emailStringPattern);
        Matcher matcher = pattern.matcher(contact);
        if (matcher.matches()) {
            return ContactType.EMAIL;
        }
        //check if phone
        String phoneStringPattern = "^[1-9]\\d{2}(-|\\s)?\\d{3}(-|\\s)?\\d{3}$";
        pattern = Pattern.compile(phoneStringPattern);
        matcher = pattern.matcher(contact);
        if (matcher.matches()) {
            return ContactType.PHONE;
        }
        //check if jabber
        String jabberStringPattern = "^([A-Za-z]+)$";
        pattern = Pattern.compile(jabberStringPattern);
        matcher = pattern.matcher(contact);
        if (matcher.matches()) {
            return ContactType.JABBER;
        }
        return ContactType.UNKNOWN;
    }

}
