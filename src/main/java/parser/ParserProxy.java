package parser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ParserProxy implements InvocationHandler {
    private static final FormatParser parser;
    private Object obj;

    private ParserProxy(Object obj) {
        this.obj = obj;
    }

    public static FormatParser newInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new ParserProxy(target));
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(this.obj.getClass().equals(JSONParser.class)) {

        }
    }
}
