package ui;

import model.Car;
import model.Cars;
import persistence.Reader;
import persistence.Writer;

import java.io.*;
import java.util.Scanner;

// Auction application inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class AuctionApp {

    private static final String CARLISTINGS_FILE = "./data/carlistings.txt";
    private Cars cars;
    private Scanner input;
    private boolean exitApp;
    private Car user;
    private Car car;

    // EFFECTS: runs the teller application
    public AuctionApp() {
        runAuctionApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input, inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    //          class: TellerApp, method: runTellerApp
    private void runAuctionApp() {
        exitApp = false;
        String command;
        input = new Scanner(System.in);
        System.out.println("Welcome to C-Auction!");

        loadCarListings();

        while (!exitApp) {

            displayMenu();
            command = input.next();

            processInput(command);

        }
        System.out.println("The application is closing");
    }

    // MODIFIES: this
    // EFFECTS: processes user command taken from auction app, inspired by TellerApp
    //          https://github.students.cs.ubc.ca/CPSC210/TellerApp
    //          class: TellerApp, method: processCommand()
    private void processInput(String command) {

        if (command.equals("1")) {
            displayListings();
        } else if (command.equals("2")) {
            listYourCar();
        } else if (command.equals("3")) {
            removeYourCar(user);
        } else if (command.equals("4")) {
            checkIfWon();
        } else if (command.equals("5")) {
            saveCarListings();
        } else if (command.equals("6")) {
            exitApp = true;
        } else {
            System.out.println("Invalid selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the car listings from carlistings.txt file if it exists,
    // otherwise initialize constructor as default
    private void loadCarListings() {
        try {
            cars  = Reader.readCars(new File(CARLISTINGS_FILE));
        } catch (IOException e) {
            cars = new Cars();
        }
    }

    // EFFECTS: saves state of the car listings with the user added listings
    // inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void saveCarListings() {
        try {
            Writer writer = new Writer(new File(CARLISTINGS_FILE));
            writer.write(cars);
            writer.close();
            System.out.println("Car Listings saved to file " + CARLISTINGS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Car Listings to " + CARLISTINGS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: displays menu of options to user, inspired by TellerApp
    //          https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    //          class: TellerApp, method: displayMenu()
    private void displayMenu() {
        System.out.println("\nSelect an option from below:");
        System.out.println("\t1. View current listings");
        System.out.println("\t2. List your vehicle");
        System.out.println("\t3. Remove your listing");
        System.out.println("\t4. Check if you won bid");
        System.out.println("\t5. Save listings file");
        System.out.println("\t6. Exit application");

    }

    // EFFECTS: displays current active listings on auction app
    private void displayListings() {

        //Lists all current listings
        System.out.println("Current Listings: \n" + cars.getListings());
        placeBid();
    }


    //EFFECTS: asks user if they want to place a bid or not
    private void placeBid() {
        System.out.println("Would you like to place a bid? (Y/N)");
        bidMenu();
    }


    // EFFECTS: returns true if the user's car has been listed,
    //          else returns false and prints an error message
    private void checkCarIsListed(Car user) {

        if (cars.containsCar(user)) {
            System.out.println("The car has been successfully listed");
        } else {
            System.out.println("The car is no longer listed");
        }

    }

    // MODIFIES: this
    // EFFECTS: creates a new car by asking for user input,
    //          then adds it to the list cars
    private void listYourCar() {

        int year;
        String brand;
        String model;
        int mileage;
        int bidPrice;

        System.out.println("Please enter the following details:\nYear of car:");

        year = input.nextInt();
        System.out.println("Brand of car:");
        brand = input.next();
        System.out.println("Model of car:");
        model = input.next();
        System.out.println("Mileage of car:");
        mileage = input.nextInt();
        System.out.println("Starting bid price of car");
        bidPrice = input.nextInt();

        user = createListings(year, brand, model, mileage, bidPrice);
        checkCarIsListed(user);
    }

    // REQUIRES: active user-created listing
    // MODIFIES: this
    // EFFECTS: removes active user-created listing
    private void removeYourCar(Car user) {

        cars.removeCar(user);
        checkCarIsListed(user);
    }

    // EFFECTS: processes user input if they want to bid on a vehicle
    private void bidMenu() {
        String command;
        input = new Scanner(System.in);

        command = input.next();

        if (command.equals("N")) {
            System.out.println("You are now returning to the main menu");
        }
        if (command.equals("Y")) {
            setBid();
        }
    }

    // REQUIRES: At least one Car in cars
    // MODIFIES: this
    // EFFECTS: processes user bid input
    private void setBid() {
        int bid;
        int carInList;
        Car temp;
        input = new Scanner(System.in);

        cars.getListings();
        System.out.println("Which vehicle would you like to bid on?");

        carInList = input.nextInt();
        temp = cars.getCar(carInList);

        System.out.println("How much would you like to place on the bid?");
        bid = input.nextInt();

        if (bid <= temp.getBidPrice()) {
            System.out.println("Your bid has to be higher than the current bid price");
        } else {
            temp.setBidPrice(bid);
            System.out.println("Your bid price of " + temp.getBidPrice() + " has been set.");
            System.out.println("Please check back later to see if you have won the bid");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new car when the program is initialized,
    //          then adds it to the list cars
    public Car createListings(int year, String brand, String model, int mileage, int bidPrice) {

        car = new Car(year, brand, model, mileage, bidPrice);

        cars.addCar(car);

        return car;

    }

    // MODIFIES: this
    // EFFECTS: a random number is generated. If the number is <= 1000, then print win,
    //          else, print lose
    private void checkIfWon() {

        Car temp;
        int carInList;
        input = new Scanner(System.in);

        System.out.println("Which vehicle did you bid on?");
        cars.getListings();

        double winningBid = rng(1, 5000);

        carInList = input.nextInt();
        temp = cars.getCar(carInList);

        if (winningBid <= 1000) {
            System.out.println("You have won the bid! Congratulations!");
        } else {
            System.out.println("I'm sorry, the selling price was $" + (int) winningBid + " above your bid");
        }
        removeYourCar(temp);

    }

    // EFFECTS: returns a random number between min and max
    // Code referenced from: https://dzone.com/articles/random-number-generation-in-java
    public static double rng(double min, double max) {

        return (Math.random() * ((max - min) + 1)) + min;
    }
}