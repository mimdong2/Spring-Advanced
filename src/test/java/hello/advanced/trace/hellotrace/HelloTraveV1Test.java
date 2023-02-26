package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraveV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus traceStatus = helloTraceV1.begin("시작이예요");
        helloTraceV1.end(traceStatus);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus traceStatus = helloTraceV1.begin("시작이예요");
        helloTraceV1.exception(traceStatus, new IllegalArgumentException());
    }


}
