package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraveV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 helloTrace = new HelloTraceV2();
        TraceStatus traceStatus1 = helloTrace.begin("시작이예요1");
        TraceStatus traceStatus2 = helloTrace.beginSync(traceStatus1.getTraceId(), "시작이예요2");
        helloTrace.end(traceStatus2);
        helloTrace.end(traceStatus1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 helloTrace = new HelloTraceV2();
        TraceStatus traceStatus1 = helloTrace.begin("시작이예요1");
        TraceStatus traceStatus2 = helloTrace.beginSync(traceStatus1.getTraceId(), "시작이예요2");
        helloTrace.exception(traceStatus1, new IllegalArgumentException());
        helloTrace.exception(traceStatus2, new IllegalArgumentException());
    }


}
