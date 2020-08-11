package ru.otus;

import ru.otus.annotations.*;
import static ru.otus.ApprovalStatements.*;

public class WeatherMessageBuilderImplTest {

    public static final String TEMPLATE = "Добрый день!\n Завтра будет %s \n Температура %d гр. Цельсия";
    public static final Integer CODE_DESCRIPTION = 1;
    public static final String EXPECTED_DESCRIPTION = "ясно";
    public static final int DEGREE_FAHRENHEIT = 70;
    public static final int EXPECTED_DEGREE_CELSIUS = 21;

    private WeatherMessageBuilderImpl weatherMessageBuilderImpl;

    @Before
    public void setUp() {
        weatherMessageBuilderImpl = new WeatherMessageBuilderImpl();
    }

    @Test
    public void shouldBuildCorrectMessageForTemplateByDescriptionAndDegree() {
        String actualMessage = weatherMessageBuilderImpl.buildMessage(CODE_DESCRIPTION, DEGREE_FAHRENHEIT);
        approvalEquals(String.format(TEMPLATE, EXPECTED_DESCRIPTION, EXPECTED_DEGREE_CELSIUS), actualMessage);
    }

    @Test
    public void shouldReturnNotNullDescription() {
        approvalNotNull(weatherMessageBuilderImpl.getDescription(CODE_DESCRIPTION));
    }

    @Test
    public void shouldReturnCorrectDescription() {
        String actualDescription = weatherMessageBuilderImpl.getDescription(CODE_DESCRIPTION);
        approvalEquals(EXPECTED_DESCRIPTION, actualDescription);
    }

    @Test
    public void shouldReturnCorrectConvertedDegreeFromFahrenheitToCelsius() {
        int actualDegreeCelsius = weatherMessageBuilderImpl.convertFromFahrenheitToCelsius(DEGREE_FAHRENHEIT);
        approvalEquals(EXPECTED_DEGREE_CELSIUS, actualDegreeCelsius);
    }

    @After
    public void tearDown() {
        System.out.println("tearDown executed");
    }
}
