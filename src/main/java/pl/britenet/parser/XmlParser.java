package pl.britenet.parser;

import pl.britenet.model.Contact;
import pl.britenet.model.ContactType;
import pl.britenet.model.Customer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses XML file into POJOs and saves to db. Uses StAX parser.
 */
public class XmlParser implements Parser {

    public List<Customer> getCustomers(File file) {
        Customer customer = null;
        List<Contact> contacts = null;
        List<Customer> customers = null;
        Contact contact = null;
        String tagContent = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            InputStream in = new FileInputStream(file);
            XMLStreamReader reader = factory.createXMLStreamReader(in);
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "persons": {
                                customers = new ArrayList<>();
                                break;
                            }
                            case "person": {
                                customer = new Customer();
                                break;
                            }
                            case "contacts": {
                                contacts = new ArrayList<>();
                                break;
                            }
                            case "email": {
                                contact = new Contact(ContactType.EMAIL);
                                break;
                            }
                            case "phone": {
                                contact = new Contact(ContactType.PHONE);
                                break;
                            }
                            case "jabber": {
                                contact = new Contact(ContactType.JABBER);
                                break;
                            }
                            case "name":
                            case "surname":
                            case "age":
                            case "city":
                                break;
                            default: {
                                contact = new Contact(ContactType.UNKNOWN);
                            }
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "person":
                                customers.add(customer);
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
                            case "contacts":
                                customer.setContacts(contacts);
                                break;
                            case "city":
                            case "persons":
                                break;
                            default:
                                contact.setContact(tagContent);
                                contacts.add(contact);
                                break;
                        }
                        break;
                }
            }
        } catch (XMLStreamException | FileNotFoundException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }
        return customers;
    }
}

