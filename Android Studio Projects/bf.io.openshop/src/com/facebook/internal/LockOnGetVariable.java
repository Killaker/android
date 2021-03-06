package com.facebook.internal;

import com.facebook.*;
import java.util.concurrent.*;

public class LockOnGetVariable<T>
{
    private CountDownLatch initLatch;
    private T value;
    
    public LockOnGetVariable(final T value) {
        this.value = value;
    }
    
    public LockOnGetVariable(final Callable<T> callable) {
        this.initLatch = new CountDownLatch(1);
        FacebookSdk.getExecutor().execute(new FutureTask<Object>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    LockOnGetVariable.this.value = (T)callable.call();
                    return null;
                }
                finally {
                    LockOnGetVariable.this.initLatch.countDown();
                }
            }
        }));
    }
    
    private void waitOnInit() {
        if (this.initLatch == null) {
            return;
        }
        try {
            this.initLatch.await();
        }
        catch (InterruptedException ex) {}
    }
    
    public T getValue() {
        this.waitOnInit();
        return this.value;
    }
}
