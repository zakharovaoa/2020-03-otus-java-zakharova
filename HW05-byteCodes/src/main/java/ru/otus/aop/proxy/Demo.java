package ru.otus.aop.proxy;

public class Demo {
    public static void action() {
        TestLoggingInterface myClass = Ioc.createMyClass();
        myClass.calculation(6, 2);
    }

    public static void main(String... args) {
        action();
    }
}
