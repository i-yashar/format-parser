package parser;

import parser.enums.Format;

public interface FormatParserFactory {
    FormatParser create(Format format);
}
