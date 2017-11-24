package okhttp3.internal;

import java.lang.reflect.*;

class OptionalMethod<T>
{
    private final String methodName;
    private final Class[] methodParams;
    private final Class<?> returnType;
    
    public OptionalMethod(final Class<?> returnType, final String methodName, final Class... methodParams) {
        this.returnType = returnType;
        this.methodName = methodName;
        this.methodParams = methodParams;
    }
    
    private Method getMethod(final Class<?> clazz) {
        final String methodName = this.methodName;
        Method publicMethod = null;
        if (methodName != null) {
            publicMethod = getPublicMethod(clazz, this.methodName, this.methodParams);
            if (publicMethod != null && this.returnType != null && !this.returnType.isAssignableFrom(publicMethod.getReturnType())) {
                publicMethod = null;
            }
        }
        return publicMethod;
    }
    
    private static Method getPublicMethod(final Class<?> clazz, final String s, final Class[] array) {
        Method method = null;
        try {
            method = clazz.getMethod(s, (Class[])array);
            if ((method.getModifiers() & 0x1) == 0x0) {
                method = null;
            }
            return method;
        }
        catch (NoSuchMethodException ex) {
            return method;
        }
    }
    
    public Object invoke(final T t, final Object... array) throws InvocationTargetException {
        final Method method = this.getMethod(t.getClass());
        if (method == null) {
            throw new AssertionError((Object)("Method " + this.methodName + " not supported for object " + t));
        }
        try {
            return method.invoke(t, array);
        }
        catch (IllegalAccessException ex) {
            final AssertionError assertionError = new AssertionError((Object)("Unexpectedly could not call: " + method));
            assertionError.initCause(ex);
            throw assertionError;
        }
    }
    
    public Object invokeOptional(final T t, final Object... array) throws InvocationTargetException {
        final Method method = this.getMethod(t.getClass());
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(t, array);
        }
        catch (IllegalAccessException ex) {
            return null;
        }
    }
    
    public Object invokeOptionalWithoutCheckedException(final T t, final Object... array) {
        try {
            return this.invokeOptional(t, array);
        }
        catch (InvocationTargetException ex) {
            final Throwable targetException = ex.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw (RuntimeException)targetException;
            }
            final AssertionError assertionError = new AssertionError((Object)"Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }
    
    public Object invokeWithoutCheckedException(final T t, final Object... array) {
        try {
            return this.invoke(t, array);
        }
        catch (InvocationTargetException ex) {
            final Throwable targetException = ex.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw (RuntimeException)targetException;
            }
            final AssertionError assertionError = new AssertionError((Object)"Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }
    
    public boolean isSupported(final T t) {
        return this.getMethod(t.getClass()) != null;
    }
}
