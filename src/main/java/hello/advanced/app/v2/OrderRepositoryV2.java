package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 helloTrace;

    public void save(TraceId traceId, String itemId) {

        TraceStatus traceStatus = null;
        try {
            traceStatus = helloTrace.beginSync(traceId, "OrderRepository.request()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            helloTrace.end(traceStatus);
        } catch (Exception e) {
            helloTrace.exception(traceStatus, e);
            throw e;    // 예외를 꼭 다시 던져줘야 한다. (로그는 서비스 흐름에 영향을 주면 안되기 때문이다.)
        }


    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
