package pl.britenet.main;

import pl.britenet.service.CustomerService;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * File location.
     */
    private static final String DATA = "src/main/resources/dane-osoby.txt";

    public static void main(String[] args) {
        File file = new File(DATA);
        CustomerService.saveFileToDb(file);
    }
}

