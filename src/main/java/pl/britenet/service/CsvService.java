package pl.britenet.service;

import pl.britenet.dao.ContactsDao;
import pl.britenet.dao.CustomerDao;
import pl.britenet.model.Contact;
import pl.britenet.model.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvService {

    public void saveToDb(String data) {
        ContactsDao contactsDao = new ContactsDao();
        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
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
                } else {
                }
                CustomerDao customerDao = new CustomerDao();
                customerDao.create(customer);
                System.out.println(customer);
                Contact contact = new Contact();
                contact.setCustomer(customer);
                for (int i = 4; i < lineArr.length; i++) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int checkType(String contact) {
        switch (contact) {

        }
    }

}
