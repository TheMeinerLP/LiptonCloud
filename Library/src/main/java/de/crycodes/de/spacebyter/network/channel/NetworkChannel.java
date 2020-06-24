package de.crycodes.de.spacebyter.network.channel;

import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: NetworkChannel
 * Date : 29.05.2020
 * Time : 14:44
 * Project: CrazyCloud - V2
 */

public class NetworkChannel {

    private final Identifier identifyer;
    private final Provider provider;

    public NetworkChannel(Identifier identifyer, Provider provider) {
        this.identifyer = identifyer;
        this.provider = provider;
    }

    public Identifier getIdentifyer() {
        return identifyer;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getChannelID(){
        return  provider.getProv() + "::" + identifyer.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkChannel that = (NetworkChannel) o;
        return Objects.equals(identifyer, that.identifyer) &&
                Objects.equals(provider, that.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifyer, provider);
    }

    @Override
    public String toString() {
        return "NetworkChannel{" +
                "identifyer='" + identifyer + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
