package com.Harbinger.Spore.Sentities.Variants;

import java.util.Arrays;
import java.util.Comparator;

public enum ConductorVariants {
    DEFAULT(0,"default"),
    CHAINED(1,"spore.entity.variant.chained"),
    SURGE(2,"spore.entity.variant.surge"),
    CHAINER(3,"spore.entity.variant.chainer"),
    MAGNETO(4,"spore.entity.variant.magneto");

    private static final ConductorVariants[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(ConductorVariants::getId)).toArray(ConductorVariants[]::new);
    private final int id;
    private final String name;

    ConductorVariants(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName(){
        return name;
    }
    public static ConductorVariants byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
