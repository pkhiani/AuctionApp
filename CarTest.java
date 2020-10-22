package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car test1;
    private Car test2;

    @BeforeEach
    public void runBefore() {

        test1 = new Car(2015, "Honda", "Civic DX", 100000, 1000);
        test2 = new Car(2014, "Toyota", "RAV4", 120000, 4000);
    }

    @Test
    public void testSetCar() {

        assertEquals(test1.getYear(), 2015);
        assertEquals(test1.getBrand(), "Honda");
        assertEquals(test1.getModel(), "Civic DX");
        assertEquals(test1.getMileage(), 100000);
    }

    @Test
    public void testChangingBidPrice() {

        test1.setBidPrice(1000);
        test1.setBidPrice(2000);
        assertEquals(test1.getBidPrice(), 2000);
    }

    @Test
    public void testMultipleCars() {

        assertEquals(test1.getYear(), 2015);
        assertEquals(test2.getYear(), 2014);
    }

}