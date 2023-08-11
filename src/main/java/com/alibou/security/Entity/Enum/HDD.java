package com.alibou.security.Entity.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HDD {
    VALUENOTINSTALL(0),
    VALUE128(128),
    VALUE256(256),
    VALUE512(512),
    VALUE1024(1024),
    VALUE2048(2048),
    VALUE4096(4096);

    @Getter
    private final int value;
}
