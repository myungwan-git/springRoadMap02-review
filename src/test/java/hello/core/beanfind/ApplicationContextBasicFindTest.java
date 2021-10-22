package hello.core.beanfind;

import hello.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findByBeanName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }
  @Test
  @DisplayName("빈 이름없이 빈 타입으로만 조회")
  void findByBeanType() {
    MemberService memberService = ac.getBean(MemberService.class);
    System.out.println("memberService = " + memberService);
    Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }
  @Test
  @DisplayName("빈 이름과 빈 구현타입으로 조회")
  void findByBeanName2() {
    MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);
    System.out.println("memberService = " + memberService);
    Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }
  @Test
  @DisplayName("빈 이름으로 조회 실패 케이스")
  void findByBeanNameFail() {
    org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xx", MemberService.class));
  }


}
