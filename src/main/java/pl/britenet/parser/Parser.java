package pl.britenet.parser;

import pl.britenet.model.Customer;

import java.io.File;
import java.util.List;

public interface Parser {
    List<Customer> getCustomers(File file);
}
