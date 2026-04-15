package com.dummyjson.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

import com.dummyjson.core.exeption.EmptyStringException;
import com.dummyjson.data.enums.Constants;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    private static void requireValidErrorMessage(final String errorMessage) throws IllegalArgumentException {
        if (isNullOrEmpty(errorMessage)) {
            throw new IllegalArgumentException("Attribute errorMessage cannot be null or empty.");
        }
    }

    public static String capitalLetter(final String string) throws IllegalArgumentException {
        if (isNullOrEmpty(string)) {
            throw new IllegalArgumentException(Constants.INVALID_STRING);
        }
        char[] str = string.toLowerCase().toCharArray();
        str[0] = Character.toUpperCase(str[0]);
        return String.valueOf(str);
    }

    public static boolean isNumeric(final String string) {
        return string != null && string.matches("[-+]?\\d*\\.?\\d+");
    }

    public static boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    public static void requireNonEmpty(final String value, final String errorMessage) throws EmptyStringException {
        StringUtils.requireValidErrorMessage(errorMessage);
        if (isNullOrEmpty(value)) {
            throw new EmptyStringException(errorMessage);
        }
    }

    public static void requireNonEmpty(final String value, final Class<? extends Exception> clazz,
                                       final String errorMessage) throws Exception {
        requireNonNull(clazz, "Attribute clazz cannot be null");
        StringUtils.requireValidErrorMessage(errorMessage);
        if (isNullOrEmpty(value)) {
            throw clazz.getConstructor(String.class).newInstance(errorMessage);
        }
    }

    public static void requireNonEmpty(final String errorMessage, final String... values) throws EmptyStringException {
        StringUtils.requireValidErrorMessage(errorMessage);
        requireNonNull(values, "Attribute values cannot be null");
        Arrays.stream(values).forEach(value -> StringUtils.requireNonEmpty(value, errorMessage));
    }

    public static void requireNumericString(final String string) throws NumberFormatException {
        if (!isNumeric(string)) {
            throw new NumberFormatException("String content is not a number");
        }
    }

    public static int[] toIntArray(final String numericString) {
        requireNonEmpty(numericString, "String cannot be null or empty");
        requireNumericString(numericString);
        int i = 0;
        char[] charArray = numericString.toCharArray();
        int[] intArray = new int[charArray.length];
        for (char c : charArray) {
            intArray[i++] = Character.getNumericValue(c);
        }
        return intArray;
    }
}
