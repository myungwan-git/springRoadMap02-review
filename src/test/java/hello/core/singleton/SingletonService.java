package hello.core.singleton;

public class SingletonService {

  private static final SingletonService instance = new SingletonService();

  public static SingletonService getInstance() {
    return instance;
  }

  private SingletonService() {
    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
  }

  public void logic() {
    System.out.println("SingletonService 호출");
  }


}
