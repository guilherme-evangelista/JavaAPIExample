package com.dummyjson.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.dummyjson.core.utils.StringUtils.*;

import java.util.List;

import com.dummyjson.core.exeption.InvalidDocumentException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentUtils {

    public static final int[] CPF_WEIGHTS = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    public static final int[] CNPJ_WEIGHTS = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final List<String> COMMON_INVALID_CPF_LIST = List.of(
            "00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
            "55555555555", "66666666666", "77777777777", "88888888888", "99999999999"
    );

    private static final List<String> COMMON_INVALID_CNPJ_LIST = List.of(
            "00000000000000", "11111111111111", "22222222222222", "33333333333333", "44444444444444",
            "55555555555555", "66666666666666", "77777777777777", "88888888888888", "99999999999999"
    );

    private static String removeMask(final String cpfCnpj) {
        return cpfCnpj == null ? null : cpfCnpj.replaceAll(" ", "")
                .replaceAll("\\.", "")
                .replaceAll("/", "")
                .replaceAll("-", "");
    }

    public static String applyCPFMask(final String cpf) throws InvalidDocumentException {
        if (cpf == null || cpf.length() != 11) {
            throw new InvalidDocumentException("Invalid CPF");
        }
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String applyCNPJMask(String cnpj) throws InvalidDocumentException {
        if (cnpj == null || cnpj.length() != 14) {
            throw new InvalidDocumentException("Invalid CNPJ");
        }
        return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    public static int calculateCPFVerifyingDigit(final int summation) {
        int value = summation - ((summation / 11) * 11);
        int remainder = (value % 11);
        if (value < 2) {
            return 0;
        }
        return 11 - remainder;
    }

    public static int calculateCNPJVerifyingDigit(final int summation) {
        int remainder;
        if ((remainder = summation % 11) < 2) {
            return 0;
        }
        return 11 - remainder;
    }

    public static boolean isCPFValid(final String cpf) {
        String _cpf;
        if (isNullOrEmpty(cpf) || (_cpf = removeMask(cpf)).length() != 11 || COMMON_INVALID_CPF_LIST.contains(cpf)) {
            return false;
        }
        int[] digits = toIntArray(_cpf);
        int verifyingDigitOne, verifyingDigitTwo, summation = 0;
        for (int i = 0; i < CPF_WEIGHTS.length - 1; i++) {
            summation += digits[i] * CPF_WEIGHTS[i + 1];
        }
        verifyingDigitOne = calculateCPFVerifyingDigit(summation);
        summation = 0;
        for (int i = 0; i < CPF_WEIGHTS.length; i++) {
            summation += digits[i] * CPF_WEIGHTS[i];
        }
        verifyingDigitTwo = calculateCPFVerifyingDigit(summation);
        return verifyingDigitOne == digits[9] && verifyingDigitTwo == digits[10];
    }

    public static boolean isCNPJValid(final String cnpj) {
        String _cnpj;
        if (isNullOrEmpty(cnpj) || (_cnpj = removeMask(cnpj)).length() != 14 || COMMON_INVALID_CNPJ_LIST.contains(cnpj)) {
            return false;
        }
        int[] digits = toIntArray(_cnpj);
        int verifyingDigitOne, verifyingDigitTwo, summation = 0;
        for (int i = 0; i < CNPJ_WEIGHTS.length - 1; i++) {
            summation += digits[i] * CNPJ_WEIGHTS[i + 1];
        }
        verifyingDigitOne = calculateCNPJVerifyingDigit(summation);
        summation = 0;
        for (int i = 0; i < CNPJ_WEIGHTS.length; i++) {
            summation += digits[i] * CNPJ_WEIGHTS[i];
        }
        verifyingDigitTwo = calculateCNPJVerifyingDigit(summation);
        return verifyingDigitOne == digits[12] && verifyingDigitTwo == digits[13];
    }

    public static void validateCPF(final String cpf) throws InvalidDocumentException {
        if (!isCPFValid(cpf)) {
            throw new InvalidDocumentException("CPF is not valid");
        }
    }

    public static void validateCNPJ(final String cnpj) throws InvalidDocumentException {
        if (!isCNPJValid(cnpj)) {
            throw new InvalidDocumentException("Cnpj is not valid");
        }
    }
}
