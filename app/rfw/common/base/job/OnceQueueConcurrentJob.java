package rfw.common.base.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.collections.CollectionUtils;

import play.jobs.Job;

/**
 * 
 * @title OnceCycleJob.java
 * @author lizhong.chen
 * @data 2014-1-17下午5:47:35
 * @description 一次循环数据处理基础job -需要实现getAllData()
 * @version V1.0
 * 
 */
public abstract class OnceQueueConcurrentJob<T> extends Job {

    private Map<Integer, Queue<T>> queues = new HashMap<Integer, Queue<T>>();

    private int queueNum = 4;

    public OnceQueueConcurrentJob(int queueNum) {
        if (queueNum > 0) {
            this.queueNum = queueNum;
        }

        for (int i = 0; i < this.queueNum; i++) {
            // 初始化队列
            Queue<T> deltaQueue = new ConcurrentLinkedQueue<T>();
            queues.put(i, deltaQueue);
        }

        for (int i = 0; i < this.queueNum; i++) {
            final int route = i;
            new Thread() {
                public void run() {
                    T t = null;
                    while (true) {
                        // 开启线程
                        while ((t = pollData(route)) != null) {
                            try {
                                dealOne(t);
                            } catch (Exception e) {
                                log(e.getMessage());
                            }
                        }

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            log(e.getMessage());
                        }
                    }
                };
            }.start();
        }
    }

    @Override
    public void doJob() {
        if (!isEnable()) {
            // 开关
            log("job is unenable..");
            return;
        }

        log("job begin...");
        // 初始化数据
        initData();
        // 获取数据
        List<T> list = getAllData();
        if (CollectionUtils.isEmpty(list)) {
            log("job end ... because list is null");
        } else {
            log("num of data got:" + list.size());
            // 进行队列分配
            distDataToQueue(list);
        }
        afterJob();
        log("job end ... finish");
    }

    /**
     * 分布数据至队列
     * 
     * @param data
     */
    public void distDataToQueue(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }
        for (T t : data) {
            addToQueue(t);
        }
    }

    /**
     * 
     * @param route
     * @return
     */
    public T pollData(int route) {
        // if (route >= queues.size()) {
        // return null;
        // }

        return queues.get(route).poll();
    }

    /**
     * 添加至队列
     * 
     * @param route
     * @param t
     */
    public void addToQueue(int route, T t) {
        if (route >= queues.size()) {
            return;
        }
        Queue<T> queue = queues.get(route);
        if (!queue.contains(t)) {
            queue.add(t);
        }
    }

    /**
     * 添加至队列
     * 
     * @param t
     */
    public abstract void addToQueue(T t);

    /**
     * 获取循环数据
     * 
     * @param offset
     * @param limit
     * @return
     */
    public abstract List<T> getAllData();

    /**
     * 进行单个处理
     * 
     * @param t
     * @return
     */
    public abstract boolean dealOne(T t);

    /**
     * 日志记录
     * 
     * @param msg
     */
    public abstract void log(String msg);

    /**
     * 是否开启
     * 
     * @return
     */
    public boolean isEnable() {
        return true;
    }

    /**
     * 初始化数据
     */
    public void initData() {
        // this is a hook
    }

    /**
     * 运行完成后执行
     */
    public void afterJob() {
        // this is a hook
    }

}
