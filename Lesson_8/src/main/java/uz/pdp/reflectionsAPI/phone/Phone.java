package uz.pdp.reflectionsAPI.phone;

import lombok.ToString;

@ToString
public class Phone {
    private String name;
    private String number;

    private Phone(String name, String number) {
        this.name = name;
        this.number = number;
    }

    private String updateNums(String number) {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
