package com.devcomanda.easymeetup.utils;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {
    private RandomUtil() {
    }

    private static final int PASS_COUNT = 7;

    private static final int ACTIVATION_KEY_COUNT = 20;

    public static String generatePassword(){
        return RandomStringUtils.randomAlphanumeric(RandomUtil.PASS_COUNT);
    }

    public static String generateActivationKey(){
        return RandomStringUtils.randomAlphanumeric(RandomUtil.ACTIVATION_KEY_COUNT);
    }
}
