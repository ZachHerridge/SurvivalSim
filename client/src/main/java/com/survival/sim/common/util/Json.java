package com.survival.sim.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.survival.sim.common.entities.*;
import com.survival.sim.common.entities.interfaces.Locatable;
import com.survival.sim.common.entities.tile.types.TileGrass;
import com.survival.sim.common.entities.tile.types.TileWater;

public class Json {

    private static final Gson gson;

    /***
     * Registers sub types to GSON
     */
    static {
        final RuntimeTypeAdapterFactory<Locatable> typeFactory = RuntimeTypeAdapterFactory
                .of(Locatable.class, "type")
                .registerSubtype(Player.class)
                .registerSubtype(Tile.class)
                .registerSubtype(WorldObject.class)
                .registerSubtype(TileGrass.class)
                .registerSubtype(TileWater.class)
                .registerSubtype(Entity.class)
                .registerSubtype(TreeEntity.class)
                .registerSubtype(RockEntity.class)
                .registerSubtype(MushroomEntity.class)
                .registerSubtype(BerryBushEntity.class);

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(typeFactory)
                .create();
    }

    public static Gson getGson() {
        return gson;
    }
}
