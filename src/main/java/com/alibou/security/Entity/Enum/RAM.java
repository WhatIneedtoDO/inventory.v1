package com.alibou.security.Entity.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RAM {

    VALUE2(2),
    VALUE4(4),
    VALUE6(6),
    VALUE8(8),
    VALUE12(12),
    VALUE16(16),
    VALUE24(24),
    VALUE32(32),
    VALUE64(64),
    VALUE128(128);

    @Getter
    private final int value;


}
