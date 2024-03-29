package com.example.fusecanteen.enumconstant;


public enum ResponseStatus {

    SUCCESS("Success", "200"),

    PENDING("Pending", "P00"),

    AMBIGUOUS("Ambiguous", "403"),

    FAILURE("Failure", "F00"),

    INTERNAL_SERVER_ERROR("Internal Server Error", "500"),

    INVALID_SESSION("Session Invalid", "F03"),

    BAD_REQUEST("Bad Request", "400"),

    VALIDATION_FAILED("Validation Fail", "F06"),

    UNAUTHORIZED_USER("Un-Authorized User", "F05"),

    CHANGE_TO_PUBLIC("Change to Public", "COP"),

    CHANGE_TO_SECRET("Change to Secret", "COS"),

    RESOURCE_NOT_FOUND("Resource not found", "404"),

    ALREADY_USED("Code Already Used", "400");


    private ResponseStatus() {

    }

    private String key;

    private String value;

    private ResponseStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static ResponseStatus getEnumByValue(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (ResponseStatus v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }

    public static ResponseStatus getEnumByKey(String key) {
        if (key == null)
            throw new IllegalArgumentException();
        for (ResponseStatus v : values())
            if (key.equalsIgnoreCase(v.getKey()))
                return v;
        throw new IllegalArgumentException();
    }

}
