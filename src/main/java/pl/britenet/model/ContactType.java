package pl.britenet.model;

public enum ContactType {
    UNKNOWN(0), EMAIL(1), PHONE(2), JABBER(3);
    private final int type;

    ContactType(int type) {
        this.type = type;
    }

    public int toInt() {
        return type;
    }
}
