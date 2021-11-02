package hello.core.singleton;

import hello.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    AppConfig appConfig = ac.getBean(AppConfig.class);
    System.out.println("appConfig = " + appConfig);

    MemberRepository memberRepository = ac.getBean(MemberRepository.class);
    MemoryMemberRepository memoryMemberRepository = ac.getBean(MemoryMemberRepository.class);
    System.out.println("memberRepository = " + memberRepository);
    System.out.println("memoryMemberRepository = " + memoryMemberRepository);
    Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    Assertions.assertThat(memoryMemberRepository).isInstanceOf(MemberRepository.class);

    MemberServiceImpl memberServiceImpl = ac.getBean(MemberServiceImpl.class);
    OrderServiceImpl orderServiceImpl = ac.getBean(OrderServiceImpl.class);
    MemberRepository memberRepository1 = memberServiceImpl.getMemberRepository();
    MemberRepository memberRepository2 = orderServiceImpl.getMemberRepository();
    System.out.println("memberRepository1 = " + memberRepository1);
    System.out.println("memberRepository2 = " + memberRepository2);
    Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
  }

}
