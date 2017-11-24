package com.google.gson;

import com.google.gson.stream.*;
import java.util.*;
import com.google.gson.internal.*;
import java.io.*;

public final class JsonStreamParser implements Iterator<JsonElement>
{
    private final Object lock;
    private final JsonReader parser;
    
    public JsonStreamParser(final Reader reader) {
        (this.parser = new JsonReader(reader)).setLenient(true);
        this.lock = new Object();
    }
    
    public JsonStreamParser(final String s) {
        this(new StringReader(s));
    }
    
    @Override
    public boolean hasNext() {
        while (true) {
            final Object lock = this.lock;
            // monitorenter(lock)
            try {
                if (this.parser.peek() != JsonToken.END_DOCUMENT) {
                    return true;
                }
            }
            catch (MalformedJsonException ex) {
                throw new JsonSyntaxException(ex);
                try {}
                finally {
                }
                // monitorexit(lock)
            }
            catch (IOException ex2) {
                throw new JsonIOException(ex2);
            }
            return false;
        }
    }
    
    @Override
    public JsonElement next() throws JsonParseException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return Streams.parse(this.parser);
        }
        catch (StackOverflowError stackOverflowError) {
            throw new JsonParseException("Failed parsing JSON source to Json", stackOverflowError);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            throw new JsonParseException("Failed parsing JSON source to Json", outOfMemoryError);
        }
        catch (JsonParseException ex) {
            if (ex.getCause() instanceof EOFException) {
                ex = new NoSuchElementException();
            }
            throw ex;
        }
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
