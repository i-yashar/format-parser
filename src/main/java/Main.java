import parser.Format;
import parser.FormatParser;
import parser.FormatParserFactoryImpl;
import parser.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FormatParser parser = new FormatParserFactoryImpl().create(Format.JSON);

        Student student = new Student("student", 16, "school");

        parser.setPrettyPrint();

        parser.serialize(student, "new_file.json");

        System.out.println("Hello world");
    }
}
