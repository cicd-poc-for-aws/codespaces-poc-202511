import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

type User = {
  id: number;
  name: string;
};

function App() {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [newUserName, setNewUserName] = useState("");

  // 一覧取得
  const fetchUsers = async () => {
    setLoading(true);
    try {
      const res = await fetch("/api/users");
      const data: User[] = await res.json();
      console.log("取得データ:", data);
      setUsers(data);
    } catch (err) {
      console.error("ユーザー取得エラー:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  // 新規作成
  const handleAddUser = async () => {
    if (!newUserName) return;
    setLoading(true);
    try {
      await fetch("/api/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: newUserName }),
      });
      setNewUserName("");
      fetchUsers();
    } catch (err) {
      console.error("ユーザー追加エラー:", err);
      setLoading(false);
    }
  };

  // 更新
  const handleUpdateUser = async (id: number, newName: string) => {
    console.log("更新:", id, newName);
    setLoading(true);
    try {
      await fetch(`/api/users/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: newName }),
      });
      fetchUsers();
    } catch (err) {
      console.error("ユーザー更新エラー:", err);
      setLoading(false);
    }
  };

  // 削除
  const handleDeleteUser = async (id: number) => {
    console.log("削除:", id);
    setLoading(true);
    try {
      await fetch(`/api/users/${id}`, { 
        method: "DELETE" 
      });
      fetchUsers();
    } catch (err) {
      console.error("ユーザー削除エラー:", err);
      setLoading(false);
    }
  };

  if (loading) return <div>Loading...</div>;

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>

      <div style={{ padding: "2rem" }}>
        <h1>ユーザー管理</h1>

        {/* 新規作成 */}
        <div style={{ marginBottom: "1rem" }}>
          <input
            type="text"
            placeholder="名前を入力"
            value={newUserName}
            onChange={(e) => setNewUserName(e.target.value)}
          />
          <button onClick={handleAddUser}>追加</button>
        </div>

        {/* ローディング表示 */}
        {loading ? (
          <p>読み込み中...</p>
        ) : (
          <ul>
            {users.map((user) => (
              <li key={user.id} style={{ marginBottom: "0.5rem" }}>
                <input
                  type="text"
                  value={user.name}
                  onChange={(e) =>
                    setUsers((prev) =>
                      prev.map((u) =>
                        u.id === user.id ? { ...u, name: e.target.value } : u
                      )
                    )
                  }
                />
                <button onClick={() => handleUpdateUser(user.id, user.name)}>
                  更新
                </button>
                <button onClick={() => handleDeleteUser(user.id)}>削除</button>
              </li>
            ))}
          </ul>
        )}
      </div>
    </>
  )
}

export default App
