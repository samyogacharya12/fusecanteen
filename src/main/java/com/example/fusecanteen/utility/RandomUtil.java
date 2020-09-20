package com.example.fusecanteen.utility;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private static final int DEF_COUNT = 4; // length of generated code
    private RandomUtil() {
    }
    /**
     * Generate a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }
    /**
     * Generate an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
    /**
     * Generate a reset key.
     *
     * @return the generated reset key
     */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
    public static String generateRandomWithTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd-hh-mm-ss-")).concat("" + getRandomFourDigitNo());
    }
    public static int getRandomFourDigitNo() {
        return ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
    }


}
