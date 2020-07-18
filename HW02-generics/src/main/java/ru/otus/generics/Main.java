package ru.otus.generics;

import ru.otus.generics.entries.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*import ru.otus.generics.entries.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;*/

public class Main {

    public static void main(String... args) {

        ArrayList<Car> CarFleet = new ArrayList<>();
        CarFleet.add(new PassengerCar("Citroen", 2015));
        CarFleet.add(new PassengerCar("Citroen", 2016));
        CarFleet.add(new PassengerCar("Citroen", 2017));
        CarFleet.add(new PassengerCar("Citroen", 2018));
        CarFleet.add(new PassengerCar("Mazda", 2019));
        CarFleet.add(new PassengerCar("Lada", 2019));
        CarFleet.add(new PassengerCar("Skoda", 2019));
        CarFleet.add(new PassengerCar("Audi", 2018));
        CarFleet.add(new PassengerCar("Toyota", 2004));
        CarFleet.add(new PassengerCar("Ford", 2000));
        CarFleet.add(new PassengerCar("УАЗ", 2019));
        CarFleet.add(new PassengerCar("Fiat", 2016));
        CarFleet.add(new PassengerCar("Peugeot", 2006));
        CarFleet.add(new PassengerCar("Aurus", 2020));
        CarFleet.add(new PassengerCar("Volvo", 2000));
        CarFleet.add(new PassengerCar("ГАЗ", 2018));
        CarFleet.add(new PassengerCar("Cadillac", 2006));
        CarFleet.add(new PassengerCar("Москвич", 2001));
        CarFleet.add(new PassengerCar("ТагАЗ", 2013));
        CarFleet.add(new PassengerCar("Foton", 2012));
        CarFleet.add(new PassengerCar("Chery", 2011));
        CarFleet.add(new PassengerCar("Subaru", 2009));
        CarFleet.add(new PassengerCar("BMW", 2003));
        CarFleet.add(new PassengerCar("Suzuki", 2004));
        CarFleet.add(new PassengerCar("Daewoo", 2017));
        CarFleet.add(new PassengerCar("Opel", 2014));
        CarFleet.add(new PassengerCar("FAW", 2017));
        CarFleet.add(new PassengerCar("GAC", 2017));
        CarFleet.add(new PassengerCar("Haval", 2017));
        CarFleet.add(new PassengerCar("Saab", 2017));
        CarFleet.add(new PassengerCar("SEAT", 2017));
        CarFleet.add(new PassengerCar("Citroen", 2005));
        CarFleet.add(new PassengerCar("Genesis", 2008));
        CarFleet.add(new PassengerCar("Chryslerr", 2007));

        System.out.println("CarFleet:" + CarFleet);

        Car newCar1 = new PassengerCar("Volkswagen", 2015);
        Car newCar2 = new PassengerCar("Kia", 2016);
        Collections.addAll(CarFleet, newCar1, newCar2);
        System.out.println("CarFleet:" + CarFleet);

        List<Car> CarPark = new ArrayList<>(CarFleet);
        //List<Car> CarPark = new ArrayList<>();
        //CarPark = CarFleet;
        //CarPark = CarFleet;
        //System.out.println("CarPark:" + CarPark);

        List<Car> newCars = new ArrayList<>();

        newCars.add(new PassengerCar("Jaguar", 2015));
        newCars.add(new PassengerCar("Smart", 2016));
        newCars.add(new PassengerCar("Brilliance", 2017));
        newCars.add(new PassengerCar("JAC", 2018));

        Collections.copy(CarPark, newCars);
        System.out.println("CarPark:" + CarPark);
        Collections.sort(CarPark, new Sorter());
        System.out.println("CarPark:");
        CarPark.forEach(System.out::println);

        DIYArrayList<Car> diyList= new DIYArrayList<>();
        diyList.add(new PassengerCar("JAC", 2018));
        diyList.add(new PassengerCar("Citroen", 2005));
        //diyList.set(1, new PassengerCar("Jaguar", 2015));
        //diyList.forEach(System.out::println);
        diyList.add(new PassengerCar("Citroen", 2015));
        diyList.add(new PassengerCar("Citroen", 2016));
        diyList.add(new PassengerCar("Citroen", 2017));
        diyList.add(new PassengerCar("Citroen", 2018));
        diyList.add(new PassengerCar("Mazda", 2019));
        diyList.add(new PassengerCar("Lada", 2019));
        diyList.add(new PassengerCar("Skoda", 2019));
        diyList.add(new PassengerCar("Audi", 2018));
        diyList.add(new PassengerCar("Toyota", 2004));
        diyList.add(new PassengerCar("Ford", 2000));
        diyList.add(new PassengerCar("УАЗ", 2019));
        diyList.add(new PassengerCar("Fiat", 2016));
        diyList.add(new PassengerCar("Peugeot", 2006));
        diyList.add(new PassengerCar("Aurus", 2020));
        diyList.add(new PassengerCar("Volvo", 2000));
        diyList.add(new PassengerCar("ГАЗ", 2018));
        diyList.add(new PassengerCar("Cadillac", 2006));
        diyList.add(new PassengerCar("Москвич", 2001));
        diyList.add(new PassengerCar("ТагАЗ", 2013));
        diyList.add(new PassengerCar("Foton", 2012));
        diyList.add(new PassengerCar("Chery", 2011));
        diyList.add(new PassengerCar("Subaru", 2009));
        diyList.add(new PassengerCar("BMW", 2003));
        diyList.add(new PassengerCar("Suzuki", 2004));
        diyList.add(new PassengerCar("Daewoo", 2017));
        diyList.add(new PassengerCar("Opel", 2014));
        diyList.add(new PassengerCar("FAW", 2017));
        diyList.add(new PassengerCar("GAC", 2017));
        diyList.add(new PassengerCar("Haval", 2017));
        diyList.add(new PassengerCar("Saab", 2017));
        diyList.add(new PassengerCar("SEAT", 2017));
        diyList.add(new PassengerCar("Citroen", 2005));
        diyList.add(new PassengerCar("Genesis", 2008));
        diyList.add(new PassengerCar("Chryslerr", 2007));


        diyList.add(new PassengerCar("Jaguar", 2015));
        diyList.add(new PassengerCar("Smart", 2016));
        diyList.add(new PassengerCar("Brilliance", 2017));
        diyList.add(new PassengerCar("JAC", 2018));

        System.out.println(diyList.size());
        Car newCar3 = new PassengerCar("Volkswagen", 2015);
        Car newCar4 = new PassengerCar("Kia", 2016);
        Collections.addAll(diyList, newCar3, newCar4);
        System.out.println(diyList.size());
        Collections.sort(diyList, new Sorter());
        DIYArrayList<Car> diyCarPark= new DIYArrayList<>();
        Collections.copy(diyList, diyCarPark);
        System.out.println(diyCarPark.size());
        System.out.println(diyCarPark.get(42));
        //    System.out.println(MEASURE_COUNT);
        //Car[] cars = diyList.toArray(new Car[0]);

        /*int i = (int) (11 * 1.5);
        System.out.println(i);*/

    }


}
