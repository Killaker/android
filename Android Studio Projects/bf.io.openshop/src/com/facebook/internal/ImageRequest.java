package com.facebook.internal;

import android.content.*;
import android.net.*;
import java.util.*;

public class ImageRequest
{
    private static final String AUTHORITY = "graph.facebook.com";
    private static final String HEIGHT_PARAM = "height";
    private static final String MIGRATION_PARAM = "migration_overrides";
    private static final String MIGRATION_VALUE = "{october_2012:true}";
    private static final String PATH = "%s/picture";
    private static final String SCHEME = "https";
    public static final int UNSPECIFIED_DIMENSION = 0;
    private static final String WIDTH_PARAM = "width";
    private boolean allowCachedRedirects;
    private Callback callback;
    private Object callerTag;
    private Context context;
    private Uri imageUri;
    
    private ImageRequest(final Builder builder) {
        this.context = builder.context;
        this.imageUri = builder.imageUrl;
        this.callback = builder.callback;
        this.allowCachedRedirects = builder.allowCachedRedirects;
        Object access$400;
        if (builder.callerTag == null) {
            access$400 = new Object();
        }
        else {
            access$400 = builder.callerTag;
        }
        this.callerTag = access$400;
    }
    
    public static Uri getProfilePictureUri(final String s, final int n, final int n2) {
        Validate.notNullOrEmpty(s, "userId");
        final int max = Math.max(n, 0);
        final int max2 = Math.max(n2, 0);
        if (max == 0 && max2 == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        final Uri$Builder path = new Uri$Builder().scheme("https").authority("graph.facebook.com").path(String.format(Locale.US, "%s/picture", s));
        if (max2 != 0) {
            path.appendQueryParameter("height", String.valueOf(max2));
        }
        if (max != 0) {
            path.appendQueryParameter("width", String.valueOf(max));
        }
        path.appendQueryParameter("migration_overrides", "{october_2012:true}");
        return path.build();
    }
    
    public Callback getCallback() {
        return this.callback;
    }
    
    public Object getCallerTag() {
        return this.callerTag;
    }
    
    public Context getContext() {
        return this.context;
    }
    
    public Uri getImageUri() {
        return this.imageUri;
    }
    
    public boolean isCachedRedirectAllowed() {
        return this.allowCachedRedirects;
    }
    
    public static class Builder
    {
        private boolean allowCachedRedirects;
        private Callback callback;
        private Object callerTag;
        private Context context;
        private Uri imageUrl;
        
        public Builder(final Context context, final Uri imageUrl) {
            Validate.notNull(imageUrl, "imageUri");
            this.context = context;
            this.imageUrl = imageUrl;
        }
        
        public ImageRequest build() {
            return new ImageRequest(this, null);
        }
        
        public Builder setAllowCachedRedirects(final boolean allowCachedRedirects) {
            this.allowCachedRedirects = allowCachedRedirects;
            return this;
        }
        
        public Builder setCallback(final Callback callback) {
            this.callback = callback;
            return this;
        }
        
        public Builder setCallerTag(final Object callerTag) {
            this.callerTag = callerTag;
            return this;
        }
    }
    
    public interface Callback
    {
        void onCompleted(final ImageResponse p0);
    }
}
