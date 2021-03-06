package okio;

import java.io.*;
import java.nio.charset.*;

final class RealBufferedSource implements BufferedSource
{
    public final Buffer buffer;
    private boolean closed;
    public final Source source;
    
    public RealBufferedSource(final Source source) {
        this(source, new Buffer());
    }
    
    public RealBufferedSource(final Source source, final Buffer buffer) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.buffer = buffer;
        this.source = source;
    }
    
    private boolean rangeEquals(final long n, final ByteString byteString) throws IOException {
        return this.request(n + byteString.size()) && this.buffer.rangeEquals(n, byteString);
    }
    
    @Override
    public Buffer buffer() {
        return this.buffer;
    }
    
    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.buffer.clear();
    }
    
    @Override
    public boolean exhausted() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.buffer.exhausted() && this.source.read(this.buffer, 2048L) == -1L;
    }
    
    @Override
    public long indexOf(final byte b) throws IOException {
        return this.indexOf(b, 0L);
    }
    
    @Override
    public long indexOf(final byte b, long size) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (size >= this.buffer.size) {
            if (this.source.read(this.buffer, 2048L) == -1L) {
                return -1L;
            }
        }
        do {
            final long index = this.buffer.indexOf(b, size);
            if (index != -1L) {
                return index;
            }
            size = this.buffer.size;
        } while (this.source.read(this.buffer, 2048L) != -1L);
        return -1L;
    }
    
    @Override
    public long indexOf(final ByteString byteString) throws IOException {
        return this.indexOf(byteString, 0L);
    }
    
    @Override
    public long indexOf(final ByteString byteString, long n) throws IOException {
        if (byteString.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long index = 0L;
        Label_0022: {
            break Label_0022;
            do {
                n = index + 1L;
                index = this.indexOf(byteString.getByte(0), n);
                if (index == -1L) {
                    return -1L;
                }
            } while (!this.rangeEquals(index, byteString));
        }
        return index;
    }
    
    @Override
    public long indexOfElement(final ByteString byteString) throws IOException {
        return this.indexOfElement(byteString, 0L);
    }
    
    @Override
    public long indexOfElement(final ByteString byteString, long size) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (size >= this.buffer.size) {
            if (this.source.read(this.buffer, 2048L) == -1L) {
                return -1L;
            }
        }
        do {
            final long indexOfElement = this.buffer.indexOfElement(byteString, size);
            if (indexOfElement != -1L) {
                return indexOfElement;
            }
            size = this.buffer.size;
        } while (this.source.read(this.buffer, 2048L) != -1L);
        return -1L;
    }
    
    @Override
    public InputStream inputStream() {
        return new InputStream() {
            @Override
            public int available() throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                return (int)Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
            }
            
            @Override
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }
            
            @Override
            public int read() throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                if (RealBufferedSource.this.buffer.size == 0L && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 2048L) == -1L) {
                    return -1;
                }
                return 0xFF & RealBufferedSource.this.buffer.readByte();
            }
            
            @Override
            public int read(final byte[] array, final int n, final int n2) throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                Util.checkOffsetAndCount(array.length, n, n2);
                if (RealBufferedSource.this.buffer.size == 0L && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 2048L) == -1L) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.read(array, n, n2);
            }
            
            @Override
            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }
    
    @Override
    public int read(final byte[] array) throws IOException {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        Util.checkOffsetAndCount(array.length, n, n2);
        if (this.buffer.size == 0L && this.source.read(this.buffer, 2048L) == -1L) {
            return -1;
        }
        return this.buffer.read(array, n, (int)Math.min(n2, this.buffer.size));
    }
    
    @Override
    public long read(final Buffer buffer, final long n) throws IOException {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.buffer.size == 0L && this.source.read(this.buffer, 2048L) == -1L) {
            return -1L;
        }
        return this.buffer.read(buffer, Math.min(n, this.buffer.size));
    }
    
    @Override
    public long readAll(final Sink sink) throws IOException {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long n = 0L;
        while (this.source.read(this.buffer, 2048L) != -1L) {
            final long completeSegmentByteCount = this.buffer.completeSegmentByteCount();
            if (completeSegmentByteCount > 0L) {
                n += completeSegmentByteCount;
                sink.write(this.buffer, completeSegmentByteCount);
            }
        }
        if (this.buffer.size() > 0L) {
            n += this.buffer.size();
            sink.write(this.buffer, this.buffer.size());
        }
        return n;
    }
    
    @Override
    public byte readByte() throws IOException {
        this.require(1L);
        return this.buffer.readByte();
    }
    
    @Override
    public byte[] readByteArray() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }
    
    @Override
    public byte[] readByteArray(final long n) throws IOException {
        this.require(n);
        return this.buffer.readByteArray(n);
    }
    
    @Override
    public ByteString readByteString() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }
    
    @Override
    public ByteString readByteString(final long n) throws IOException {
        this.require(n);
        return this.buffer.readByteString(n);
    }
    
    @Override
    public long readDecimalLong() throws IOException {
        this.require(1L);
        int n = 0;
        while (this.request(n + 1)) {
            final byte byte1 = this.buffer.getByte(n);
            if ((byte1 < 48 || byte1 > 57) && (n != 0 || byte1 != 45)) {
                if (n == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", byte1));
                }
                break;
            }
            else {
                ++n;
            }
        }
        return this.buffer.readDecimalLong();
    }
    
    @Override
    public void readFully(final Buffer buffer, final long n) throws IOException {
        try {
            this.require(n);
            this.buffer.readFully(buffer, n);
        }
        catch (EOFException ex) {
            buffer.writeAll(this.buffer);
            throw ex;
        }
    }
    
    @Override
    public void readFully(final byte[] array) throws IOException {
        try {
            this.require(array.length);
            this.buffer.readFully(array);
        }
        catch (EOFException ex) {
            int n = 0;
            while (this.buffer.size > 0L) {
                final int read = this.buffer.read(array, n, (int)this.buffer.size);
                if (read == -1) {
                    throw new AssertionError();
                }
                n += read;
            }
            throw ex;
        }
    }
    
    @Override
    public long readHexadecimalUnsignedLong() throws IOException {
        this.require(1L);
        int n = 0;
        while (this.request(n + 1)) {
            final byte byte1 = this.buffer.getByte(n);
            if ((byte1 < 48 || byte1 > 57) && (byte1 < 97 || byte1 > 102) && (byte1 < 65 || byte1 > 70)) {
                if (n == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", byte1));
                }
                break;
            }
            else {
                ++n;
            }
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }
    
    @Override
    public int readInt() throws IOException {
        this.require(4L);
        return this.buffer.readInt();
    }
    
    @Override
    public int readIntLe() throws IOException {
        this.require(4L);
        return this.buffer.readIntLe();
    }
    
    @Override
    public long readLong() throws IOException {
        this.require(8L);
        return this.buffer.readLong();
    }
    
    @Override
    public long readLongLe() throws IOException {
        this.require(8L);
        return this.buffer.readLongLe();
    }
    
    @Override
    public short readShort() throws IOException {
        this.require(2L);
        return this.buffer.readShort();
    }
    
    @Override
    public short readShortLe() throws IOException {
        this.require(2L);
        return this.buffer.readShortLe();
    }
    
    @Override
    public String readString(final long n, final Charset charset) throws IOException {
        this.require(n);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return this.buffer.readString(n, charset);
    }
    
    @Override
    public String readString(final Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.buffer.writeAll(this.source);
        return this.buffer.readString(charset);
    }
    
    @Override
    public String readUtf8() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }
    
    @Override
    public String readUtf8(final long n) throws IOException {
        this.require(n);
        return this.buffer.readUtf8(n);
    }
    
    @Override
    public int readUtf8CodePoint() throws IOException {
        this.require(1L);
        final byte byte1 = this.buffer.getByte(0L);
        if ((byte1 & 0xE0) == 0xC0) {
            this.require(2L);
        }
        else if ((byte1 & 0xF0) == 0xE0) {
            this.require(3L);
        }
        else if ((byte1 & 0xF8) == 0xF0) {
            this.require(4L);
        }
        return this.buffer.readUtf8CodePoint();
    }
    
    @Override
    public String readUtf8Line() throws IOException {
        final long index = this.indexOf((byte)10);
        if (index != -1L) {
            return this.buffer.readUtf8Line(index);
        }
        if (this.buffer.size != 0L) {
            return this.readUtf8(this.buffer.size);
        }
        return null;
    }
    
    @Override
    public String readUtf8LineStrict() throws IOException {
        final long index = this.indexOf((byte)10);
        if (index == -1L) {
            final Buffer buffer = new Buffer();
            this.buffer.copyTo(buffer, 0L, Math.min(32L, this.buffer.size()));
            throw new EOFException("\\n not found: size=" + this.buffer.size() + " content=" + buffer.readByteString().hex() + "...");
        }
        return this.buffer.readUtf8Line(index);
    }
    
    @Override
    public boolean request(final long n) throws IOException {
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (this.buffer.size < n) {
            if (this.source.read(this.buffer, 2048L) == -1L) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void require(final long n) throws IOException {
        if (!this.request(n)) {
            throw new EOFException();
        }
    }
    
    @Override
    public void skip(long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (n > 0L) {
            if (this.buffer.size == 0L && this.source.read(this.buffer, 2048L) == -1L) {
                throw new EOFException();
            }
            final long min = Math.min(n, this.buffer.size());
            this.buffer.skip(min);
            n -= min;
        }
    }
    
    @Override
    public Timeout timeout() {
        return this.source.timeout();
    }
    
    @Override
    public String toString() {
        return "buffer(" + this.source + ")";
    }
}
