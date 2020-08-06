package ru.otus.generics.entries;

public class Bus extends Car {
    private int countPassenger;

    public Bus (String brand, int year, int countPassenger) {
        super(brand, year);
        this.countPassenger = countPassenger;
    }

    @Override
    public String toString() {
        return "Bus: brand:" + brand + ", year:" + year;
    }
}
