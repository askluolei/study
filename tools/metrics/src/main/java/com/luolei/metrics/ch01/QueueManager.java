package com.luolei.metrics.ch01;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author luolei
 * @date 2017-03-17 15:40
 */
public class QueueManager {
    private final Queue queue;

    public QueueManager(MetricRegistry metrics, String name) {
        this.queue = new LinkedList();
        metrics.register(MetricRegistry.name(QueueManager.class, name, "size"),
                new Gauge<Integer>() {
                    @Override
                    public Integer getValue() {
                        return queue.size();
                    }
                });
    }
}
