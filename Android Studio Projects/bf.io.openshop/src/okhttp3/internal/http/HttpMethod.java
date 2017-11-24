package okhttp3.internal.http;

public final class HttpMethod
{
    public static boolean invalidatesCache(final String s) {
        return s.equals("POST") || s.equals("PATCH") || s.equals("PUT") || s.equals("DELETE") || s.equals("MOVE");
    }
    
    public static boolean permitsRequestBody(final String s) {
        return requiresRequestBody(s) || s.equals("OPTIONS") || s.equals("DELETE") || s.equals("PROPFIND") || s.equals("MKCOL") || s.equals("LOCK");
    }
    
    public static boolean redirectsToGet(final String s) {
        return !s.equals("PROPFIND");
    }
    
    public static boolean requiresRequestBody(final String s) {
        return s.equals("POST") || s.equals("PUT") || s.equals("PATCH") || s.equals("PROPPATCH") || s.equals("REPORT");
    }
}
