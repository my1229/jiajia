/**
 * 
 */
package jiajia.common.base.job;

import org.slf4j.Logger;

import jiajia.common.base.models.IndexModel;
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
public abstract class OnceIndexJob<T extends IndexModel> extends Job {

    @Override
    public void doJob() {
        if (!isEnable()) {
            getLog().info("job is unenable..");
            return;
        }
        getLog().info("job begin...");
        initData();
        T index = getIndexData();
        try {
            dealOne(index);
        } catch (Exception e) {
            // 对单个处理进行异常捕获，防止中途异常,导致程序异常中断
            getLog().error(e.getMessage(), e);
        }
        afterJob();
        getLog().info("job end ... finish");
    }

    /**
     * 获取循环数据
     * 
     * @param offset
     * @param limit
     * @return
     */
    public abstract T getIndexData();

    /**
     * 进行单个处理
     * 
     * @param t
     * @return
     */
    public abstract boolean dealOne(T t);

    // /**
    // * 日志记录
    // *
    // * @param msg
    // */
    // public abstract void log(String msg);

    /**
     * 获取log
     * 
     * @return
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

}
