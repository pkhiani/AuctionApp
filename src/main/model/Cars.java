package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;

// Represents a list of Cars and a few commands that can be done to the ArrayList
public class Cars implements Saveable {

    private final ArrayList<Car> listOfCars;
    private Car temp;

    // Creates a new ArrayList in the constructor
    public Cars() {
        listOfCars = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: adds a Car object to the list
    public void addCar(Car car) {

        listOfCars.add(car);
    }

    // EFFECTS: returns the size of the list
    public int getNumberOfCarsInList() {
        return listOfCars.size();
    }

    // EFFECTS: lists all the cars in list and their respective details
    public String getListings() {
        String listings = "";
        for (int i = 0; i < getNumberOfCarsInList(); i++) {
            listings += "Car #" + i + "\n" + listOfCars.get(i).getYear()
                    + "\n" + listOfCars.get(i).getBrand() + "\n"
                    + listOfCars.get(i).getModel() + "\n" + listOfCars.get(i).getMileage() + " KMS" + "\n"
                    + "The starting bid of the car is $" + listOfCars.get(i).getBidPrice() + "\n" + "\n";
        }
        return listings;
    }

    // EFFECTS: returns true if the given car is in list,
    //          else return false
    public boolean containsCar(Car car) {
        return listOfCars.contains(car);
    }

    // EFFECTS: returns false and removes the given car if it is in list,
    //          else return true
    public boolean removeCar(Car car) {
        if (containsCar(car)) {
            listOfCars.remove(car);
            return false;
        } else {
            return true;
        }
    }

    // EFFECTS: returns the car at a given point in the list
    public Car getCar(int x) throws IndexOutOfBoundsException {
        if (listOfCars.get(x) == null) {
            throw new IndexOutOfBoundsException("Cars has not been initialized");
        }
        return temp = listOfCars.get(x);
    }

    // EFFECTS: Prints all the components of the plan to a txt file
    // inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    @Override
    public void save(PrintWriter printWriter) {

        for (Car c : listOfCars) {
            printWriter.print(c.getYear());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(c.getBrand());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(c.getModel());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(c.getMileage());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(c.getBidPrice());
            printWriter.print("\n");
        }
    }
}
