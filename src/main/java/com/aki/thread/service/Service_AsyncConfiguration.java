package com.aki.thread.service;

import java.util.concurrent.CompletableFuture;

public interface Service_AsyncConfiguration {
    CompletableFuture<String> runThread(String name);
}
