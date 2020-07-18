package de.crycodes.de.spacebyter.liptoncloud.library;

import de.crycodes.de.spacebyter.liptoncloud.library.librarys.*;
import de.crycodes.de.spacebyter.liptoncloud.utils.Library;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: JarInjector
 * Date : 18.07.2020
 * Time : 11:36
 * Project: LiptonCloud
 */

public class JarInjector {

    private List<Library> libraries;
    private List<URL> urls;
    private LibraryDownloader libraryDownloader;
    private Class<?>[] classParameters = new Class[]{URL.class};

    //CURRENT
    private File currentLibrary;
    private URLClassLoader currentUrlLoader;
    private Method currentMethod;
    private Field currentField;

    public JarInjector(File location) throws IOException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());

        libraries = new ArrayList<>();
        urls = new ArrayList<>();

        libraries.add(new CassandraAll());
        libraries.add(new CommonsIO());
        libraries.add(new Gson());
        libraries.add(new Guava());
        libraries.add(new Json());
        libraries.add(new Log4J());
        libraries.add(new MongoDriver());
        libraries.add(new SparkCore());
        libraries.add(new SparkNetwork());


        libraryDownloader = new LibraryDownloader();

        for (Library library : libraries){
            libraryDownloader.downloadLibrary(library, location);

            System.out.println("found Library to install: '" + library.getName() + "' trying to inject into System!");

            currentLibrary = new File(location + "/" + library.getName() + "-" + library.getVersion() + ".jar");

            if (currentLibrary.exists()){
                if (currentLibrary.getName().endsWith("jar")){
                    urls.add(currentLibrary.toURI().toURL());
                }
            }

        }
        for (URL url : urls){
            currentField = ClassLoader.class.getDeclaredField("scl");
            currentField.setAccessible(true);
            currentField.set("scl", new URLClassLoader(urls.toArray(new URL[0])));

            currentUrlLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            currentMethod = currentUrlLoader.getClass().getDeclaredMethod("addURL", this.classParameters);
            currentMethod.setAccessible(true);
            currentMethod.invoke(ClassLoader.getSystemClassLoader(), url);
            String[] name = url.getFile().split("/");
            System.out.println("Installed Library '" + name[name.length -1] + "' no error found in injection!");
        }

    }
}
