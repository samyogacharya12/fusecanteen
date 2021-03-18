package com.example.fusecanteen.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    public static boolean isBlank(String data) {
        return org.apache.commons.lang3.StringUtils.isBlank(data);
    }

    public static boolean isValidEmail(String email) {
        if (isBlank(email)) {
            return false;
        } else {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            return matcher.matches();
        }
    }

}
