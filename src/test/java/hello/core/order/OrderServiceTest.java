package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();

  @Test
  void findOrder() {
    Member member = new Member(1L,"memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(1L, "ItemA", 10000);

    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }


}
