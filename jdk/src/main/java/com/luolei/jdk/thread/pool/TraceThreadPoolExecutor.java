package com.luolei.jdk.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展JDK线程池，获取完整的异常堆栈信息
 * @author 罗雷
 * @date 2016年9月13日
 * @time 上午7:38:28
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	public void execute(Runnable command) {
		super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
	}
	
	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
	}
	
	
	private Exception clientTrace() {
		return new Exception("Client stack trace");
	}
	
	private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
		return new Runnable() {
			
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					clientStack.printStackTrace();
					throw e;
				}
			}
		};
	}

	public static void main(String[] args) {
		ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
		
		for (int i = 0; i < 5; i++) {
			pools.execute(new DivTask(100, i));
		}
	}
}
