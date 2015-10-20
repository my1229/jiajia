/**
 * 
 */
package jiajia.common.base.job;

import java.util.List;
import java.util.Queue;

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
public abstract class OnceQueueJob<T> extends Job {

    @Override
    public void doJob() {

        if (!isEnable()) {
            log("job is unenable..");
            return;
        }

        log("job begin...");
        initData();
        List<T> list = getAllData();
        if (CollectionUtils.isEmpty(list)) {
            log("job end ... because list is null");
            return;
        }
        log("num of data got:" + list.size());
        getQueue().addAll(list);
        T t = null;
        while ((t = getQueue().poll()) != null) {
            try {
                dealOne(t);
            } catch (Exception e) {
                // 对单个处理进行异常捕获，防止中途异常,导致程序异常中断
                log(e.getMessage());
            }
        }
        afterJob();
        log("job end ... finish");
    }

    /**
     * 数据队列
     * 
     * @return
     */
    public abstract Queue<T> getQueue();

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
