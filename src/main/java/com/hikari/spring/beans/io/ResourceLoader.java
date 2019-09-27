package com.hikari.spring.beans.io;

import java.net.URL;

/**
 * Strategy for loading resources (e.. class path or file system
 * resources)
 */
public class ResourceLoader {

    /**
     * Return a Resource handle for the specified resource location.
     *
     * @param location the resource location
     * @return a corresponding Resource handle
     */
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
