package com.example.backend.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HelloControllerTest {

  private HelloController helloController = new HelloController();

  /**
   * HelloController の hello() メソッドが正しいメッセージを返すことを確認するテスト
   * /api/hello エンドポイントから返されるメッセージが期待値と一致することを検証
   */
  @Test
  void testHelloEndpoint() {
    String result = helloController.hello();
    assertEquals("Hello from Spring Modulith (Gradle)", result);
  }
}
