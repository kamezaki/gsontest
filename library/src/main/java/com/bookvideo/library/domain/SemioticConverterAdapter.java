package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SemioticConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Semiotic.class, new SemioticDeserializer());
    }

    private static class SemioticDeserializer implements JsonDeserializer<Semiotic> {

        @Override
        public Semiotic deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Semiotic.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Semiotic.NONE;
            }
        }
    }
}
