import { useNavigate } from "react-router-dom";

export default function Menu() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Menu</h1>
      <button onClick={() => navigate("/users")}>ユーザー管理へ</button>
    </div>
  );
}
