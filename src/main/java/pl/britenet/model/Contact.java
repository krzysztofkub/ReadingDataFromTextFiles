package pl.britenet.model;

public class Contact {

    private int type;
    private String contact;

    public Contact() {
    }

    public Contact(int type, String contact) {
        this.type = type;
        this.contact = contact;
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
}
