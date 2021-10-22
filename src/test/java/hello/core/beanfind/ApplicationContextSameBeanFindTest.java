package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

class ApplicationContextSameBeanFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회 -> 같은타입이 2 이상 -> 오류 발생")
  void findBeanByTypeDuplicate() {
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("타입으로 조회 -> 같은타입이 2 이상 -> 빈 이름과 타입으로 찾기 -> 오류X")
  void findBeanByNames() {
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
    System.out.println("memberRepository = " + memberRepository);

    org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    
  }
  
  @Test
  @DisplayName("특정타입으로 모두 조회하기")
  void findAllBeanByTypes() {
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    System.out.println("Map = " + beansOfType);
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value = " + beansOfType.get(key));
    }
    org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

  }
  

  @Configuration
  static class SameBeanConfig {
    @Bean
    public MemberRepository memberRepository() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}
