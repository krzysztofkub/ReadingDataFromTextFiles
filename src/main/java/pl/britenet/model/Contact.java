package pl.britenet.model;

public class Contact {

    private int id;
    private int type;
    private String contact;
    private Customer customer;

    public Contact() {
    }

    public Contact(int id, int type, String contact, Customer customer) {
        this.id = id;
        this.type = type;
        this.contact = contact;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
