package parser;

import java.io.File;
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
        if(obj instanceof FormatParser) {
            parser = (FormatParser) obj;
        }
    }

    public static FormatParser newInstance() {
        return jsonParser;
    }
    public static FormatParser newInstance(Object obj) {
        return parser = (FormatParser) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                new Class[] {FormatParser.class},
                new ParserProxy(obj));
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        if(method.getName().equals("changeFormat")) {
            if(method.getParameterTypes()[0] == Format.JSON.getClass()) {
                parser = jsonParser;
            }
        }

        result = method.invoke(parser, args);
        if(parser.getClass().equals(JSONParser.class)) {

        }

        return result;
    }
}
