package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info(">>>>>> main start");
        Runnable userA = () -> service.logic("userA");
        Runnable userB = () -> service.logic("userB");
        Thread threadA = new Thread(userA);
        threadA.setName("THREAD-A");
        Thread threadB = new Thread(userB);
        threadB.setName("THREAD-B");

        threadA.start();
        sleep(500);     // ThreadLocal을 사용하여 동시성 문제가 발생하지 않는다.
        threadB.start();

        sleep(3000);        // 메인 스레드 종료 대기
        log.info(">>>>>> main end");
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}