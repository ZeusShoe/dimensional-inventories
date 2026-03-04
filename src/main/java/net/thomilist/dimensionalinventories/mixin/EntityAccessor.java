package net.thomilist.dimensionalinventories.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin( Entity.class )
public interface EntityAccessor
{
    @Invoker
    void invokeReadData( ReadView view );

    @Invoker
    void invokeWriteData( WriteView view );
}
