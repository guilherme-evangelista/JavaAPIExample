package com.dummyjson.core.utils;

import com.dummyjson.data.enums.ValidZipCode;
import com.github.javafaker.Faker;

import java.util.Locale;

public class DataUtils {

    private static final Faker FAKER = new Faker(Locale.forLanguageTag("pt-br"));

    public static String genUserName() {
        return FAKER.name().username();
    }

    public static String genMobilePhoneNumber() {
        return FAKER.phoneNumber().cellPhone();
    }

    public static String genEmail() {
        return FAKER.internet().emailAddress(genUserName());
    }

    public static String genEmail(final String prefix) {
        return FAKER.internet().emailAddress(prefix);
    }

    public static String genFirstName() {
        return FAKER.name().firstName();
    }

    public static String genLastName() {
        return FAKER.name().lastName();
    }

    public static String genFullName() {
        return FAKER.name().fullName();
    }

    public static String genCompanyName() {
        return FAKER.company().name();
    }

    public static Integer genAddressNumber() {
        return Integer.parseInt(FAKER.address().streetAddressNumber().replaceAll("\\D", ""));
    }

    public static String genZipCode() {
        return getRandomZipCodeEnum().getZipCode();
    }

    public static ValidZipCode getRandomZipCodeEnum() {
        final ValidZipCode[] values = ValidZipCode.values();
        return values[getRandomIntNumber(values.length)];
    }

    public static String genPassword(final int minLen, final int maxLen) {
        return FAKER.internet().password(minLen, maxLen, true, true, true);
    }

    public static int getRandomIntNumber(final int ceil) {
        return FAKER.random().nextInt(ceil);
    }

    public static String genCnpj(final boolean applyMask) {
        int[] digits = new int[14];
        int verifyingDigitOne, verifyingDigitTwo, summation = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            digits[i] = FAKER.random().nextInt(10);
        }
        for (int i = 0; i < DocumentUtils.CNPJ_WEIGHTS.length - 1; i++) {
            summation += digits[i] * DocumentUtils.CNPJ_WEIGHTS[i + 1];
        }
        verifyingDigitOne = DocumentUtils.calculateCNPJVerifyingDigit(summation);
        digits[12] = verifyingDigitOne;
        summation = 0;
        for (int i = 0; i < DocumentUtils.CNPJ_WEIGHTS.length; i++) {
            summation += digits[i] * DocumentUtils.CNPJ_WEIGHTS[i];
        }
        verifyingDigitTwo = DocumentUtils.calculateCNPJVerifyingDigit(summation);
        digits[13] = verifyingDigitTwo;
        for (int digit : digits) {
            stringBuilder.append(digit);
        }
        return applyMask ? DocumentUtils.applyCNPJMask(stringBuilder.toString()) : stringBuilder.toString();
    }

    public static String genCpf(final boolean applyMask) {
        int[] digits = new int[11];
        int verifyingDigitOne, verifyingDigitTwo, summation = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            digits[i] = FAKER.random().nextInt(10);
        }
        for (int i = 0; i < DocumentUtils.CPF_WEIGHTS.length - 1; i++) {
            summation += digits[i] * DocumentUtils.CPF_WEIGHTS[i + 1];
        }
        verifyingDigitOne = DocumentUtils.calculateCPFVerifyingDigit(summation);
        digits[9] = verifyingDigitOne;
        summation = 0;
        for (int i = 0; i < DocumentUtils.CPF_WEIGHTS.length; i++) {
            summation += digits[i] * DocumentUtils.CPF_WEIGHTS[i];
        }
        verifyingDigitTwo = DocumentUtils.calculateCPFVerifyingDigit(summation);
        digits[10] = verifyingDigitTwo;
        for (int digit : digits) {
            stringBuilder.append(digit);
        }
        return applyMask ? DocumentUtils.applyCPFMask(stringBuilder.toString()) : stringBuilder.toString();
    }
}
