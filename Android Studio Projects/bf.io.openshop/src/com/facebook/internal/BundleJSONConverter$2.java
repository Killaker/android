package com.facebook.internal;

import android.os.*;
import org.json.*;

static final class BundleJSONConverter$2 implements Setter {
    @Override
    public void setOnBundle(final Bundle bundle, final String s, final Object o) throws JSONException {
        bundle.putInt(s, (int)o);
    }
    
    @Override
    public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) throws JSONException {
        jsonObject.put(s, o);
    }
}