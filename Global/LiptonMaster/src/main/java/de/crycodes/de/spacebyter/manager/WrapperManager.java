package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.utils.CallBack;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldNotBeNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class WrapperManager {

    private List<WrapperMeta> wrapperList = new ArrayList<>();

    private final LiptonMaster liptonMaster;

    public WrapperManager(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }

    public void registerWrapper(@ShouldNotBeNull  WrapperMeta wrapperMeta, CallBack<Boolean> isAuthenticated) {
        if (LiptonMaster.getInstance().getWrapperConfig().getWrapperByID(wrapperMeta.getWrapperConfig().getWrapperId()) != null) {
            if (!(wrapperList.contains(wrapperMeta)))
                wrapperList.add(wrapperMeta);
            liptonMaster.getColouredConsoleProvider().info("Registered new Wrapper: " + wrapperMeta.getWrapperConfig().getWrapperId());
            isAuthenticated.accept(true);
        } else {
            LiptonMaster.getInstance().getColouredConsoleProvider().error("Wrapper Tried to Connect but found no WrapperGroup -> (create <wrapper>)!");
            isAuthenticated.accept(false);
        }
    }

    public boolean isWrapperAvailable( @ShouldNotBeNull String wrapperID) {
        AtomicBoolean result = new AtomicBoolean(false);
        wrapperList.forEach(wrappers -> {
            if(wrappers.getWrapperConfig().getWrapperId().equalsIgnoreCase(wrapperID) && wrappers.isAvailable()) {
                result.set(true);
            }
        });
        return result.get();
    }
    public WrapperMeta getBestFreeWrapper(){
        if (!this.wrapperList.isEmpty())
            return this.wrapperList.get(0);
        else {
            if (liptonMaster.getMasterConfig().isDebugMode())
                LiptonMaster.getInstance().getColouredConsoleProvider().error("No Wrapper Available -> (create <wrapper>)!");
        }
        return null;
    }

    public void unregisterWrapper( @ShouldNotBeNull WrapperMeta wrapperMeta){
        if(wrapperList.contains(wrapperMeta))
            wrapperList.remove(wrapperMeta);
        liptonMaster.getColouredConsoleProvider().info("Unregistered Wrapper: " + wrapperMeta.getWrapperConfig().getWrapperId());
    }

}
