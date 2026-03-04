package net.thomilist.dimensionalinventories.module.builtin.travelersbackpack;

import com.google.gson.Gson;
import net.minecraft.server.network.ServerPlayerEntity;
import net.thomilist.dimensionalinventories.module.base.JsonModule;
import net.thomilist.dimensionalinventories.module.base.ModuleBase;
import net.thomilist.dimensionalinventories.module.base.player.JsonPlayerModule;
import net.thomilist.dimensionalinventories.module.version.StorageVersion;

public final class TravelersBackpackModule
    extends ModuleBase
    implements JsonPlayerModule<TravelersBackpackModuleState>
{
    private static final String MODULE_ID = "travelers-backpack";
    private static final String DESCRIPTION = "Traveler's Backpack wearable and stored backpack state.";

    private static final StorageVersion[] STORAGE_VERSIONS = {
        StorageVersion.V2
    };

    private static final Gson GSON = JsonModule.GSON_BUILDER.create();

    private final TravelersBackpackModuleState state = new TravelersBackpackModuleState();

    public TravelersBackpackModule( final String groupId )
    {
        super(
            TravelersBackpackModule.STORAGE_VERSIONS,
            groupId,
            TravelersBackpackModule.MODULE_ID,
            TravelersBackpackModule.DESCRIPTION
        );
    }

    @Override
    public TravelersBackpackModuleState newInstance( final ServerPlayerEntity player )
    {
        return new TravelersBackpackModuleState( player );
    }

    @Override
    public TravelersBackpackModuleState state()
    {
        return this.state;
    }

    @Override
    public TravelersBackpackModuleState defaultState()
    {
        return new TravelersBackpackModuleState();
    }

    @Override
    public Gson gson()
    {
        return TravelersBackpackModule.GSON;
    }
}
