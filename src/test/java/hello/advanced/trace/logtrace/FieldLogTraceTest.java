package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {

    FieldLogTrace fieldLogTrace = new FieldLogTrace();

    @Test
    void begin_end() {
        TraceStatus traceStatus1 = fieldLogTrace.begin("START 1");
        TraceStatus traceStatus2 = fieldLogTrace.begin("START 2");
        fieldLogTrace.end(traceStatus2);
        fieldLogTrace.end(traceStatus1);
    }

    @Test
    void begin_exception() {
        TraceStatus traceStatus1 = fieldLogTrace.begin("START 1");
        TraceStatus traceStatus2 = fieldLogTrace.begin("START 2");
        fieldLogTrace.exception(traceStatus2, new IllegalStateException());
        fieldLogTrace.exception(traceStatus1, new IllegalStateException());
    }

}