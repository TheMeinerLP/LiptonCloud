package de.crycodes.de.spacebyter.screen;

import java.io.File;
import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: Screen
 * Date : 16.07.2020
 * Time : 17:23
 * Project: LiptonCloud
 */

public class Screen {

    private final Thread thread;
    private final Process process;
    private final File serverDir;

    //<editor-fold desc="Screen">
    public Screen(Thread thread, Process process, File serverDir) {
        this.thread = thread;
        this.process = process;
        this.serverDir = serverDir;
    }
    //</editor-fold>

    //<editor-fold desc="toString">
    @Override
    public String toString() {
        return "Screen{" +
                "thread=" + thread +
                ", process=" + process +
                ", serverDir=" + serverDir +
                '}';
    }
    //</editor-fold>

    //<editor-fold desc="equals">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screen screen = (Screen) o;
        return Objects.equals(thread, screen.thread) &&
                Objects.equals(process, screen.process) &&
                Objects.equals(serverDir, screen.serverDir);
    }
    //</editor-fold>

    //<editor-fold desc="hashCode">
    @Override
    public int hashCode() {
        return Objects.hash(thread, process, serverDir);
    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public File getServerDir() {
        return serverDir;
    }

    public Thread getThread() {
        return thread;
    }

    public Process getProcess() {
        return process;
    }
    //</editor-fold>
}
