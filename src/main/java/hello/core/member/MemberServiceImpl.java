package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

  //인터페이스만이 아닌 구현체(MemoryMemberRepository)까지 의존했으므로 이는 DIP 위반.
  //private final MemberRepository memberRepository = new MemoryMemberRepository();
  private MemberRepository memberRepository;

  //AppConfig에서 memberRepository가 singleton이다. 의 확인을 위한 테스트코드
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
