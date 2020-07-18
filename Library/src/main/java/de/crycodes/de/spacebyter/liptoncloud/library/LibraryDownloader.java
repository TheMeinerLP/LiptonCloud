package de.crycodes.de.spacebyter.liptoncloud.library;

import de.crycodes.de.spacebyter.liptoncloud.utils.Library;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Coded By CryCodes
 * Class: LibraryDownloader
 * Date : 18.07.2020
 * Time : 11:37
 * Project: LiptonCloud
 */

public class LibraryDownloader {

    private final String url = "https://repo1.maven.org/maven2/";

    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;

    public void downloadLibrary(final Library library, File location) throws IOException {
        try {
            if (new File(location + "/" + library.getName() + "-" + library.getVersion() + ".jar").exists())
                return;
            System.out.println("Downloading Library '" + library.getName() + "' from Maven Repo with URL: '" + this.format(library) + "'");
            httpURLConnection = (HttpURLConnection) new URL(this.format(library)).openConnection();
            httpURLConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64)AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

            Files.copy(inputStream, Paths.get(location + "/" + library.getName() + "-" + library.getVersion() + ".jar"), StandardCopyOption.REPLACE_EXISTING);

            httpURLConnection.disconnect();

            System.out.println("Library '" + library.getName() + "' was downloaded successfully!");
        } catch (IOException ex) {
            httpURLConnection.disconnect();
            inputStream.close();
            ex.printStackTrace();
        }

    }

    private String format(final Library dependency) {
        return this.url + dependency.getArtifactId().replace(".", "/") + "/" + dependency.getName() + "/" + dependency.getVersion() + "/" + dependency.getName() + "-" + dependency.getVersion() + ".jar";
    }



}
