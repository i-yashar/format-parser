package parser;

import java.io.File;

public interface FormatParser {

    String serialize(Object object);

    void serialize(Object object, String fileName);

    void serialize(Object object, File file);

    //<T> T deserialize(String content, Class<T> type);

    <T> T deserialize(String fileName, Class<T> type);

    <T> T deserialize(File file, Class<T> type);

    void setPrettyPrint();

    void changeFormat(Format format);



}
