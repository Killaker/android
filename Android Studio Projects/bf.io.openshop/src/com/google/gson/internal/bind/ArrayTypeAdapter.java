package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.reflect.*;
import com.google.gson.internal.*;
import java.util.*;
import java.lang.reflect.*;
import java.io.*;
import com.google.gson.stream.*;

public final class ArrayTypeAdapter<E> extends TypeAdapter<Object>
{
    public static final TypeAdapterFactory FACTORY;
    private final Class<E> componentType;
    private final TypeAdapter<E> componentTypeAdapter;
    
    static {
        FACTORY = new TypeAdapterFactory() {
            @Override
            public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
                final Type type = typeToken.getType();
                if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class)type).isArray())) {
                    return null;
                }
                final Type arrayComponentType = $Gson$Types.getArrayComponentType(type);
                return (TypeAdapter<T>)new ArrayTypeAdapter(gson, (TypeAdapter<Object>)gson.getAdapter(TypeToken.get(arrayComponentType)), (Class<Object>)$Gson$Types.getRawType(arrayComponentType));
            }
        };
    }
    
    public ArrayTypeAdapter(final Gson gson, final TypeAdapter<E> typeAdapter, final Class<E> componentType) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper<E>(gson, typeAdapter, componentType);
        this.componentType = componentType;
    }
    
    @Override
    public Object read(final JsonReader jsonReader) throws IOException {
        Object instance;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            instance = null;
        }
        else {
            final ArrayList<E> list = new ArrayList<E>();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                list.add(this.componentTypeAdapter.read(jsonReader));
            }
            jsonReader.endArray();
            instance = Array.newInstance(this.componentType, list.size());
            for (int i = 0; i < list.size(); ++i) {
                Array.set(instance, i, list.get(i));
            }
        }
        return instance;
    }
    
    @Override
    public void write(final JsonWriter jsonWriter, final Object o) throws IOException {
        if (o == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        for (int i = 0; i < Array.getLength(o); ++i) {
            this.componentTypeAdapter.write(jsonWriter, (E)Array.get(o, i));
        }
        jsonWriter.endArray();
    }
}
