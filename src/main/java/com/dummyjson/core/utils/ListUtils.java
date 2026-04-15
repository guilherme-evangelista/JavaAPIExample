package com.dummyjson.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.dummyjson.core.exeption.EmptyListException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListUtils {

    private static void requireNonEmpty(List<?> list) throws EmptyListException {
        requireNonEmpty(list, "List is null or empty.");
    }

    public static boolean isNullOrEmpty(final List<?> list) {
        return list == null || list.isEmpty();
    }

    public static void requireNonEmpty(final List<?> list, final String errorMessage) throws EmptyListException {
        if (StringUtils.isNullOrEmpty(errorMessage)) {
            throw new IllegalArgumentException("Attribute errorMessage cannot be null or empty.");
        }
        if (isNullOrEmpty(list)) {
            throw new EmptyListException(errorMessage);
        }
    }

    public static <T> List<T> removeDuplicates(@NonNull final List<T> list) {
        if (list.isEmpty()) {
            return list;
        }
        return list.stream().parallel().distinct().collect(Collectors.toList());
    }

    public static String prettyPrint(@NonNull final List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(line -> stringBuilder.append(line).append(System.lineSeparator()));
        return stringBuilder.toString().stripTrailing();
    }

    public static boolean areListsEquals(@NonNull final List<?> listOne, @NonNull final List<?> listTwo) {
        return listOne.size() == listTwo.size() && new HashSet<>(listOne).equals(new HashSet<>(listTwo));
    }
}
