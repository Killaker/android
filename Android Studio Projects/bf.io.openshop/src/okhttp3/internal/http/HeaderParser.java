package okhttp3.internal.http;

public final class HeaderParser
{
    public static int parseSeconds(final String s, final int n) {
        try {
            final long long1 = Long.parseLong(s);
            if (long1 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (long1 < 0L) {
                return 0;
            }
            return (int)long1;
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static int skipUntil(final String s, int n, final String s2) {
        while (n < s.length() && s2.indexOf(s.charAt(n)) == -1) {
            ++n;
        }
        return n;
    }
    
    public static int skipWhitespace(final String s, int i) {
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if (char1 != ' ' && char1 != '\t') {
                break;
            }
            ++i;
        }
        return i;
    }
}
