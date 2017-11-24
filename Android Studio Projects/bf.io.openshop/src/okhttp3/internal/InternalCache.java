package okhttp3.internal;

import okhttp3.*;
import java.io.*;
import okhttp3.internal.http.*;

public interface InternalCache
{
    Response get(final Request p0) throws IOException;
    
    CacheRequest put(final Response p0) throws IOException;
    
    void remove(final Request p0) throws IOException;
    
    void trackConditionalCacheHit();
    
    void trackResponse(final CacheStrategy p0);
    
    void update(final Response p0, final Response p1) throws IOException;
}
