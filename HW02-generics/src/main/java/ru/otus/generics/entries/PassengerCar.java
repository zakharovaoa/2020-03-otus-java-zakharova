package ru.otus.generics.entries;

public class PassengerCar extends Car {

    public PassengerCar (String brand, int year) {
        super(brand, year);
    }

    @Override
    public String toString() {
        return "PassengerCar: brand:" + brand + ", year:" + year;
    }

}
