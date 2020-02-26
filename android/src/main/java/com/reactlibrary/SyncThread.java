package com.reactlibrary;

import com.danikula.videocache.HttpProxyCacheServer;
import com.facebook.react.bridge.Promise;

import java.io.InputStream;
import java.net.URL;

public class SyncThread extends Thread implements Runnable {
    public HttpProxyCacheServer proxy;
    public Promise promise;
    public String url;

    public SyncThread(HttpProxyCacheServer proxy, Promise promise, String url) {
        this.proxy = proxy;
        this.promise = promise;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            String spec = this.proxy.getProxyUrl(this.url);
            URL proxyUrl = new URL(spec);
            InputStream inputStream = proxyUrl.openStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {}
            this.promise.resolve(spec);
        } catch (Exception ex) {
            this.promise.reject(ex);
        }
    }
}
