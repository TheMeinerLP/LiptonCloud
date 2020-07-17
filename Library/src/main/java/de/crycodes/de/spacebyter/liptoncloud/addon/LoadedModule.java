package de.crycodes.de.spacebyter.liptoncloud.addon;

import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: LoadedModule
 * Date : 17.07.2020
 * Time : 12:03
 * Project: LiptonCloud
 */

public class LoadedModule {

    private final String moduleName;
    private final String version;
    private final String author;
    private final String website;
    private final String dependencies;

    public LoadedModule(String moduleName, String version, String author, String website, String dependencies) {
        this.moduleName = moduleName;
        this.version = version;
        this.author = author;
        this.website = website;
        this.dependencies = dependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadedModule that = (LoadedModule) o;
        return Objects.equals(moduleName, that.moduleName) &&
                Objects.equals(version, that.version) &&
                Objects.equals(author, that.author) &&
                Objects.equals(website, that.website) &&
                Objects.equals(dependencies, that.dependencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, version, author, website, dependencies);
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getWebsite() {
        return website;
    }

    public String getDependencies() {
        return dependencies;
    }

}
