package pl.britenet.main;

import pl.britenet.parser.CsvParser;
import pl.britenet.parser.XmlParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * File location.
     */
    private static final String DATA = "src/main/resources/dane-osoby.txt";

    public static void main(String[] args) {
        if (checkIfXml()) {
            XmlParser xmlParser = new XmlParser();
            xmlParser.saveToDb(DATA);
        } else {
            CsvParser csvParser = new CsvParser();
            csvParser.saveToDb(DATA);
        }
    }

    /**
     * Check if the file is XML format. If not then it's CSV.
     * @return
     */
    private static boolean checkIfXml() {
        String patternString = ".+\\.xml$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(DATA);
        return matcher.matches();
    }
}

