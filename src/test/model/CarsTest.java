package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarsTest {

    private Cars test1;
    private Cars test2;
    private Car car;

    @BeforeEach
    public void runBefore() {

        test1 = new Cars();
        test2 = new Cars();
        car = new Car(2015, "Honda", "Civic DX", 100000, 5000);
    }

    @Test
    public void testAddCar() {

        test1.addCar(car);
        assertTrue(test1.containsCar(car));
    }

    @Test
    public void testGetNumberOfCarsInList() {

        test1.addCar(car);
        assertEquals(test1.getNumberOfCarsInList(), 1);
        test1.addCar(car);
        assertEquals(test1.getNumberOfCarsInList(), 2);
    }

    @Test
    public void testRemoveCar() {

        test1.addCar(car);
        assertFalse(test1.removeCar(car));
        assertTrue(test1.removeCar(car));
    }

    @Test
    public void testGetCarExpectedException() throws IndexOutOfBoundsException {

        test1.addCar(car);

        try {
            assertNull(test1.getCar(1));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        test1.addCar(car);
        test1.addCar(car);
        assertEquals(test1.getCar(2), car);
    }

    @Test
    public void testGetListingsAndExceptionFails() throws IndexOutOfBoundsException {

        test1.addCar(car);
        test1.getListings();

        try {
            assertEquals(test1.getCar(0), car);
        } catch (IndexOutOfBoundsException e) {
            fail();
        }

    }
}
