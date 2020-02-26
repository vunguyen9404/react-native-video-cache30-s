package com.reactlibrary;

import android.telecom.Call;

import com.danikula.videocache.HttpProxyCacheServer;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class VideoCache30SModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private HttpProxyCacheServer proxy;

    public VideoCache30SModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "VideoCache30S";
    }

    @ReactMethod
    public void convert(
            String url,
            Promise promise) {
        promise.resolve(this.getProxy().getProxyUrl(url));
    }

    @ReactMethod
    public void syncBackground(String url, Promise promise){
        try {
            backgroundSyncVideo(url, promise);
        } catch (IOException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void isCached(String url, Promise promise) {
        if (this.proxy == null) promise.resolve(false);
        promise.resolve(this.proxy.isCached(url));
    }

    public void backgroundSyncVideo(String url, Promise promise) throws IOException {
        String spec = this.getProxy().getProxyUrl(url);
        URL proxyUrl = new URL(spec);
        InputStream inputStream = proxyUrl.openStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {}
        promise.resolve(spec);
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void helloWorld(String name, Callback callback) {
        callback.invoke("Hello " + name + "! i reply from native module!");
    }

    public HttpProxyCacheServer getProxy() {
        if (this.proxy == null) {
            this.proxy = new HttpProxyCacheServer.Builder(this.reactContext)
                    .maxCacheFilesCount(20)
                    .maxCacheSize(1073741824)
                    .build();
        }

        return this.proxy;
    }
}
