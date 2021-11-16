package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AutoAppConfigTest {
  @Autowired
  private DiscountPolicy discountPolicy;

  @Test
  void basicScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    //MemberService memberService = ac.getBean(MemberService.class);
    //Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    OrderServiceImpl bean2 = ac.getBean(OrderServiceImpl.class);
    System.out.println("bean2 = " + bean2);



    MemberRepository bean = ac.getBean(MemberRepository.class);
    MemoryMemberRepository bean1 = ac.getBean(MemoryMemberRepository.class);
    System.out.println("bean = " + bean);
    System.out.println("bean1 = " + bean1);
  }

}