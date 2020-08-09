package ru.otus;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String... args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        new Tester().doTest("ru.otus.WeatherMessageBuilderImplTest");
    }
}
