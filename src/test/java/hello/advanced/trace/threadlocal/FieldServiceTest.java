package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info(">>>>>> main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");
        Thread threadA = new Thread(userA);
        threadA.setName("THREAD-A");
        Thread threadB = new Thread(userB);
        threadB.setName("THREAD-B");

        threadA.start();
//        sleep(2000);        // 동시성 문제 발생하지 않는다. (threadA 끝나고 threadB가 수행되게 했기 때문)
        sleep(500);     // 동시성 문제 발생!!!!
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