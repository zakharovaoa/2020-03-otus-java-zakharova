package ru.otus.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingImpl());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private List listMethods;

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            this.listMethods = new ArrayList<Method>();
            Class<TestLoggingInterface> clazz = TestLoggingInterface.class;
            Method[] methodsPublic = clazz.getMethods();
            for (Method m : methodsPublic) {
                if (m.isAnnotationPresent(Log.class)) {
                    this.listMethods.add(m);
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (listMethods.indexOf(method) >= 0) {
                String str = "";
                for (int i = 0; i < args.length; i++ ) {
                    if (i > 0) {
                        str += "; ";
                    }
                    str += args[i];
                }
                System.out.println("executed method: " + method.getName() + ", params: " + str);
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
