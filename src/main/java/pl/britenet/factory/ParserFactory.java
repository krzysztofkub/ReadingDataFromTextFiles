package pl.britenet.factory;

import pl.britenet.parser.CsvParser;
import pl.britenet.parser.Parser;
import pl.britenet.parser.XmlParser;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserFactory {
    public static Parser getParser(File file) {
        String patternString = ".+\\.xml$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(file.getName());
        if (matcher.matches()) {
            return new XmlParser();
        } else {
            return new CsvParser();
        }
    }
}
