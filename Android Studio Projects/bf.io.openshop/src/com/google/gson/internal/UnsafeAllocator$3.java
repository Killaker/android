package com.google.gson.internal;

import java.lang.reflect.*;

static final class UnsafeAllocator$3 extends UnsafeAllocator {
    final /* synthetic */ Method val$newInstance;
    
    @Override
    public <T> T newInstance(final Class<T> clazz) throws Exception {
        return (T)this.val$newInstance.invoke(null, clazz, Object.class);
    }
}