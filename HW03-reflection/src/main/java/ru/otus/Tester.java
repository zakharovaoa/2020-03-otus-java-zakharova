package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class Tester {

    private static int countAll;
    private static int countSuccess;
    private static int countFail;

    public void doTest(String string) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(string);
        Method[] methodsPublic = clazz.getMethods();
        Method methodBefore = null;
        Method methodAfter = null;
        ArrayList<Method> methodsTest = new ArrayList<>();
        for (Method m : methodsPublic) {
            if (m.isAnnotationPresent(Before.class)) {
                methodBefore = m;
            } else if (m.isAnnotationPresent(After.class)) {
                methodAfter = m;
            } else if (m.isAnnotationPresent(Test.class)) {
                methodsTest.add(m);
            }
        }
        Collections.shuffle(methodsTest);
        countAll = methodsTest.size();
        for (Method m : methodsTest) {
            Constructor<?> constructor = clazz.getConstructor();
            Object object = constructor.newInstance();
            methodBefore.invoke(object);
            callMethodTest(object, m);
            methodAfter.invoke(object);
        }
        printStatisticRunTests();
    }

    public static void callMethodTest(Object object, Method method) {
        try {
           method.invoke(object);
            countSuccess += 1;
        } catch (Exception e) {
            countFail += 1;
            System.out.println(method.getName());
        }
    }

    public static void printStatisticRunTests() {
        System.out.println(String.format("Статистика выполнения тестов:\n" +
                                            "  Всего тестов: %d\n  Успешно тестов: %d\n" +
                                            "  Не успешно тестов: %d", countAll, countSuccess, countFail));
    }
}