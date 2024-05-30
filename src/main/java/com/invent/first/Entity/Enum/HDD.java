package com.invent.first.Entity.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HDD {
    VALUE0(0),
    VALUE128(128),
    VALUE256(256),
    VALUE512(512),
    VALUE1024(1024),
    VALUE2048(2048),
    VALUE4096(4096);

    private final int value;
}
