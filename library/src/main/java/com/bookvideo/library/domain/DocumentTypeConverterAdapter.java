package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DocumentTypeConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(DocumentType.class, new DocumentTypeDeserializer());
    }

    private static class DocumentTypeDeserializer implements JsonDeserializer<DocumentType> {
        @Override
        public DocumentType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return DocumentType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Link.defaultType;
            }
        }
    }

}