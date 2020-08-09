package ru.otus;

import ru.otus.annotations.Before;

public class Tests {

    public static final String TEMPLATE = "Добрый день!\n Завтра будет %s \n Температура %d гр. Цельсия";
    private static final int DEGREE_FAHRENHEIT = 70;


    private WeatherMessageBuilderImpl messageBuilder;

    @Before
    void setUp() {
        messageBuilder = new WeatherMessageBuilderImpl();
    }

    buildMessage

}
