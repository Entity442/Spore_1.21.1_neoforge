package com.Harbinger.Spore.Fluids;

import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.core.Seffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.ArrayList;
import java.util.List;

public class BileLiquid extends FluidType {

    public BileLiquid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSwim(Entity entity) {
        return entity instanceof UtilityEntity;
    }

    @Override
    public boolean canExtinguish(Entity entity) {
        return true;
    }

    public static List<MobEffectInstance> bileEffects(){
        List<MobEffectInstance> values = new ArrayList<>();
        values.add(new MobEffectInstance(Seffects.BILED,100,0));
        return values;
    }

}
