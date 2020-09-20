package com.example.fusecanteen.enumconstant;

public enum AuthorityType {

    EMPLOYEE("EMPLOYEE"), ADMIN("ADMIN");

    private final String value;

    AuthorityType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }


    public static AuthorityType getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (AuthorityType v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }


}
