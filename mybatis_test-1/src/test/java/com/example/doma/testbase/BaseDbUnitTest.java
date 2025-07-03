package com.example.doma.testbase;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * DbUnitテスト用のベースクラス
 * 
 * 全てのMapperテストクラスはこのクラスを継承することで、
 * 以下の設定が自動的に適用されます：
 * 
 * - Spring Boot テスト環境
 * - H2 インメモリデータベース
 * - DbUnit によるExcelデータ投入
 * - トランザクション管理（各テスト後に自動ロールバック）
 * 
 * 使用例：
 * public class UserMapperTest extends BaseDbUnitTest {
 *     @Test
 *     @DatabaseSetup("/testdata/user-test-data.xlsx")
 *     public void テストメソッド() { ... }
 * }
 */
@SpringBootTest
@ActiveProfiles("test")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,    // DIコンテナ
    DirtiesContextTestExecutionListener.class,         // コンテキストクリーンアップ
    TransactionalTestExecutionListener.class,          // トランザクション管理
    DbUnitTestExecutionListener.class                  // DbUnit (Excel読み込み)
})
@Transactional  // 各テスト後に自動ロールバック
public abstract class BaseDbUnitTest {
    
    // このクラスは設定のみを提供
    // 具体的なテストロジックは継承先クラスで実装する
    
    /**
     * テスト開始時のログ出力（オプション）
     */
    protected void logTestStart(String testName) {
        System.out.println("=== " + testName + " 開始 ===");
    }
    
    /**
     * テスト完了時のログ出力（オプション）
     */
    protected void logTestEnd(String testName) {
        System.out.println("✅ " + testName + " 完了");
    }
}