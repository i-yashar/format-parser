package parser;

public class FormatParserFactoryImpl implements FormatParserFactory{
    private static final FormatParser formatParser;

    static {
        //formatParser = (FormatParser) Proxy.newProxyInstance(FormatParser.class.getClassLoader(), new Class[] {FormatParser.class}, new ParserProxy(null));
        formatParser = ParserProxy.newInstance();
    }
    @Override
    public FormatParser create(Format format) {
        if(format.equals(Format.JSON)) {
            formatParser.changeFormat(Format.JSON);
            return formatParser;
        }

        return null;
    }
}