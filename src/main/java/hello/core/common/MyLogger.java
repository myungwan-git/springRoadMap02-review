package hello.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Slf4j
@Component
@Scope(value = "request")
public class MyLogger {

  private String uuid;
  private String requestURL;

  public MyLogger(String requestURL) {
    this.requestURL = requestURL;
  }
  public void log(String message) {
    log.info("uuid = {} ", uuid);
    log.info("requestURL = {} ", requestURL);
    log.info("message = {} ", message);
  }

  @PostConstruct
  public void init() {
    uuid = UUID.randomUUID().toString();
    System.out.println("[" + uuid + "] request scope bean create:" + this);
  }
  @PreDestroy
  public void close() {
    System.out.println("[" + uuid + "] request scope bean close:" + this);
  }
}
