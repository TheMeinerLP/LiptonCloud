package de.crycodes.de.spacebyter.liptoncloud.utils.files;

import de.crycodes.de.spacebyter.liptoncloud.utils.Require;
import de.crycodes.de.spacebyter.liptoncloud.utils.map.maps.Double;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class DownloadManager implements Serializable {

    /**
     * The used request property of the cloud system
     */
    public static final Double<String, String> REQUEST_PROPERTY = new Double<>("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

    /**
     * Downloads a specific file from the given url and copies it to the correct place
     *
     * @param input The name of the file which should be downloaded, for information only
     * @param url The url of the file which should be downloaded
     * @param to The path where the file should be copied to
     */
    public static void download(final String input, final String url, final String to) {
        Require.requiresNotNull(url, to);
        if (input == null) {
            downloadSilent(url, to);
            return;
        }
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection
                .setRequestProperty(REQUEST_PROPERTY.getFirst(), REQUEST_PROPERTY.getSecond());
            urlConnection.setConnectTimeout(1000);
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            try (InputStream inputStream = urlConnection.getInputStream()) {
                Files.copy(inputStream, Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (final IOException ex) {
        }
    }

    /**
     * Downloads a specific file from the given url and copies it to the correct place
     *
     * @param url The url of the file which should be downloaded
     * @param to The path where the file should be copied to
     */
    public static void downloadSilent(final String url, final String to) {
        Require.requiresNotNull(url, to);
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection
                .setRequestProperty(REQUEST_PROPERTY.getFirst(), REQUEST_PROPERTY.getSecond());
            urlConnection.setConnectTimeout(1000);
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            try (InputStream inputStream = urlConnection.getInputStream()) {
                Files.copy(inputStream, Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (final IOException ex) {
        }
    }

    /**
     * Downloads a specific file from the given url and copies it to the correct place and disconnects
     * the connection
     *
     * @param input The name of the file which should be downloaded, for information only
     * @param url The url of the file which should be downloaded
     * @param to The path where the file should be copied to
     */
    public static void downloadAndDisconnect(final String input, final String url,
        final String to) {
        Require.requiresNotNull(url, to);
        if (input == null) {
            downloadSilentAndDisconnect(url, to);
            return;
        }
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection
                .setRequestProperty(REQUEST_PROPERTY.getFirst(), REQUEST_PROPERTY.getSecond());
            urlConnection.setConnectTimeout(1000);
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            try (InputStream inputStream = urlConnection.getInputStream()) {
                Files.copy(inputStream, Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
            }

            ((HttpURLConnection) urlConnection).disconnect();

        } catch (final IOException ex) {

        }
    }

    /**
     * Downloads a specific file from the given url and copies it to the correct place and disconnects
     * the connection
     *
     * @param url The url of the file which should be downloaded
     * @param to The path where the file should be copied to
     */
    public static void downloadSilentAndDisconnect(final String url, final String to) {
        Require.requiresNotNull(url, to);
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection
                .setRequestProperty(REQUEST_PROPERTY.getFirst(), REQUEST_PROPERTY.getSecond());
            urlConnection.setConnectTimeout(1000);
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            try (InputStream inputStream = urlConnection.getInputStream()) {
                Files.copy(inputStream, Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
            }

            ((HttpURLConnection) urlConnection).disconnect();
        } catch (final IOException ex) {
        }
    }
}
