package parser.impl;

import parser.FormatParser;
import parser.FormatParserFactory;
import parser.enums.Format;

public class FormatParserFactoryImpl implements FormatParserFactory {

    @Override
    public FormatParser create(Format format) {

        if(format.equals(Format.JSON)) {
            return (FormatParser) FormatParserProxy.newInstance("json");
        } else if(format.equals(Format.XML)) {
            return (FormatParser) FormatParserProxy.newInstance("xml");
        }

        return null;
    }
}