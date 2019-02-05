package pl.britenet.service;

import pl.britenet.dao.ContactDao;
import pl.britenet.dao.CustomerDao;
import pl.britenet.factory.ParserFactory;
import pl.britenet.model.Contact;
import pl.britenet.model.Customer;
import pl.britenet.parser.CsvParser;
import pl.britenet.parser.Parser;
import pl.britenet.parser.XmlParser;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService {
    public static void saveFileToDb(File file) {
        Parser parser = ParserFactory.getParser(file);
        List<Customer> customers = parser.getCustomers(file);
        for (Customer customer : customers) {
            CustomerDao.save(customer);
            for (Contact contact : customer.getContacts()) {
                ContactDao.save(contact, customer.getId());
            }
        }
    }


}
