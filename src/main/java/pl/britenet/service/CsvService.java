package pl.britenet.service;

import pl.britenet.dao.ContactDao;
import pl.britenet.dao.CustomerDao;
import pl.britenet.model.Contact;
import pl.britenet.model.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvService {

    public void saveToDb(String data) {
        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Save customer from line
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
                CustomerDao customerDao = new CustomerDao();
                customerDao.create(customer);
                //Save contacts from line
                ContactDao contactDao = new ContactDao();
                for (int i = 4; i < lineArr.length; i++) {
                    Contact contact = new Contact();
                    contact.setCustomer(customer);
                    contact.setType(checkType(lineArr[i]));
                    contact.setContact(lineArr[i]);
                    contactDao.create(contact);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int checkType(String contact) {
        //check if email
        String emailStringPattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
        Pattern pattern = Pattern.compile(emailStringPattern);
        Matcher matcher = pattern.matcher(contact);
        if (matcher.matches()) {
            return 1;
        }
        //check if phone
        String phoneStringPattern = "^[1-9]\\d{2}(-|\\s)?\\d{3}(-|\\s)?\\d{3}$";
        pattern = Pattern.compile(phoneStringPattern);
        matcher = pattern.matcher(contact);
        if (matcher.matches()) {
            return 2;
        }
        //check if jabber - NEED CHANGES
        //        String jabberStringPattern = "^([^@/<>'\\\"]+)$";
        String jabberStringPattern = "^([A-Za-z]+)$";
        pattern = Pattern.compile(jabberStringPattern);
        matcher = pattern.matcher(contact);
        if (matcher.matches()) {
            return 3;
        }
        return 0;
    }

}
