package ru.otus.aop.proxy;

public class TestLoggingImpl implements TestLoggingInterface {

    @Override
    public void calculation(int param, int multi) {
        System.out.println("calculation, result: " + param * multi);
    }

    @Override
    public String toString() {
        return "TestLoggingImpl{}";
    }
}
