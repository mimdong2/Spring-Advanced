package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 helloTrace;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = helloTrace.begin("OrderController.request()");
            orderService.orderItem(traceStatus.getTraceId(), itemId);
            helloTrace.end(traceStatus);
            return "ok";
        } catch (Exception e) {
            helloTrace.exception(traceStatus, e);
            throw e;    // 예외를 꼭 다시 던져줘야 한다. (로그는 서비스 흐름에 영향을 주면 안되기 때문이다.)
        }
    }
}
