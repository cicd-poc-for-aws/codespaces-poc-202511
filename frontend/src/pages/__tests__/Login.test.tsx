import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { MemoryRouter } from 'react-router-dom'
import Login from '../Login'

describe('Login page', () => {
  test('renders heading and button', async () => {
    // MemoryRouter でラップしてルーティング機能を提供
    render(
      <MemoryRouter>
        <Login />
      </MemoryRouter>
    )

    // Loginページのタイトル（h1）が表示されているか確認
    expect(screen.getByRole('heading', { name: /login/i })).toBeInTheDocument()

    // ログインボタンが表示されているか確認
    const button = screen.getByRole('button', { name: /ログイン（テスト）/i })
    expect(button).toBeInTheDocument()

    // ボタンをクリックしてイベントハンドラが正常に動作することを確認
    await userEvent.click(button)
    // react-routerのナビゲーションが動作し、エラーなく実行されることを検証
  })
})
