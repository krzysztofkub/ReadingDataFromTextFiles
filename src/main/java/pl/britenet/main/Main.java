package pl.britenet.main;

import pl.britenet.service.CustomerService;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (file.isFile()) {
            CustomerService.saveFileToDb(file);
        }
    }
}

