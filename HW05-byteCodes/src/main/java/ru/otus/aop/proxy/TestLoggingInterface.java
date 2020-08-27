package ru.otus.aop.proxy;

public interface TestLoggingInterface {

    @Log
    void calculation(int param, int multi);
}
