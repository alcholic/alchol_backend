package com.hirim.sulgijang.models.enums;

public enum DrinkType {
    SOJU("soju"), BEER("beer"), WINE("wine"), BOMB("bomb"), ETC("etc");

    private final String value;

    DrinkType(String value) {
        this.value = value;
    }
}
