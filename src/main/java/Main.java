import parser.enums.Format;
import parser.FormatParser;
import parser.impl.FormatParserFactoryImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FormatParser parser = new FormatParserFactoryImpl().create(Format.JSON);

        Student student = new Student("student", 16, "school");

        Student student2 = new Student("student2", 15, "school2");

        parser.setPrettyPrint();

        parser.serialize(student2, "new_file2.json");

        parser.changeFormat(Format.JSON);

        System.out.println("Hello world");
    }
}
