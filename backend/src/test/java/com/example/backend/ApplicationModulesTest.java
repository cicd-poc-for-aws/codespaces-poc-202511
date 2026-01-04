package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

/**
 * Spring Modulith のモジュール構造とモジュール間の依存関係を検証するテストクラス
 * アプリケーションのモジュール設計が適切に分離されているかを確認
 */
class ApplicationModulesTest {

    /**
     * アプリケーションモジュールの構造が正しく定義されているか確認するテスト
     * Spring Modulith によってモジュールが検出され、構造が有効であることを検証
     * モジュール間の違反した依存関係がないことを確認
     */
    @Test
    void verifyModules() {
        ApplicationModules modules =
                ApplicationModules.of(BackendApplication.class);

        modules.verify();
    }

    /**
     * モジュール構造を文書化するテスト
     * アプリケーション内のモジュール構成をドキュメント形式で出力
     * これにより、モジュール間の依存関係が可視化される
     * target/spring-modulith-docs に詳細なドキュメントが生成される
     */
    @Test
    void createModuleDocumentation() {
        ApplicationModules modules =
                ApplicationModules.of(BackendApplication.class);

        new Documenter(modules).writeDocumentation();
    }
}
