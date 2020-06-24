package de.crycodes.de.spacebyter.liptoncloud.addon.dependency.util;

import java.io.Serializable;

public abstract class DynamicDependency implements Serializable {

    private static final long serialVersionUID = 697761527505608255L;

    /**
     * The download url of the dependency
     */
    public String downloadUrl = "https://repo1.maven.org/maven2/";

    /**
     * Creates a new dynamic dependency using the default url
     */
    protected DynamicDependency() {
        this(null);
    }

    /**
     * Creates a new constructor of the dependency
     *
     * @param url The download url of the dependency or {@code null} if the cloud should use the
     * default url
     */
    protected DynamicDependency(final String url) {
        if (url != null) {
            this.downloadUrl = url.endsWith("/") ? url : url + "/";
        }
    }

    /**
     * The group Indetifier of the dependency
     *
     * @return The group Indetifier of the dependency
     */
    public abstract String getGroupID();

    /**
     * The name of the dependency
     *
     * @return The name of the dependency
     */
    public abstract String getName();

    /**
     * The version Indetifier of the dependency
     *
     * @return The version Indetifier of the dependency
     */
    public abstract String getVersion();

    public String getDownloadUrl() {
        return this.downloadUrl;
    }
}
