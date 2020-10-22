package persistence;

import model.Car;
import model.Cars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterTest {

    private static final String TEST_FILE = "./data/testCarListings.txt";
    private Cars cars;
    private Car test;
    private Writer testWriter;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {

        testWriter = new Writer(new File(TEST_FILE));
        cars = new Cars();
        test = new Car (2015, "Honda", "Civic", 100000,5000);
    }

    @Test
    void testWriteCarListing() throws IOException, IndexOutOfBoundsException {

        cars.addCar(test);

        // saves car listings in file
        testWriter.write(cars);
        testWriter.close();

        // now read them back in and verify the data is correct

        Cars cars = Reader.readCars(new File(TEST_FILE));
        assertEquals(1, cars.getNumberOfCarsInList());

    }
}

