package com.google.gson.internal;

import java.util.*;

class LinkedTreeMap$EntrySet$1 extends LinkedTreeMapIterator<Entry<K, V>> {
    @Override
    public Entry<K, V> next() {
        return ((LinkedTreeMapIterator)this).nextNode();
    }
}