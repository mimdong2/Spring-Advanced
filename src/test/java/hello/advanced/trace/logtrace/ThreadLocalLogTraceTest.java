package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace logTrace = new ThreadLocalLogTrace();

    @Test
    void begin_end() {
        TraceStatus traceStatus1 = logTrace.begin("START 1");
        TraceStatus traceStatus2 = logTrace.begin("START 2");
        logTrace.end(traceStatus2);
        logTrace.end(traceStatus1);
    }

    @Test
    void begin_exception() {
        TraceStatus traceStatus1 = logTrace.begin("START 1");
        TraceStatus traceStatus2 = logTrace.begin("START 2");
        logTrace.exception(traceStatus2, new IllegalStateException());
        logTrace.exception(traceStatus1, new IllegalStateException());
    }

}