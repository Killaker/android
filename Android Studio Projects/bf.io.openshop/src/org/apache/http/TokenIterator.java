package org.apache.http;

import java.util.*;

@Deprecated
public interface TokenIterator extends Iterator
{
    boolean hasNext();
    
    String nextToken();
}
