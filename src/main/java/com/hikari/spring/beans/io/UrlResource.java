package com.hikari.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * {@link Resource} implementation for {@code java.net.URL} locators.
 * Supports resolution as a {@code URL} and also as a {@code File} in
 * case of the {@code "file:"} protocol.
 */
public class UrlResource implements Resource {

    /**
     * Original URL, used for actual access.
     */
    private final URL url;

    /**
     * Create a new {@code UrlResource} based on the given URL object.
     *
     * @param url a URL
     */
    public UrlResource(URL url) {
        this.url = url;
    }

    /**
     * This implementation opens an InputStream for the given URL.
     *
     * @return the input stream for the underlying resource (must not be {@code null})
     * @throws IOException if the content stream could not be opened
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
