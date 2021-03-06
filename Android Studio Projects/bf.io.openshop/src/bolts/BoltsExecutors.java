package bolts;

import java.util.concurrent.*;
import java.util.*;

final class BoltsExecutors
{
    private static final BoltsExecutors INSTANCE;
    private final ExecutorService background;
    private final Executor immediate;
    private final ScheduledExecutorService scheduled;
    
    static {
        INSTANCE = new BoltsExecutors();
    }
    
    private BoltsExecutors() {
        ExecutorService background;
        if (!isAndroidRuntime()) {
            background = Executors.newCachedThreadPool();
        }
        else {
            background = AndroidExecutors.newCachedThreadPool();
        }
        this.background = background;
        this.scheduled = Executors.newSingleThreadScheduledExecutor();
        this.immediate = new ImmediateExecutor();
    }
    
    public static ExecutorService background() {
        return BoltsExecutors.INSTANCE.background;
    }
    
    static Executor immediate() {
        return BoltsExecutors.INSTANCE.immediate;
    }
    
    private static boolean isAndroidRuntime() {
        final String property = System.getProperty("java.runtime.name");
        return property != null && property.toLowerCase(Locale.US).contains("android");
    }
    
    static ScheduledExecutorService scheduled() {
        return BoltsExecutors.INSTANCE.scheduled;
    }
    
    private static class ImmediateExecutor implements Executor
    {
        private static final int MAX_DEPTH = 15;
        private ThreadLocal<Integer> executionDepth;
        
        private ImmediateExecutor() {
            this.executionDepth = new ThreadLocal<Integer>();
        }
        
        private int decrementDepth() {
            Integer value = this.executionDepth.get();
            if (value == null) {
                value = 0;
            }
            final int n = -1 + value;
            if (n == 0) {
                this.executionDepth.remove();
                return n;
            }
            this.executionDepth.set(n);
            return n;
        }
        
        private int incrementDepth() {
            Integer value = this.executionDepth.get();
            if (value == null) {
                value = 0;
            }
            final int n = 1 + value;
            this.executionDepth.set(n);
            return n;
        }
        
        @Override
        public void execute(final Runnable runnable) {
            Label_0021: {
                if (this.incrementDepth() > 15) {
                    break Label_0021;
                }
                try {
                    runnable.run();
                    return;
                    BoltsExecutors.background().execute(runnable);
                }
                finally {
                    this.decrementDepth();
                }
            }
        }
    }
}
