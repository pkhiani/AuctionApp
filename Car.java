package model;

// Represents a Car with different attributes
public class Car {

    private String brand;
    private int year;
    private String model;
    private int mileage;
    private int bidPrice;

    // Creates a new car with the following arguments
    public Car(int year, String brand, String model, int mileage, int bidPrice) {
        this.brand = brand;
        this.year = year;
        this.model = model;
        this.mileage = mileage;
        this.bidPrice = bidPrice;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

}
