package ru.otus.gc.bench;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

class Benchmark implements BenchmarkMBean {
  private final int loopCounter;
  private volatile int size = 0;

  public Benchmark(int loopCounter) {
    this.loopCounter = loopCounter;
  }

  void run() throws InterruptedException {
    List<Object> list = new ArrayList<>();
    long beginTime = System.currentTimeMillis();
    for (int idx = 0; idx < loopCounter; idx++) {
      System.out.println(idx);
      int delta = 100;
      int local = size + idx * delta;
      for (int i = 0; i < local; i++) {
        Object[] array = new Object[52];
        for (int j = 0; j < array.length; j++) {
          array[j] = new String(new char[0]);
        }
        list.add(array);
      }
      for (int i = 0; i < list.size()/2 - idx * delta; i++) {
        list.remove(i);
      }
      System.out.println("time process loop " + idx + ": " + (System.currentTimeMillis() - beginTime) / 1000);
    }
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public void setSize(int size) {
    System.out.println("new size:" + size);
    this.size = size;
  }
}
