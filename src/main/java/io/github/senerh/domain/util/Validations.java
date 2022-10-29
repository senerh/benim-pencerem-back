package io.github.senerh.domain.util;

import io.github.senerh.domain.exception.DomainException;

public class Validations {

    public static <E> E notNull(E e, String attributName) {
        if (e == null) {
            throw new DomainException(attributName + " is null");
        }
        return e;
    }

    public static String notBlank(String s, String attributName) {
        if (isBlank(s)) {
            throw new DomainException(attributName + " is a blank String");
        }
        return s;
    }

    public static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
