package uz.pdp.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    private String fullName;
    private String country;
    private String jobTitle;
    private int age;
}
