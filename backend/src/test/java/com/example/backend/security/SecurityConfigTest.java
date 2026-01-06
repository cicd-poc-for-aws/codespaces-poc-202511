package com.example.backend.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig のテストクラス
 * Spring Security の設定が正しく適用されているかを検証
 */
@SpringBootTest
class SecurityConfigTest {

  @Autowired
  private SecurityFilterChain securityFilterChain;

  /**
   * SecurityFilterChain Bean が正しく作成されることを確認するテスト
   * スプリングコンテキストが SecurityFilterChain をインジェクションできることを検証
   */
  @Test
  void testSecurityFilterChainBeanCreation() {
    assertNotNull(securityFilterChain, "SecurityFilterChain should be created and injected");
  }
}
