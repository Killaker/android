package com.google.gson.internal;

class LinkedTreeMap$KeySet$1 extends LinkedTreeMapIterator<K> {
    @Override
    public K next() {
        return ((LinkedTreeMapIterator)this).nextNode().key;
    }
}