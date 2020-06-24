package de.crycodes.de.spacebyter.network.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: ChacheCleare
 * Date : 30.05.2020
 * Time : 11:17
 * Project: Networking-Framework
 */

public final class CacheClearer implements Serializable {

    private static List<Cache> caches = new ArrayList<>();

    public CacheClearer() {
        while (!Thread.currentThread().isInterrupted()) {
            caches.forEach(Cache::invalidateAll);
        }
    }

    static void register(Cache cache) {
        caches.add(cache);
    }
}