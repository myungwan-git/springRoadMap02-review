package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  //인터페이스만이 아닌 구현체(FixDiscountPolicy)까지 의존했으므로 이는 DIP 위반.
  //private final MemberRepository memberRepository = new MemoryMemberRepository();
  //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

  private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

  //AppConfig에서 memberRepository가 singleton이다. 의 확인을 위한 테스트코드
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
