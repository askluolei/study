package com.luolei.metrics.ch01;

import com.codahale.metrics.*;
import java.util.concurrent.TimeUnit;

/**
 * @author luolei
 * @date 2017-03-17 15:35
 */
public class GetStarted {

    static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String args[]) {
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        wait5Seconds();
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    static void wait5Seconds() {
        try {
            Thread.sleep(5*1000);
        }
        catch(InterruptedException e) {}
    }
}
