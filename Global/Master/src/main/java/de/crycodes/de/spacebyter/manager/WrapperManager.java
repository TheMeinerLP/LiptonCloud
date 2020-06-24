package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class WrapperManager {

    private List<WrapperMeta> wrapperList = new ArrayList<>();

    public void registerWrapper(WrapperMeta wrapperMeta) {
        if(!(wrapperList.contains(wrapperMeta)))
            wrapperList.add(wrapperMeta);
    }

    public boolean isWrapperAvailable(String wrapperID) {
        AtomicBoolean result = new AtomicBoolean(false);
        wrapperList.forEach(wrappers -> {
            if(wrappers.getWrapperConfig().getWrapperId().equalsIgnoreCase(wrapperID) && wrappers.isAvailable()) {
                result.set(true);
            }
        });
        return result.get();
    }

    public void unregisterWrapper(WrapperMeta wrapperMeta){
        if(wrapperList.contains(wrapperMeta))
            wrapperList.remove(wrapperMeta);
    }

}
