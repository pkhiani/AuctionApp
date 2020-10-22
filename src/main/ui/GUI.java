package ui;

import model.Car;
import model.Cars;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class GUI extends JFrame implements ActionListener {

    private static final String CARLISTINGS_FILE = "./data/carlistings.txt";
    private Cars cars;
    private Car user;

    private JPanel mainMenu;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button7;
    private JButton button6;

    private JPanel carListingsPanel;
    private JLabel listings;
    private boolean listed;

    private JPanel listingsPage;
    private JButton addCar;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JLabel year;
    private JLabel brand;
    private JLabel model;
    private JLabel mileage;
    private JLabel bidPrice;
    private JLabel carListed;


    // Makes a new JFrame with different attributes
    public GUI() {
        super("Auction App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        initializeMenu();
        makeCarListingsPanel();
        makeListYourCarPanel();


        JLabel welcomeLabel = new JLabel("Welcome to C-Auction!");
        JLabel mainScreenImage = new JLabel();
        addLabel(welcomeLabel);
        addImageToLabel(mainScreenImage);

        initializeMenuButtons();

        addButtons(button1, button2, button3, button4, button5, button7, button6);

        addActionToButton();

        mainMenu.setVisible(true);
    }

    // EFFECTS: Makes the main menu panel and changes the background color
    public void initializeMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.lightGray);
        add(mainMenu);
        listings = new JLabel();
        listings.setText("No Listings available");
        //loadCarListings();
    }

    // EFFECTS: Initializes main menu buttons and gives them labels
    public void initializeMenuButtons() {
        button1 = new JButton("View current listings");
        button2 = new JButton("List your vehicle");
        button3 = new JButton("Remove your listing");
        button4 = new JButton("Check if you won bid");
        button5 = new JButton("Save listings file");
        button7 = new JButton("Load listings file");
        button6 = new JButton("Exit application");
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    public void addButton(JButton button1, JPanel panel) {
        button1.setFont(new Font("Arial", Font.BOLD, 12));
        button1.setBackground(Color.white);
        panel.add(button1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: Calls the addButton method for each argument
    public void addButtons(JButton button1, JButton button2, JButton button3, JButton button4,
                           JButton button5, JButton button6, JButton button7) {

        addButton(button1, mainMenu);
        addButton(button2, mainMenu);
        addButton(button3, mainMenu);
        addButton(button4, mainMenu);
        addButton(button5, mainMenu);
        addButton(button6, mainMenu);
        addButton(button7, mainMenu);
    }

    // EFFECTS: Creates a button and adds it to the given panel, changing various attributes of the
    //          color and text of the button
    public void addMenuButton(JButton button1, JPanel panel) {
        button1.setFont(new Font("Arial", Font.BOLD, 12));
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);
        panel.add(button1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: Creates the welcome text label and adds it to the main menu panel
    public void addLabel(JLabel j1) {
        j1.setFont(new Font("ComicSans", Font.BOLD, 50));
        mainMenu.add(j1);
    }

    // EFFECTS: Creates the image on the main menu and its it to the panel
    public void addImageToLabel(JLabel j1) {
        j1.setIcon(new ImageIcon("./data/supra1.png"));
        j1.setMinimumSize(new Dimension(20,20));
        mainMenu.add(j1);
    }

    // MODIFIES: this
    // EFFECTS: Sets each button to their respective action
    public void addActionToButton() {

        button1.addActionListener(this);
        button1.setActionCommand("View listings");
        button2.addActionListener(this);
        button2.setActionCommand("List your car");
        button3.addActionListener(this);
        button3.setActionCommand("Remove your listing");
        button4.addActionListener(this);
        button4.setActionCommand("Check if won");
        button5.addActionListener(this);
        button5.setActionCommand("Save listings file");
        button6.addActionListener(this);
        button6.setActionCommand("Exit application");
        button7.addActionListener(this);
        button7.setActionCommand("Load listings file");
    }


    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("View listings")) {
            playSound("./data/rev.wav");
            initializeCarListingsPanel();
        } else if (ae.getActionCommand().equals("List your car")) {
            playSound("./data/crash.wav");
            initializeListingsPanel();
        } else if (ae.getActionCommand().equals("Remove your listing")) {
            playSound("./data/horn.wav");
            removeListing(user);
        } else if (ae.getActionCommand().equals("Check if won")) {
            checkIfWon();
        } else if (ae.getActionCommand().equals("Save listings file")) {
            playSound("./data/cheer.wav");
            saveCarListings();
        } else if (ae.getActionCommand().equals("Exit application")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        } else if (ae.getActionCommand().equals("Add Car to listings")) {
            addCarToListings();
        } else if (ae.getActionCommand().equals("Load listings file")) {
            loadCarListings();
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates the panel that displays the option for the user to input their car
    public void makeListYourCarPanel() {

        listingsPage  = new JPanel(new GridLayout(0, 2));
        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, listingsPage);

        createListingsPage();
        addLabelsToListings();
    }

    // EFFECTS: Adds the listings page to the screen, and sets the other ones false so they are not visible to the user
    public void initializeListingsPanel() {
        add(listingsPage);
        listingsPage.setVisible(true);
        mainMenu.setVisible(false);
        carListingsPanel.setVisible(false);
        carListed.setText("car listed? " + listed);
    }

    // MODIFIES: this
    // EFFECTS: Generates the fields for the user to type into
    public void createListingsPage() {

        addCar = new JButton("Add Car to listings");
        addCar.setActionCommand("Add Car to listings");
        addCar.addActionListener(this);

        year = new JLabel("Year of car:");
        t1 = new JTextField(10);
        brand = new JLabel("Brand of car:");
        t2 = new JTextField(10);
        model = new JLabel("Model of car:");
        t3 = new JTextField(10);
        mileage = new JLabel("Mileage of car:");
        t4 = new JTextField(10);
        bidPrice = new JLabel("Starting Bid Price:");
        t5 = new JTextField(10);

        listed = false;
        carListed = new JLabel();

        listingLabelSettings();

    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabelsToListings() {

        listingsPage.add(addCar);

        listingsPage.add(year);
        listingsPage.add(t1);
        listingsPage.add(brand);
        listingsPage.add(t2);
        listingsPage.add(model);
        listingsPage.add(t3);
        listingsPage.add(mileage);
        listingsPage.add(t4);
        listingsPage.add(bidPrice);
        listingsPage.add(t5);

        listingsPage.add(carListed);
    }

    // EFFECTS: Changes certain attributes of the labels and text fields
    private void listingLabelSettings() {

        addCar.setBackground(Color.BLACK);
        addCar.setForeground(Color.WHITE);
        addCar.setFont(new Font("Arial", Font.BOLD, 12));

        year.setFont(new Font("ComicSans", Font.BOLD, 24));
        brand.setFont(new Font("ComicSans", Font.BOLD, 24));
        model.setFont(new Font("ComicSans", Font.BOLD, 24));
        mileage.setFont(new Font("ComicSans", Font.BOLD, 24));
        bidPrice.setFont(new Font("ComicSans", Font.BOLD, 24));

        t1.setMaximumSize(new Dimension(1200, 400));
        t2.setMaximumSize(new Dimension(1200, 400));
        t3.setMaximumSize(new Dimension(1200, 400));
        t4.setMaximumSize(new Dimension(1200, 400));
        t5.setMaximumSize(new Dimension(1200, 400));
    }

    // MODIFIES: this
    // EFFECTS: Adds the user given listing into the Cars object to be displayed
    public void addCarToListings() {

        try {
            user = new Car(Integer.parseInt(t1.getText()), t2.getText(), t3.getText(),
                    Integer.parseInt(t4.getText()), Integer.parseInt(t5.getText()));
            cars.addCar(user);
            // Uses HTML tags to create a multi line text label
            listings.setText("<html><pre>Current Listings: \n" + cars.getListings() + "\n</pre></html>");
            listed = true;
            carListed.setText("car listed? " + listed);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again");
        } catch (IndexOutOfBoundsException e) {
            listings.setText("Please initialize listings file before proceeding");
        }

    }

    // MODIFIES: this
    // EFFECTS: Removes user listed car from active listings, and prints message in console
    public void removeListing(Car user) {

        try {
            cars.removeCar(user);
            // Uses HTML tags to create a multi line text label
            listings.setText("<html><pre>Current Listings: \n" + cars.getListings() + "\n</pre></html>");
            System.out.println("The car is no longer listed");
            listed = false;
        } catch (NullPointerException e) {
            System.out.println("Please add a car before attempting to remove it");
        } catch (IndexOutOfBoundsException e) {
            listings.setText("Please initialize listings file before proceeding");
        }
    }

    // EFFECTS: Leaving blank as not required for Phase 3
    public void checkIfWon() {

    }

    // MODIFIES: this
    // EFFECTS: Creates the panel that displays the current listings
    public void makeCarListingsPanel() {

        JLabel image = new JLabel();
        JLabel image2 = new JLabel();
        carListingsPanel = new JPanel(new GridLayout(2, 1));
        JScrollPane scroll = new JScrollPane(listings, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);
        addMenuButton(mainMenuButton, carListingsPanel);

        image.setIcon(new ImageIcon("./data/skyline1.png"));
        image.setMinimumSize(new Dimension(10,20));
        carListingsPanel.add(image);

        image2.setIcon(new ImageIcon("./data/skyline1.png"));
        image2.setMinimumSize(new Dimension(10,20));
        carListingsPanel.add(image2);

        listings.setFont(new Font("ComicSans", Font.BOLD, 12));
        carListingsPanel.add(scroll);
    }

    // EFFECTS: Adds the carListings panel to the screen,
    // and sets the other ones false so they are not visible to the user
    public void initializeCarListingsPanel() {
        add(carListingsPanel);
        carListingsPanel.setVisible(true);
        mainMenu.setVisible(false);
        listingsPage.setVisible(false);
    }


    // MODIFIES: this
    // EFFECTS: loads the car listings from carlistings.txt file if it exists,
    // otherwise prints error
    private void loadCarListings() {

        try {
            cars  = Reader.readCars(new File(CARLISTINGS_FILE));
            listings.setText("<html><pre>Current Listings: \n" + cars.getListings() + "\n</pre></html>");
            System.out.println("Listings loaded from file " + CARLISTINGS_FILE);
        } catch (IOException e) {
            listings.setText("No Listings added yet");
        } catch (IndexOutOfBoundsException e) {
            listings.setText("Please initialize listings file before proceeding");
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
        } catch (NullPointerException e) {
            System.out.println("Please load the file before trying to save it");
        }
    }

    // EFFECTS: Plays a given sound
    //          Inspired from: https://www.programcreek.com/java-api-examples/?class=javax.sound.sampled.Clip&method=start
    public void playSound(String soundName) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(soundName));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing the sound");
        }
    }

    // EFFECTS: Sets all panels' visibility to false except for the main menu
    public void returnToMainMenu() {
        mainMenu.setVisible(true);
        carListingsPanel.setVisible(false);
        listingsPage.setVisible(false);
    }

}
