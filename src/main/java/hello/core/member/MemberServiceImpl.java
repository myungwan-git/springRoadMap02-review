package hello.core.member;

public class MemberServiceImpl implements MemberService{

  //인터페이스만이 아닌 구현체(MemoryMemberRepository)까지 의존했으므로 이는 DIP 위반.
  //private final MemberRepository memberRepository = new MemoryMemberRepository();
  private MemberRepository memberRepository;

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
