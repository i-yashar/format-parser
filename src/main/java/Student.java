import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class Student {

    @Expose
    @XmlElement
    private String name;

    @Expose
    @XmlElement
    private int age;

    @Expose
    @XmlElement
    private String school;

    public Student() {
    }

    public Student(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }
}
