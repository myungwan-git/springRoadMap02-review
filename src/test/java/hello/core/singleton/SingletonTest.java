package hello.core.singleton;

import hello.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

  @Test
  @DisplayName("순수 DI 컨테이너의 싱글톤이 아닌것을 확인")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();

    MemberService memberService1 = appConfig.memberService();
    MemberService memberService2 = appConfig.memberService();

    Assertions.assertThat(memberService1).isNotSameAs(memberService2);

  }

  @Test
  @DisplayName("스프링 컨테이너의 싱글톤을 확인")
  void springContainer() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    Assertions.assertThat(memberService1).isSameAs(memberService2);

  }


  @Test
  @DisplayName("순수 자바로 싱클톤 패턴 적용")
  public void singletonServiceTest() {
    SingletonService singletonService1 = SingletonService.getInstance();
    SingletonService singletonService2 = SingletonService.getInstance();

    Assertions.assertThat(singletonService1).isSameAs(singletonService2);

    singletonService1.logic();
    singletonService2.logic();
  }



}
