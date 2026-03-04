package net.thomilist.dimensionalinventories.module.builtin.travelersbackpack;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.NbtWriteView;
import net.minecraft.storage.ReadView;
import net.minecraft.util.ErrorReporter;
import net.thomilist.dimensionalinventories.compatibility.Compat;
import net.thomilist.dimensionalinventories.mixin.EntityAccessor;
import net.thomilist.dimensionalinventories.module.base.player.PlayerModuleState;
import net.thomilist.dimensionalinventories.util.NbtUtils;

import java.lang.reflect.Type;

public class TravelersBackpackModuleState
    implements PlayerModuleState
{
    private static final String TRAVELERS_BACKPACK_DATA_KEY = "travelersbackpack:travelersbackpack";

    public NbtCompound travelersBackpackData = new NbtCompound();

    public TravelersBackpackModuleState()
    { }

    public TravelersBackpackModuleState( final ServerPlayerEntity player )
    {
        this.loadFromPlayer( player );
    }

    private static NbtCompound getPlayerDataNbt( final ServerPlayerEntity player )
    {
        final NbtWriteView writeView = NbtWriteView.create(
            new ErrorReporter.Impl(),
            Compat.ENTITY.getWorld( player ).getRegistryManager()
        );

        ((EntityAccessor) player).invokeWriteData( writeView );
        return writeView.getNbt();
    }

    private static void setPlayerDataNbt( final ServerPlayerEntity player, final NbtCompound playerData )
    {
        final ReadView readView = NbtReadView.create(
            new ErrorReporter.Impl(),
            Compat.ENTITY.getWorld( player ).getRegistryManager(),
            playerData
        );

        ((EntityAccessor) player).invokeReadData( readView );
    }

    @Override
    public Type type()
    {
        return TravelersBackpackModuleState.class;
    }

    @Override
    public void applyToPlayer( final ServerPlayerEntity player )
    {
        final NbtCompound playerData = TravelersBackpackModuleState.getPlayerDataNbt( player );

        if ( NbtUtils.isEffectivelyEmpty( this.travelersBackpackData ) )
        {
            playerData.remove( TravelersBackpackModuleState.TRAVELERS_BACKPACK_DATA_KEY );
        }
        else
        {
            playerData.put(
                TravelersBackpackModuleState.TRAVELERS_BACKPACK_DATA_KEY,
                this.travelersBackpackData.copy()
            );
        }

        TravelersBackpackModuleState.setPlayerDataNbt( player, playerData );
    }

    @Override
    public void loadFromPlayer( final ServerPlayerEntity player )
    {
        final NbtCompound playerData = TravelersBackpackModuleState.getPlayerDataNbt( player );
        final NbtElement data = playerData.get( TravelersBackpackModuleState.TRAVELERS_BACKPACK_DATA_KEY );

        if ( data instanceof final NbtCompound nbtData )
        {
            this.travelersBackpackData = nbtData.copy();
        }
        else
        {
            this.travelersBackpackData = new NbtCompound();
        }
    }
}
