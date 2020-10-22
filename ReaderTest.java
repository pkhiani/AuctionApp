package persistence;

import model.Cars;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest {

    @Test
    void testParseCarsFile() throws IOException, IndexOutOfBoundsException {

        Reader reader = new Reader();

        Cars cars = Reader.readCars(new File("./data/testCarListings.txt"));
        assertEquals(1, cars.getNumberOfCarsInList());
    }

    @Test
    void testIOException() {
        try {
            Reader.readCars(new File("./path/not/real/path/testCarListings.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
