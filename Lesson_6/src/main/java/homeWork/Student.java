package homeWork;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Cloneable{

    private String name;
    private String country;
    private String group;
    private int age;
    private double score;
}