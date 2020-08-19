package ru.otus.gc.bench;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GcCompare {
  static int countYoungCollector = 0;
  static long sumYoungDuration = 0;
  static int countOldCollector = 0;
  static long sumOldDuration = 0;

  public static void main(String... args) throws Exception {
    System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
    switchOnMonitoring();
    long beginTime = System.currentTimeMillis();
    System.out.println(beginTime);
    int size = 500 * 1000;
    int loopCounter = 1000;
    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    ObjectName name = new ObjectName("ru.otus:type=Benchmark");

    ru.otus.gc.bench.Benchmark mbean = new ru.otus.gc.bench.Benchmark(loopCounter);
    mbs.registerMBean(mbean, name);
    mbean.setSize(size);
    mbean.run();
    System.out.println("time:" + (System.currentTimeMillis() - beginTime) / 1000);
    System.out.println("countYoungCollector: " + countYoungCollector + " sumYoungDuration: " + sumYoungDuration + "(ms)"
                     + "countOldCollector: " + countOldCollector + " sumOldDuration: " + sumOldDuration + "(ms)");
  }

  private static void switchOnMonitoring() {
    List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();
    for (GarbageCollectorMXBean gcbean : gcbeans) {
      System.out.println("GC name:" + gcbean.getName());
      NotificationEmitter emitter = (NotificationEmitter) gcbean;
      NotificationListener listener = (notification, handback) -> {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
          GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
          String gcName = info.getGcName();
          String gcAction = info.getGcAction();
          String gcCause = info.getGcCause();

          long startTime = info.getGcInfo().getStartTime();
          long duration = info.getGcInfo().getDuration();

          System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
          if (gcAction.equals("end of minor GC")) {
            countYoungCollector += 1;
            sumYoungDuration += duration;
          } else {
            countOldCollector += 1;
            sumOldDuration += duration;
          }
          System.out.println("countYoungCollector: " + countYoungCollector + " sumYoungDuration: " + sumYoungDuration + "(ms)"
                  + "countOldCollector: " + countOldCollector + " sumOldDuration: " + sumOldDuration + "(ms)");
        }
      };
      emitter.addNotificationListener(listener, null, null);
    }
  }

}
