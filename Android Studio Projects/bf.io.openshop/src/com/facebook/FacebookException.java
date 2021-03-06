package com.facebook;

public class FacebookException extends RuntimeException
{
    static final long serialVersionUID = 1L;
    
    public FacebookException() {
    }
    
    public FacebookException(final String s) {
        super(s);
    }
    
    public FacebookException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FacebookException(final String s, final Object... array) {
        this(String.format(s, array));
    }
    
    public FacebookException(final Throwable t) {
        super(t);
    }
    
    @Override
    public String toString() {
        return this.getMessage();
    }
}
