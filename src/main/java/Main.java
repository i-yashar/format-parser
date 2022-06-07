import parser.enums.Format;
import parser.FormatParser;
import parser.impl.FormatParserFactoryImpl;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FormatParser parser = new FormatParserFactoryImpl().create(Format.JSON);

        Student student = new Student("student", 16, "school");

        Student student2 = new Student("student2", 15, "school2");

        parser.setPrettyPrint();

        parser.serialize(student2, "new_file2.json");
        System.out.println(parser.serialize(student));

        parser.changeFormat(Format.JSON);

        parser.changeFormat(Format.XML);

        parser.setPrettyPrint();

        parser.serialize(student, "new_file3.xml");

        parser.changeFormat(Format.JSON);

        Student st = parser.deserialize("new_file2.json", Student.class);

        parser.changeFormat(Format.XML);

        Student st1 = parser.deserialize("new_file3.xml", Student.class);

    }
}
