package pl.britenet.model;

import java.util.List;

public class Customer {

    private String name;
    private String surname;
    private int age;
    private List<Contact> contact;

    public Customer() {
    }

    public Customer(String name, String surname, int age, List<Contact> contact) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }
}
