import { useEffect, useState } from "react";

interface User {
  id: number;
  name: string;
}

export default function UserManagement() {
  const [users, setUsers] = useState<User[]>([]);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editingName, setEditingName] = useState("");
  const [newName, setNewName] = useState("");

  const fetchUsers = async () => {
    try {
      const res = await fetch("/api/users");
      const data = await res.json();
      setUsers(data);
    } catch (err) {
      console.error("ユーザー取得エラー:", err);
    }
  };

  const addUser = async () => {
    try {
      const res = await fetch("/api/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: newName }),
      });
      if (!res.ok) throw new Error("追加失敗");
      fetchUsers();
      setNewName("");
    } catch (err) {
      console.error(err);
    }
  };

  const startEdit = (user: User) => {
    setEditingId(user.id);
    setEditingName(user.name);
  };

  const saveEdit = async (id: number) => {
    try {
      const res = await fetch(`/api/users/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: editingName }),
      });
      if (!res.ok) throw new Error("更新失敗");
      setEditingId(null);
      setEditingName("");
      fetchUsers();
    } catch (err) {
      console.error(err);
    }
  };

  const deleteUser = async (id: number) => {
    try {
      const res = await fetch(`/api/users/${id}`, { method: "DELETE" });
      if (!res.ok) throw new Error("削除失敗");
      fetchUsers();
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div>
      <h1>ユーザー管理</h1>

      <div>
        <input
          value={newName}
          onChange={(e) => setNewName(e.target.value)}
          placeholder="新しいユーザー名"
        />
        <button onClick={addUser}>追加</button>
      </div>

      <ul>
        {users.map((user) => (
          <li key={user.id}>
            {editingId === user.id ? (
              <>
                <input
                  value={editingName}
                  onChange={(e) => setEditingName(e.target.value)}
                />
                <button onClick={() => saveEdit(user.id)}>保存</button>
                <button onClick={() => setEditingId(null)}>キャンセル</button>
              </>
            ) : (
              <>
                <span>{user.name}</span>
                <button onClick={() => startEdit(user)}>編集</button>
                <button onClick={() => deleteUser(user.id)}>削除</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
