
package yunbei.common.base.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PYFutureTaskPool<T> {

    ExecutorService exec;

    public PYFutureTaskPool(int num) {
        exec = Executors.newFixedThreadPool(num);
    }

    public FutureTask<T> submit(Callable<T> t) {
        FutureTask<T> futureTask = new FutureTask<T>(t);
        exec.submit(futureTask);
        return futureTask;
    }

    public void shutdown() {
        this.exec.shutdown();
    }

    public ExecutorService getExec() {
        return exec;
    }

    public void setExec(ExecutorService exec) {
        this.exec = exec;
    }

}
