package com.example.fusecanteen.enumconstant;

import java.util.Arrays;
import java.util.List;

public enum FileLocationConstant {
    FILE_BASE_PATH("E://wofowon//");

    private final String value;

    FileLocationConstant(String value) {
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }


    public static FileLocationConstant getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (FileLocationConstant v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }

    public static List<FileLocationConstant> getEnumList(FileLocationConstant...statuses){
        return Arrays.asList(statuses);
    }
}
