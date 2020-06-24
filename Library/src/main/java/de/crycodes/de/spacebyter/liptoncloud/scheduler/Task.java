package de.crycodes.de.spacebyter.liptoncloud.scheduler;

/*
 * Created by CryCodes on 14.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


public class Task extends Thread {
    private Runnable task;
    private int taskId;

    public Runnable getTask() { return this.task; } private long delay; private long repeat; private Scheduler scheduler;
    public int getTaskId() { return this.taskId; }
    public long getDelay() { return this.delay; }
    public long getRepeat() { return this.repeat; }
    public Scheduler getScheduler() { return this.scheduler; }

    protected Task(Runnable run, int id, long delay, long repeat, Scheduler scheduler) {
        this.task = run;
        this.taskId = id;
        this.delay = delay;
        this.repeat = repeat;
        this.scheduler = scheduler;
        start();
    }




    public void run() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        while (this.repeat != -1L) {

            this.task.run();

            try {
                Thread.sleep(this.repeat);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

        if (this.repeat == -1L)
        {
            this.task.run();
        }

        this.scheduler.tasks.remove(Integer.valueOf(this.taskId));
        this.scheduler.cancelTask(this.taskId);
    }
}
