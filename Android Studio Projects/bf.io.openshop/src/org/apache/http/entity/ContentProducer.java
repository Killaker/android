package org.apache.http.entity;

import java.io.*;

@Deprecated
public interface ContentProducer
{
    void writeTo(final OutputStream p0) throws IOException;
}
