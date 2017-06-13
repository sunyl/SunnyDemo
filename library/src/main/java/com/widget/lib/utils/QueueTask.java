package com.widget.lib.utils;

import java.util.ArrayList;

/**
 * Created by zuijinbuzai on 2016/4/22.
 */
public abstract class QueueTask<T> {

    private ArrayList<T> mTasks = new ArrayList<>();
    private boolean mIsExecute = false;

    public void addTask(T task) {
        mTasks.add(task);
        checkForNextTask(false);
    }

    public void removeAll() {
        mTasks.clear();
    }

    public void checkForNextTask() {
        checkForNextTask(true);
    }

    public void checkForLastTask() {
        checkForLastTask(true);
    }

    /**
     * 在任务结束后，需要调用者手动调用
     * @param must
     */
    private void checkForLastTask(boolean must) {
        if(mTasks.size() > 0) {
            if(must || !mIsExecute) {
                mIsExecute = true;
                T task = mTasks.remove(0);
                mTasks.clear();
                doTask(task);
            }
        } else {
            mIsExecute = false;
        }
    }

    /**
     * 在任务结束后，需要调用者手动调用
     * @param must
     */
    private void checkForNextTask(boolean must) {
        if(mTasks.size() > 0) {
            if(must || !mIsExecute) {
                mIsExecute = true;
                T task = mTasks.remove(0);
                doTask(task);
            }
        } else {
            mIsExecute = false;
        }
    }

    public int taskSize() {
        return mTasks.size();
    }

    public boolean isRun() {
        return mIsExecute;
    }

    public void clear() {
        mTasks.clear();
    }

    public abstract void doTask(T task);
}