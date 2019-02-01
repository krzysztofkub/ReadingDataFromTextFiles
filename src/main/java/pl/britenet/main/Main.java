package pl.britenet.main;

import pl.britenet.service.CsvService;
import pl.britenet.service.XmlService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String DATA = "src/main/resources/dane-osoby.txt";

    public static void main(String[] args) {
        if (checkIfXml()) {
            XmlService xmlService = new XmlService();
            xmlService.saveToDb(DATA);
        } else {
            CsvService csvService = new CsvService();
            csvService.saveToDb(DATA);
        }
    }

    private static boolean checkIfXml() {
        String patternString = ".+\\.xml$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(DATA);
        return matcher.matches();
    }
}

