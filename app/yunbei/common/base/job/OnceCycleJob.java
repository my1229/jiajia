/**
 * 
 */
package yunbei.common.base.job;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;

import play.jobs.Job;

/**
 * 
 * @title OnceCycleJob.java
 * @author ChenLz
 * @data 2014-1-17下午5:47:35
 * @description 一次循环数据处理基础job -需要实现getCycleData(int,int)
 * @version V1.0
 * 
 */
public abstract class OnceCycleJob<T> extends Job {

    /**
     * 开始
     */
    protected int offset = 0;
    /**
     * 步长
     */
    protected int limit = 1024;

    @Override
    public void doJob() throws Exception {
        if (!isEnable()) {
            getLog().info("job is unenable..");
            return;
        }
        getLog().info("job begin...");
        initData();
        offset = 0;
        boolean hasNext = true;
        while (hasNext) {
            List<T> list = getCycleData(offset, limit);
            if (CollectionUtils.isEmpty(list)) {
                getLog().info("job end ... because list is null");
                return;
            }
            if (list.size() < limit) {
                hasNext = false;
            }
            // offset += limit;
            setOffset();
            for (T t : list) {
                try {
                    dealOne(t);
                } catch (Exception e) {
                    // 对单个处理进行异常捕获，防止中途异常,导致程序异常中断
                    getLog().info(e.getMessage());
                }
            }
        }
        getLog().info("job end ... finish");
    }

    /**
     * 设置每次循环后数据获取的offset
     */
    public void setOffset() {
        offset += limit;
    }

    /**
     * 获取循环数据
     * 
     * @param offset
     * @param limit
     * @return
     */
    public abstract List<T> getCycleData(int offset, int limit);

    /**
     * 处理单个
     * 
     * @param t
     * @return
     */
    public abstract boolean dealOne(T t);

    /**
     * 日志
     * 
     * @param msg
     */
    // public abstract void log(String msg);

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

}
