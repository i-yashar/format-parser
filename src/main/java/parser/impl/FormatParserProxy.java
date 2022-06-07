package parser.impl;

import parser.FormatParser;
import parser.enums.Format;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormatParserProxy implements InvocationHandler {
    private static FormatParser ctx;
    private static final Map<String, Object> PARSERS = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger("FormatParserProxy");

    private FormatParserProxy(Object obj) {
        if (obj instanceof FormatParser) {
            ctx = (FormatParser) obj;
        } else {
            throw new IllegalArgumentException("Expected args of type FormatParser but got: " + obj.getClass().getSimpleName());
        }
    }

    public static Object newInstance(String str) {
        PARSERS.put(str, str.equals("json") ? new JSONParser() : new XMLParser());

        return Proxy.newProxyInstance(
                PARSERS.get(str).getClass().getClassLoader(),
                PARSERS.get(str).getClass().getInterfaces(),
                new FormatParserProxy(PARSERS.get(str)));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Object result = null;

        sb.append("Method call to ").append(method.getName()).append(System.lineSeparator());
        String action = "Action: ";

        if (method.getName().equals("changeFormat")) {
            action += "Change format from " + (ctx instanceof JSONParser ? "JSON" : "XML") + " to " + (args[0].equals(Format.JSON) ? "JSON" : "XML");
            if (args[0].equals(Format.JSON)) {
                PARSERS.putIfAbsent("json", new JSONParser());
                ctx = (FormatParser) PARSERS.get("json");
            } else if(args[0].equals(Format.XML)) {
                PARSERS.putIfAbsent("xml", new XMLParser());
                ctx = (FormatParser) PARSERS.get("xml");
            }
        } else if(method.getName().equals("setPrettyPrint")) {
            action += "Set pretty print";
        } else if(method.getName().equals("serialize") && args.length > 1) {
            String fileName = (args[1] instanceof String) ? args[1].toString() : ((File) args[1]).getName();
            action += "Serialize object of type " + args[0].getClass().getName() + " to file " + fileName;
        } else if(method.getName().equals("deserialize")) {
            String fileName = (args[0] instanceof String) ? args[0].toString() : ((File) args[0]).getName();
            action += "Deserialize contents of file " + fileName + " to object of type " + ((Class<?>)args[1]).getSimpleName();
        }

        sb.append(action).append(System.lineSeparator());

        LOGGER.log(Level.INFO, sb.toString());

        result = method.invoke(ctx, args);

        return result;
    }
}
