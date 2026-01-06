package com.example.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * User エンティティのテストクラス
 * ユーザーエンティティのコンストラクタとゲッター/セッターの動作を検証
 */
class UserTest {

  /**
   * 名前付きコンストラクタのテスト
   * 指定した名前でユーザーが作成され、ID は初期化されていないことを確認
   */
  @Test
  void testUserConstructorWithName() {
    User user = new User("Alice");
    assertEquals("Alice", user.getName());
    assertNull(user.getId());
  }

  /**
   * デフォルトコンストラクタのテスト
   * パラメータなしでユーザーを作成した場合、name と id が null であることを確認
   */
  @Test
  void testUserDefaultConstructor() {
    User user = new User();
    assertNull(user.getName());
    assertNull(user.getId());
  }

  /**
   * name のゲッター/セッターのテスト
   * setName() で設定した名前が getName() で正しく取得できることを確認
   */
  @Test
  void testSetAndGetName() {
    User user = new User();
    user.setName("Bob");
    assertEquals("Bob", user.getName());
  }

  /**
   * id のゲッター/セッターのテスト
   * setId() で設定したID が getId() で正しく取得できることを確認
   */
  @Test
  void testSetAndGetId() {
    User user = new User("Charlie");
    user.setId(1L);
    assertEquals(1L, user.getId());
  }
}
