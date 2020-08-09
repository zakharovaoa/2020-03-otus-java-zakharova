package ru.otus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class WeatherMessageBuilderImpl implements WeatherMessageBuilder{

    public static final String TEMPLATE = "Добрый день!\n Завтра будет %s \n Температура %d гр. Цельсия";
    private final HashMap<Integer, String> mapDescription;

    public WeatherMessageBuilderImpl() {
        this.mapDescription = new HashMap<>();
        mapDescription.put(1, "ясно");
        mapDescription.put(2, "пасмурно");
        mapDescription.put(3, "дождь");
        mapDescription.put(4, "снег");
    }

    @Override
    public String buildMessage(Integer codeDescription, int degreeFahrenheit) {
        int degreeCelsius = convertFromFahrenheitToCelsius(degreeFahrenheit);
        String description = getDescription(codeDescription);
        if (description == null || description.isEmpty()) {
            throw new TemplateNotFoundException();
        }
        return String.format(TEMPLATE, description, degreeCelsius);
    }

    @Override
    public String getDescription(Integer codeDescription){
        return mapDescription.get(codeDescription);
    };

    private static int convertFromFahrenheitToCelsius(int degreeFahrenheit) {
        return (int) (5/9.0 * (degreeFahrenheit - 32));
    }





}
