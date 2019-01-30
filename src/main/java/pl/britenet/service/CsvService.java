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
                Contact contact = new Contact();
                String[] lineArr = line.split(",");
                customer.setName(lineArr[0]);
                customer.setSurname(lineArr[1]);
                if (!lineArr[2].equals("")){
                    try {
                        customer.setAge(Integer.parseInt(lineArr[2]));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                CustomerDao customerDao = new CustomerDao();
                customerDao.create(customer);
                System.out.println(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
