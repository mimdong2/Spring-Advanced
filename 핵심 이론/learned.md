# Learned

## 쓰레드 로컬 - ThreadLocal
### 동시성 문제
싱글톤으로 등록된 스프링 빈이 있다. 멀티 스레드 환경에서 이 객체에 동시에 접근하기 때문에 발생하는 문제이다.
객체를 싱글톤으로 application 상 단 1개만 존재하고, 모든 Thread에서 공유해서 사용하도록 설계되어 있는 경우 동시성 문제가 발생하여 원하는 비즈니스 로직을 수행하지 못하게 된다.  
**즉, 싱글톤 객체의 필드를 변경하며 사용할 때!!! 동시성 문제를 조심해야 한다.**
