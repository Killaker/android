package com.google.gson.internal;

import java.lang.reflect.*;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.util.*;

public final class ConstructorConstructor
{
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    
    public ConstructorConstructor(final Map<Type, InstanceCreator<?>> instanceCreators) {
        this.instanceCreators = instanceCreators;
    }
    
    private <T> ObjectConstructor<T> newDefaultConstructor(final Class<? super T> clazz) {
        try {
            final Constructor<? super T> declaredConstructor = clazz.getDeclaredConstructor((Class<?>[])new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new ObjectConstructor<T>() {
                @Override
                public T construct() {
                    try {
                        return declaredConstructor.newInstance((Object[])null);
                    }
                    catch (InstantiationException ex) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", ex);
                    }
                    catch (InvocationTargetException ex2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", ex2.getTargetException());
                    }
                    catch (IllegalAccessException ex3) {
                        throw new AssertionError((Object)ex3);
                    }
                }
            };
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(final Type type, final Class<? super T> clazz) {
        if (Collection.class.isAssignableFrom(clazz)) {
            if (SortedSet.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        if (!(type instanceof ParameterizedType)) {
                            throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                        }
                        final Type type = ((ParameterizedType)type).getActualTypeArguments()[0];
                        if (type instanceof Class) {
                            return (T)EnumSet.noneOf((Class<Enum>)type);
                        }
                        throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new LinkedList();
                    }
                };
            }
            return new ObjectConstructor<T>() {
                @Override
                public T construct() {
                    return (T)new ArrayList();
                }
            };
        }
        else {
            if (!Map.class.isAssignableFrom(clazz)) {
                return null;
            }
            if (SortedMap.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new TreeMap();
                    }
                };
            }
            if (type instanceof ParameterizedType && !String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)type).getActualTypeArguments()[0]).getRawType())) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new LinkedHashMap();
                    }
                };
            }
            return new ObjectConstructor<T>() {
                @Override
                public T construct() {
                    return (T)new LinkedTreeMap();
                }
            };
        }
    }
    
    private <T> ObjectConstructor<T> newUnsafeAllocator(final Type type, final Class<? super T> clazz) {
        return new ObjectConstructor<T>() {
            private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
            
            @Override
            public T construct() {
                try {
                    return this.unsafeAllocator.newInstance(clazz);
                }
                catch (Exception ex) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", ex);
                }
            }
        };
    }
    
    public <T> ObjectConstructor<T> get(final TypeToken<T> typeToken) {
        final Type type = typeToken.getType();
        final Class<? super T> rawType = typeToken.getRawType();
        final InstanceCreator<?> instanceCreator = this.instanceCreators.get(type);
        Object defaultConstructor;
        if (instanceCreator != null) {
            defaultConstructor = new ObjectConstructor<T>() {
                @Override
                public T construct() {
                    return instanceCreator.createInstance(type);
                }
            };
        }
        else {
            final InstanceCreator<?> instanceCreator2 = this.instanceCreators.get(rawType);
            if (instanceCreator2 != null) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return instanceCreator2.createInstance(type);
                    }
                };
            }
            defaultConstructor = this.newDefaultConstructor((Class<? super Object>)rawType);
            if (defaultConstructor == null) {
                final ObjectConstructor<Object> defaultImplementationConstructor = this.newDefaultImplementationConstructor(type, (Class<? super Object>)rawType);
                if (defaultImplementationConstructor != null) {
                    return (ObjectConstructor<T>)defaultImplementationConstructor;
                }
                return (ObjectConstructor<T>)this.newUnsafeAllocator(type, (Class<? super Object>)rawType);
            }
        }
        return (ObjectConstructor<T>)defaultConstructor;
    }
    
    @Override
    public String toString() {
        return this.instanceCreators.toString();
    }
}
