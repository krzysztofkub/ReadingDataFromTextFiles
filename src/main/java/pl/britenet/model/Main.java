package pl.britenet.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final String DATA = "dane-osoby.txt";

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(DATA))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataArr = line.split(",");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
