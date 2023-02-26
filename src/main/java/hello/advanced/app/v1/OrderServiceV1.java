package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 helloTrace;

    public void orderItem(String itemId) {

        TraceStatus traceStatus = null;
        try {
            traceStatus = helloTrace.begin("OrderService.request()");
            orderRepository.save(itemId);
            helloTrace.end(traceStatus);
        } catch (Exception e) {
            helloTrace.exception(traceStatus, e);
            throw e;    // 예외를 꼭 다시 던져줘야 한다. (로그는 서비스 흐름에 영향을 주면 안되기 때문이다.)
        }
    }
}
