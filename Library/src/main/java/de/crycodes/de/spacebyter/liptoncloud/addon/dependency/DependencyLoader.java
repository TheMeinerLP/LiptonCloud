package de.crycodes.de.spacebyter.liptoncloud.addon.dependency;

import de.crycodes.de.spacebyter.liptoncloud.addon.dependency.util.DynamicDependency;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public final class DependencyLoader implements Serializable {

    public static void loadDependency(DynamicDependency dynamicDependency) {
        try {
            URL result = downloadLib(dynamicDependency);
            if (result == null) {
                return;
            }

            URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            try {
                Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addURL.setAccessible(true);
                addURL.invoke(urlClassLoader, result);
            } catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }

            final String[] name = result.getFile().split("/");
            System.out.println(
                "Successfully installed dependency " + name[name.length - 1].replace(".jar", ""));
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    private static URL downloadLib(final DynamicDependency dependency)
        throws MalformedURLException {
        if (Files.exists(Paths
            .get("libraries/" + dependency.getName() + "-" + dependency.getVersion() + ".jar"))) {
            return new File(
                "libraries/" + dependency.getName() + "-" + dependency.getVersion() + ".jar")
                .toURI().toURL();
        }

        deleteExistingDependency(dependency);

        try {
            System.out.println(
                "Downloading dependency " + dependency.getName() + " from \"" + format(dependency)
                    + "\"...");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(format(dependency))
                .openConnection();
            httpURLConnection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();

            try (InputStream inputStream = httpURLConnection.getInputStream()) {
                Files.copy(inputStream, Paths.get(
                    "libraries/" + dependency.getName() + "-" + dependency.getVersion() + ".jar"),
                    StandardCopyOption.REPLACE_EXISTING);
            }

            httpURLConnection.disconnect();
            return new File(
                "libraries/" + dependency.getName() + "-" + dependency.getVersion() + ".jar")
                .toURI().toURL();
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static void deleteExistingDependency(DynamicDependency dependency) {
        File[] files = new File("libraries").listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.getName().contains(dependency.getName()) && file.getName().endsWith(".jar")) {
                if (file.delete()) {
                    break;
                }
            }
        }
    }

    private static String format(final DynamicDependency dependency) {
        return dependency.downloadUrl + dependency.getGroupID().replace(".", "/") + "/" +
            dependency.getName() + "/" + dependency.getVersion() + "/" + dependency.getName() + "-"
            + dependency.getVersion() + ".jar";
    }
}
