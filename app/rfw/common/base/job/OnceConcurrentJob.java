/**
 * 
 */
package rfw.common.base.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;

import play.jobs.Job;
import rfw.common.base.pool.PYFutureTaskPool;

/**
 * 
 * @title OnceCycleJob.java
 * @author lizhong.chen
 * @data 2014-1-17下午5:47:35
 * @description 一次循环数据--concurrent处理基础job -需要实现getAllData()
 *              在子类中构造类实现dealFutureTask() {@link IConcurrentExecutor}
 * @version V1.0
 * 
 */
public abstract class OnceConcurrentJob<T, FR> extends Job {

    // 默认线程数
    private static int DEFAULT_SIZE = 3;

    // 当前线程数
    private int concurrentSize = DEFAULT_SIZE;

    // 并发处理器
    private IConcurrentExecutor<T, FR> executor;

    // 线程池
    private PYFutureTaskPool<T> futureTaskPool = new PYFutureTaskPool<T>(concurrentSize);

    public OnceConcurrentJob(int concurrentSize, IConcurrentExecutor<T, FR> executor) {
        if (concurrentSize > 0) {
            this.concurrentSize = concurrentSize;
        }
        // 多线程
        this.futureTaskPool = new PYFutureTaskPool<T>(concurrentSize);

        this.executor = executor;
    }

    @Override
    public void doJob() {
        if (!isEnable()) {
            getLog().info("job is unenable..");
            return;
        }
        getLog().info("job begin...");
        initData();
        List<T> list = getAllData();

        if (CollectionUtils.isEmpty(list)) {
            getLog().info("job end ... because list is null");
        } else {
            getLog().info("got data num:{}", list.size());
            concurrentDeal(list);
        }
        afterJob();
        getLog().info("job end ... finish");
    }

    /**
     * 将list进行分任务处理
     * 
     * @param list
     */
    // public void concurrentDeal(List<T> list) {
    // int total = list.size();
    // int sigleCount = total / this.concurrentSize;
    //
    // if (sigleCount == 0) {
    // sigleCount = 1;
    // }
    //
    // List<FutureTask<T>> tasks = new ArrayList<FutureTask<T>>();
    // for (int i = 0; i < total;) {
    // int end = (i + sigleCount) > total ? total : (i + sigleCount);
    // FutureTask<T> task = this.futureTaskPool.submit(new
    // ConcurrentTask(list.subList(i, end), executor));
    // tasks.add(task);
    // i = end;
    // }
    // dealFutureTask(tasks);
    //
    // }

    public void concurrentDeal(List<T> list) {
        List<List<T>> conCurrentTasks = distTask(list);

        List<FutureTask<T>> tasks = new ArrayList<FutureTask<T>>();
        // for (int i = 0; i < total;) {
        // int end = (i + sigleCount) > total ? total : (i + sigleCount);
        // FutureTask<T> task = this.futureTaskPool.submit(new
        // ConcurrentTask(list.subList(i, end), executor));
        // tasks.add(task);
        // i = end;
        // }

        if (CollectionUtils.isNotEmpty(conCurrentTasks)) {
            for (List<T> concurrentTask : conCurrentTasks) {
                if (CollectionUtils.isNotEmpty(concurrentTask)) {
                    FutureTask<T> task = this.futureTaskPool.submit(new ConcurrentTask(concurrentTask, executor));
                    tasks.add(task);
                }
            }
        }

        dealFutureTask(tasks);
    }

    /**
     * 分配任务
     * 
     * @param list
     * @param sigleCount
     * @return
     */
    public List<List<T>> distTask(List<T> list) {
        List<List<T>> tasks = new ArrayList<List<T>>();
        int total = list.size();
        int sigleCount = total / this.concurrentSize;

        if (sigleCount == 0) {
            sigleCount = 1;
        }
        for (int i = 0; i < total;) {
            int end = (i + sigleCount) > total ? total : (i + sigleCount);
            tasks.add(list.subList(i, end));
            i = end;
        }
        return tasks;
    }

    public PYFutureTaskPool<T> getTaskPool() {
        return this.futureTaskPool;
    }

    public IConcurrentExecutor<T, FR> getExecutor() {
        return this.executor;
    }

    public int getConcurrentSize() {
        return concurrentSize;
    }

    /**
     * 
     * 处理多线程的FutureTask
     * 
     * @param tasks
     */
    public void dealFutureTask(List<FutureTask<T>> tasks) {
        for (int i = 0; i < tasks.size(); i++) {

            while (!tasks.get(i).isDone()) {
                // 等待处理结束
                // hold
            }

            getLog().info("finsh deal task:" + (i + 1) + "/" + tasks.size());
        }
    }

    /**
     * 获取循环数据
     * 
     * @param offset
     * @param limit
     * @return
     */
    public abstract List<T> getAllData();

    // /**
    // * 进行单个处理
    // *
    // * @param t
    // * @return
    // */
    // public abstract boolean dealOne(T t);

    /**
     * 日志记录
     * 
     * @param msg
     */

    public abstract Logger getLog();

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

    public class ConcurrentTask<T, FR> implements Callable<Collection<FR>> {

        private Collection<T> subObjects;
        IConcurrentExecutor<T, FR> executor;

        public ConcurrentTask(Collection<T> subObjects, IConcurrentExecutor<T, FR> executor) {
            this.subObjects = subObjects;
            this.executor = executor;
        }

        @Override
        public Collection<FR> call() throws Exception {
            if (subObjects == null) {
                return null;
            }

            Collection<FR> result = executor.process(subObjects);

            return result;
        }

    }

    public interface IConcurrentExecutor<T, FR> {

        Collection<FR> process(Collection<T> t);

    }

}
