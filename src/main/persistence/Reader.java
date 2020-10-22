package persistence;

import model.Car;
import model.Cars;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read car listings from a file
// inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of cars parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Cars readCars(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of cars parsed from list of strings
    // where each string contains data for one account
    private static Cars parseContent(List<String> fileContent) {
        Cars cars = new Cars();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            cars.addCar(parseCar(lineComponents));
        }

        return cars;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 5 where element 0 represents the
    // year of the car, element 1 represents
    // the brand, elements 2 represents the model, element 3 represents
    // the mileage of the car, and element 5 is the bid price
    // EFFECTS: returns a Car constructed from components
    private static Car parseCar(List<String> components) {
        int year = Integer.parseInt(components.get(0));
        String brand = components.get(1);
        String model = components.get(2);
        int mileage = Integer.parseInt(components.get(3));
        int bidPrice = Integer.parseInt(components.get(4));
        return new Car(year, brand, model, mileage, bidPrice);
    }
}
