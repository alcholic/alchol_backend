package com.hirim.sulgijang.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultStatus {
    SUCCESS(0), FAIL(-1);

    private final int value;
}
