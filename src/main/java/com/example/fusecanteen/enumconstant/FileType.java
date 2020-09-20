package com.example.fusecanteen.enumconstant;

import java.util.Arrays;
import java.util.List;

public enum FileType {

    Banners("Banners") ,Notice("Notice") ,Gallery("Gallery"),ImageAlbum("ImageAlbum"), News("News"), Campaign("Campaign"),
    Team("Team"), AboutUs("AboutUs"), ComplainFile("ComplainFile"), User("User"), Video("Video");

    private final String value;

    FileType(String value) {

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }


    public static FileType getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (FileType v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }

    public static List<FileType> getEnumList(FileType...statuses){
        return Arrays.asList(statuses);
    }

}
