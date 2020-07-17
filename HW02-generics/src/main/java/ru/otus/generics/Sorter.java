package ru.otus.generics;

import java.util.Comparator;
import ru.otus.generics.entries.*;

public class Sorter implements Comparator<Car>{

    @Override
    public int compare(Car c1, Car c2){
        int result = c2.getYear().compareTo(c1.getYear());
        if (result == 0) {
            result = c1.getBrand().compareTo(c2.getBrand());
        }
        return result;
    }

}
