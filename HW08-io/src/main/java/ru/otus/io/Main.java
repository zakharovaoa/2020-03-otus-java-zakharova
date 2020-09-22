package ru.otus.io;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws Exception {
        int[] array = getArray();
        Collection<String> list = getCollection();

        Gson gson = new Gson();
        AnyObject obj = new AnyObject('A', true, array, list);
        System.out.println("objForGson: " + obj);
        String json = gson.toJson(obj);
        System.out.println("jsonFromGson: " + json);

        MyGson myGson = new MyGson();
        AnyObject obj1 = new AnyObject('A', true, array, list);
        System.out.println("objForMyGson: " + obj1);
        String myJson = myGson.toJson(obj1);
        System.out.println("jsonFromMyGson: " + myJson);

        AnyObject obj2 = gson.fromJson(myJson, AnyObject.class);
        System.out.println(obj.equals(obj2));
    }

    public static Collection<String> getCollection() {
        Collection<String> list = new ArrayList<>(Arrays.asList("Металл", "Дерево", "Железобетон", "Стекло"));
        return list;
    }

    public static int[] getArray() {
        int[] array = new int[] {3, 5, 7, 9};
        return array;
    }
}
