package de.crycodes.de.spacebyter.liptoncloud.utils;

import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: Library
 * Date : 18.07.2020
 * Time : 11:36
 * Project: LiptonCloud
 */

public class Library {

    public final String artifactId;
    public final String groupID;
    public final String version;

    public Library(String artifactId, String groupID, String version) {
        this.artifactId = artifactId;
        this.groupID = groupID;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Library{" +
                "artifactId='" + artifactId + '\'' +
                ", groupID='" + groupID + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(artifactId, library.artifactId) &&
                Objects.equals(groupID, library.groupID) &&
                Objects.equals(version, library.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifactId, groupID, version);
    }

    public String getArtifactId() {
        return groupID;
    }

    public String getName() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }
}
