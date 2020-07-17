package ru.otus.generics.entries;

public class Car extends Transport {
    protected final String brand;
    protected final int year;

    public Car (String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car: brand:" + brand + ", year:" + year;
    }

    public void move() {

    }

    public String getBrand() {
        return brand;
    }

    public Integer getYear() {
        return year;
    }

}
