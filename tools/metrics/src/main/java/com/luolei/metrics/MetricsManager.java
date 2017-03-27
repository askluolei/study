package com.luolei.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

/**
 * metrics 几个基本结构
 * 
 * @author luolei
 */
public class MetricsManager {

	//Metrics 的核心类  一个应用一般只要一个
	private static final MetricRegistry metrics = new MetricRegistry();
	
	/*
	 * Counter  统计次数用的  inc dec
	 */
	public static final Counter counter = metrics.counter("counter");
	
	/*
	 * 获取瞬时值用的
	 */
	public static final Gauge gauge = metrics.gauge("gauge", () -> {
		return new Gauge<Integer>() {
			@Override
			public Integer getValue() {
				return 1;
			}
			
		};
	});
	
	/*
	 * mark一下  统计频率用的
	 */
	public static final Meter meter = metrics.meter("meter");
	
	/*
	 * 统计线性值用的update 
	 */
	public static final Histogram histogram = metrics.histogram("histogram");
	
	/*
	 * 统计时间开销
	 */
	public static final Timer timer = metrics.timer("timer");
}
