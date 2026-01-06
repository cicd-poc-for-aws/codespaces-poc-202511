package com.example.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * UserController のテストクラス
 * Mockito を使用して UserRepository をモック化し、各エンドポイントの動作を検証
 */
class UserControllerTest {

  @Mock
  private UserRepository userRepository;

  private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    userController = new UserController(userRepository);
  }

  /**
   * getUsers() メソッドのテスト
   * リポジトリから取得したユーザーリストが正しく返されることを確認
   */
  @Test
  void testGetUsers() {
    User user1 = new User("Alice");
    User user2 = new User("Bob");
    when(userRepository.findAll()).thenReturn(List.of(user1, user2));

    List<User> result = userController.getUsers();

    assertEquals(2, result.size());
    assertEquals("Alice", result.get(0).getName());
    assertEquals("Bob", result.get(1).getName());
  }

  /**
   * createUser() メソッドのテスト
   * 新規ユーザーが正しく保存され、生成されたIDを含めて返されることを確認
   */
  @Test
  void testCreateUser() {
    User newUser = new User("Charlie");
    User savedUser = new User("Charlie");
    savedUser.setId(1L);
    when(userRepository.save(newUser)).thenReturn(savedUser);

    User result = userController.createUser(newUser);

    assertEquals(1L, result.getId());
    assertEquals("Charlie", result.getName());
    verify(userRepository).save(newUser);
  }

  /**
   * updateUser() メソッドのテスト
   * 既存ユーザーの情報が正しく更新されることを確認
   */
  @Test
  void testUpdateUser() {
    Long userId = 1L;
    User existingUser = new User("Alice");
    existingUser.setId(userId);
    User updatedUser = new User("Updated Alice");
    updatedUser.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(existingUser)).thenReturn(updatedUser);

    User result = userController.updateUser(userId, new User("Updated Alice"));

    assertEquals("Updated Alice", result.getName());
    verify(userRepository).findById(userId);
    verify(userRepository).save(existingUser);
  }

  /**
   * updateUser() メソッドのエラーケーステスト
   * 存在しないユーザーを更新しようとした場合に RuntimeException が発生することを確認
   */
  @Test
  void testUpdateUserNotFound() {
    Long userId = 999L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> {
      userController.updateUser(userId, new User("Test"));
    });
  }

  /**
   * deleteUser() メソッドのテスト
   * ユーザーが正しく削除されることを確認（deleteById が呼ばれることを検証）
   */
  @Test
  void testDeleteUser() {
    Long userId = 1L;

    userController.deleteUser(userId);

    verify(userRepository).deleteById(userId);
  }
}
