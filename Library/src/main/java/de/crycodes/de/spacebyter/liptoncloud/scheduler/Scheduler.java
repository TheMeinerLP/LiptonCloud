package de.crycodes.de.spacebyter.liptoncloud.scheduler;

/*
 * Created by CryCodes on 14.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Scheduler {
    protected final ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap();
    protected final Random random = new Random();



    public int scheduleAsync(Runnable run) {
        int taskid = this.random.nextInt(2147483647);
        this.tasks.put(Integer.valueOf(taskid), new Task(run, taskid, 0L, -1L, this));
        return taskid;
    }


    public int scheduleAsyncDelay(Runnable run, long delay) {
        int taskid = this.random.nextInt(2147483647);
        this.tasks.put(Integer.valueOf(taskid), new Task(run, taskid, delay, -1L, this));
        return taskid;
    }


    public int scheduleAsyncWhile(Runnable run, long delay, long repeat) {
        int taskid = this.random.nextInt(2147483647);
        this.tasks.put(Integer.valueOf(taskid), new Task(run, taskid, delay, repeat, this));
        return taskid;
    }



    public int getCount() { return this.tasks.size(); }




    public void cancelTask(int id) {
        if (this.tasks.containsKey(Integer.valueOf(id))) {

            ((Task)this.tasks.get(Integer.valueOf(id))).stop();
            this.tasks.remove(Integer.valueOf(id));
        }
    }


    public void cancelAllTasks() {
        for (Iterator iterator = this.tasks.keySet().iterator(); iterator.hasNext(); ) { int task = ((Integer)iterator.next()).intValue();

            cancelTask(task); }

    }
}
