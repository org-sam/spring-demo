package io.platosedu.springdemo.config;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {

    private byte[] cachedPayload;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CachedHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        var requestInputStream = request.getInputStream();
        this.cachedPayload = StreamUtils.copyToByteArray(requestInputStream);
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedServletInputStream(this.cachedPayload);
    }

    @Override
    public BufferedReader getReader() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedPayload);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }
}
