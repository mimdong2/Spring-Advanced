package hello.advanced.app.v3.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        TraceStatus traceStatus = null;
        try {

            traceStatus = trace.begin("OrderService.request()");
            orderRepository.save(itemId);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;    // 예외를 꼭 다시 던져줘야 한다. (로그는 서비스 흐름에 영향을 주면 안되기 때문이다.)
        }
    }
}
