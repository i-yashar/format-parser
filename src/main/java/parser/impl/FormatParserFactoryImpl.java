package parser.impl;

import parser.FormatParser;
import parser.FormatParserFactory;
import parser.enums.Format;

import javax.swing.text.html.parser.Parser;
import java.lang.reflect.Proxy;

public class FormatParserFactoryImpl implements FormatParserFactory {

    @Override
    public FormatParser create(Format format) {
        if(format.equals(Format.JSON)) {
            return (FormatParser) ParserProxy.newInstance(new JSONParser());
        }

        return null;
    }
}