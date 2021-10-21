package com.simononboard.blockchain.common.util;

import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class Exceptions {
    public static final Supplier<SecurityException> securityExceptionSupplier = () -> new SecurityException("Ресурс принадлежит другому пользователю");
}
