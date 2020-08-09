package ru.otus;

public interface WeatherMessageBuilder {
    String buildMessage(Integer codeDescription, int degreeFahrenheit);

    String getDescription(Integer codeDescription);
}
