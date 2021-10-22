package hello.core;

import hello.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

  public static void main(String[] args) {
    //Spring 컨테이너로 변경해보자.
    //AppConfig appConfig = new AppConfig();
    //MemberService memberService = appConfig.memberService();
    //OrderService orderService = appConfig.orderService();

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


    Member member = new Member(1L, "MemberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(1L, "ItemA", 20000);
    System.out.println("order = " + order);
  }

}
