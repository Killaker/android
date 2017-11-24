package android.net.http;

public class HttpAuthHeader
{
    public static final int BASIC = 1;
    public static final String BASIC_TOKEN = "Basic";
    public static final int DIGEST = 2;
    public static final String DIGEST_TOKEN = "Digest";
    public static final int UNKNOWN;
    
    public HttpAuthHeader(final String s) {
        throw new RuntimeException("Stub!");
    }
    
    public String getAlgorithm() {
        throw new RuntimeException("Stub!");
    }
    
    public String getNonce() {
        throw new RuntimeException("Stub!");
    }
    
    public String getOpaque() {
        throw new RuntimeException("Stub!");
    }
    
    public String getPassword() {
        throw new RuntimeException("Stub!");
    }
    
    public String getQop() {
        throw new RuntimeException("Stub!");
    }
    
    public String getRealm() {
        throw new RuntimeException("Stub!");
    }
    
    public int getScheme() {
        throw new RuntimeException("Stub!");
    }
    
    public boolean getStale() {
        throw new RuntimeException("Stub!");
    }
    
    public String getUsername() {
        throw new RuntimeException("Stub!");
    }
    
    public boolean isBasic() {
        throw new RuntimeException("Stub!");
    }
    
    public boolean isDigest() {
        throw new RuntimeException("Stub!");
    }
    
    public boolean isProxy() {
        throw new RuntimeException("Stub!");
    }
    
    public boolean isSupportedScheme() {
        throw new RuntimeException("Stub!");
    }
    
    public void setPassword(final String s) {
        throw new RuntimeException("Stub!");
    }
    
    public void setProxy() {
        throw new RuntimeException("Stub!");
    }
    
    public void setUsername(final String s) {
        throw new RuntimeException("Stub!");
    }
}
