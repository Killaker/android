package org.apache.http.io;

import java.io.*;
import org.apache.http.*;

@Deprecated
public interface HttpMessageWriter
{
    void write(final HttpMessage p0) throws IOException, HttpException;
}
