package ru.otus.generics;

import ru.otus.generics.entries.*;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String... args) {
        //useArrayList();
        DIYArrayList<Car> diyList= new DIYArrayList<>();
        diyList = fillDIYArrayListData(diyList);
        diyList.set(1, new Bus("MAZ", 2020, 20));
        Car newCar1 = new PassengerCar("Volkswagen", 2019);
        Car newCar2 = new PassengerCar("Kia", 2020);
        Collections.addAll(diyList, newCar1, newCar2);
        printDIYArrayList(diyList);
        System.out.println("\n" + diyList.remove(40)
                + " size: " + diyList.size() + "\n");
        diyList.add(40, newCar1);
        Collections.sort(diyList, new Sorter());
        printDIYArrayList(diyList);
        DIYArrayList<Car> diyCarPark= new DIYArrayList<>(diyList);
        Collections.copy(diyCarPark, diyList);
        System.out.println("size: " + diyCarPark.size());
        printDIYArrayList(diyCarPark);
    }

    public static void printDIYArrayList(DIYArrayList<Car> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " " + list.get(i));
        }
    }

    public static DIYArrayList<Car> fillDIYArrayListData(DIYArrayList<Car> list) {
        list.add(new PassengerCar("JAC", 2018));
        list.add(new PassengerCar("Citroen", 2005));
        list.add(new PassengerCar("Citroen", 2015));
        list.add(new PassengerCar("Chery", 2016));
        list.add(new PassengerCar("Saab", 2017));
        list.add(new PassengerCar("Fiat", 2018));
        list.add(new PassengerCar("Mazda", 2019));
        list.add(new PassengerCar("Lada", 2019));
        list.add(new PassengerCar("Skoda", 2019));
        list.add(new PassengerCar("Audi", 2018));
        list.add(new PassengerCar("Toyota", 2004));
        list.add(new PassengerCar("Ford", 2000));
        list.add(new PassengerCar("UAZ", 2019));
        list.add(new PassengerCar("Fiat", 2016));
        list.add(new PassengerCar("Peugeot", 2006));
        list.add(new PassengerCar("Aurus", 2020));
        list.add(new PassengerCar("Volvo", 2000));
        list.add(new PassengerCar("Gaz", 2018));
        list.add(new PassengerCar("Cadillac", 2006));
        list.add(new PassengerCar("Moskvich", 2001));
        list.add(new PassengerCar("TagAZ", 2013));
        list.add(new PassengerCar("Foton", 2012));
        list.add(new PassengerCar("Chery", 2011));
        list.add(new PassengerCar("Subaru", 2009));
        list.add(new PassengerCar("BMW", 2003));
        list.add(new PassengerCar("Suzuki", 2004));
        list.add(new PassengerCar("Daewoo", 2017));
        list.add(new PassengerCar("Opel", 2014));
        list.add(new PassengerCar("FAW", 2017));
        list.add(new PassengerCar("GAC", 2017));
        list.add(new PassengerCar("Haval", 2017));
        list.add(new PassengerCar("Saab", 2017));
        list.add(new PassengerCar("SEAT", 2017));
        list.add(new PassengerCar("Citroen", 2005));
        list.add(new PassengerCar("Genesis", 2008));
        list.add(new PassengerCar("Chryslerr", 2007));
        list.add(new PassengerCar("Jaguar", 2015));
        list.add(new PassengerCar("Smart", 2016));
        list.add(new PassengerCar("Brilliance", 2017));
        list.add(new PassengerCar("JAC", 2018));
        return list;
    }

    public static void useArrayList() {
        ArrayList<Car> list= new ArrayList<>();
        list = fillArrayListData(list);
        list.set(1, new Bus("MAZ", 2020, 20));
        Car newCar1 = new PassengerCar("Volkswagen", 2019);
        Car newCar2 = new PassengerCar("Kia", 2020);
        Collections.addAll(list, newCar1, newCar2);
        printArrayList(list);
        System.out.println("\n" + list.remove(40)
                + " size: " + list.size() + "\n");
        list.add(40, newCar1);
        Collections.sort(list, new Sorter());
        printArrayList(list);
        ArrayList<Car> carPark= new ArrayList<>(list);
        Collections.copy(carPark, list);
        System.out.println("size: " + carPark.size());
        printArrayList(carPark);
    }

    public static void printArrayList(ArrayList<Car> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " " + list.get(i));
        }
    }

    public static ArrayList<Car> fillArrayListData(ArrayList<Car> list) {
        list.add(new PassengerCar("JAC", 2018));
        list.add(new PassengerCar("Citroen", 2005));
        list.add(new PassengerCar("Citroen", 2015));
        list.add(new PassengerCar("Chery", 2016));
        list.add(new PassengerCar("Saab", 2017));
        list.add(new PassengerCar("Fiat", 2018));
        list.add(new PassengerCar("Mazda", 2019));
        list.add(new PassengerCar("Lada", 2019));
        list.add(new PassengerCar("Skoda", 2019));
        list.add(new PassengerCar("Audi", 2018));
        list.add(new PassengerCar("Toyota", 2004));
        list.add(new PassengerCar("Ford", 2000));
        list.add(new PassengerCar("UAZ", 2019));
        list.add(new PassengerCar("Fiat", 2016));
        list.add(new PassengerCar("Peugeot", 2006));
        list.add(new PassengerCar("Aurus", 2020));
        list.add(new PassengerCar("Volvo", 2000));
        list.add(new PassengerCar("Gaz", 2018));
        list.add(new PassengerCar("Cadillac", 2006));
        list.add(new PassengerCar("Moskvich", 2001));
        list.add(new PassengerCar("TagAZ", 2013));
        list.add(new PassengerCar("Foton", 2012));
        list.add(new PassengerCar("Chery", 2011));
        list.add(new PassengerCar("Subaru", 2009));
        list.add(new PassengerCar("BMW", 2003));
        list.add(new PassengerCar("Suzuki", 2004));
        list.add(new PassengerCar("Daewoo", 2017));
        list.add(new PassengerCar("Opel", 2014));
        list.add(new PassengerCar("FAW", 2017));
        list.add(new PassengerCar("GAC", 2017));
        list.add(new PassengerCar("Haval", 2017));
        list.add(new PassengerCar("Saab", 2017));
        list.add(new PassengerCar("SEAT", 2017));
        list.add(new PassengerCar("Citroen", 2005));
        list.add(new PassengerCar("Genesis", 2008));
        list.add(new PassengerCar("Chryslerr", 2007));
        list.add(new PassengerCar("Jaguar", 2015));
        list.add(new PassengerCar("Smart", 2016));
        list.add(new PassengerCar("Brilliance", 2017));
        list.add(new PassengerCar("JAC", 2018));
        return list;
    }
}
