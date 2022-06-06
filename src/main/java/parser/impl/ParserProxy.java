package parser.impl;

import parser.FormatParser;
import parser.enums.Format;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ParserProxy implements InvocationHandler {
    private static FormatParser parser;
    private static final JSONParser jsonParser;

    static {
        jsonParser = new JSONParser();
    }

    private ParserProxy(Object obj) {
        if (obj instanceof FormatParser) {
            parser = (FormatParser) obj;
        } else {
            throw new IllegalArgumentException("Expected args of type FormatParser but got: " + obj.getClass().getSimpleName());
        }
    }

    public static Object newInstance(Object obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new ParserProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        if (method.getName().equals("changeFormat")) {
            if (method.getParameterTypes()[0] == Format.JSON.getClass()) {
                parser = jsonParser;
            }
        }

        result = method.invoke(parser, args);
        if (parser.getClass().equals(JSONParser.class)) {

        }

        return result;
    }
}
