package pl.britenet.parser;

import pl.britenet.dao.ContactDao;
import pl.britenet.dao.CustomerDao;
import pl.britenet.model.Contact;
import pl.britenet.model.Customer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses XML file into POJOs and saves to db. Uses StAX parser.
 */
public class XmlParser {

    public void saveToDb(String data) {
        Customer customer = null;
        List<Contact> contacts = null;
        Contact contact = null;
        String tagContent = null;
        CustomerDao customerDao = new CustomerDao();
        ContactDao contactDao = new ContactDao();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            InputStream in = new FileInputStream(data);
            XMLStreamReader reader = factory.createXMLStreamReader(in);
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "person": {
                                customer = new Customer();
                                break;
                            }
                            case "contacts": {
                                contacts = new ArrayList<>();
                                break;
                            }
                            case "email": {
                                contact = new Contact(1);
                                break;
                            }
                            case "phone": {
                                contact = new Contact(2);
                                break;
                            }
                            case "jabber": {
                                contact = new Contact(3);
                                break;
                            }
                            case "persons":
                            case "name":
                            case "surname":
                            case "age":
                            case "city":
                                break;
                            default: {
                                contact = new Contact(0);
                            }
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "person":
                                customerDao.create(customer);
                                for (Contact item : contacts) {
                                    item.setCustomer(customer);
                                    contactDao.create(item);
                                }
                                break;
                            case "name":
                                customer.setName(tagContent);
                                break;
                            case "surname":
                                customer.setSurname(tagContent);
                                break;
                            case "age":
                                customer.setAge(tagContent);
                                break;
                            case "city":
                            case "persons":
                            case "contacts":
                                break;
                            case "email":
                            case "phone":
                            case "jabber":
                            default:
                                contact.setContact(tagContent);
                                contacts.add(contact);
                                break;
                        }
                        break;
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

