import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { login } from "../services/AuthService";
import { useAuth } from "../context/AuthContext";
import ClosableAlert from "../components/ClosableAlert";
import "./AuthPage.css";

function Login() {
  const [credentials, setCredentials] = useState({ identifier: "", password: "" });
  const location = useLocation();
  const [message, setMessage] = useState(() => location.state?.message || "");
  const navigate = useNavigate();
  const auth = useAuth();

  useEffect(() => {
    if (auth?.isAuthenticated) {
      navigate("/");
    }
  }, [auth, navigate]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setCredentials((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!credentials.identifier.trim()) {
      setMessage("Please enter your username or Gmail.");
      return;
    }
    if (!credentials.password) {
      setMessage("Please enter your password.");
      return;
    }
    try {
      const data = await login({ username: credentials.identifier.trim(), password: credentials.password });
      const token = data?.token;
      const role = data?.role;
      const id = Number(data?.id || data?.userId || data?.user_id);
      const username = data?.username || credentials.identifier.trim();

      if (token) {
        auth.login({ token, username, role, id });
        setMessage("Successfully logged in. Redirecting to home...");
        setTimeout(() => navigate("/", { state: { message: "Successfully logged in." } }), 800);
      } else {
        setMessage(data?.message || "Login failed. Check your credentials.");
      }
    } catch (error) {
      setMessage(error.message || "Failed to login. Please try again.");
    }
  };

  return (
    <div className="auth-page">
      {message && (
        <ClosableAlert
          message={message}
          onClose={() => setMessage("")}
          className="auth-top-message mb-4"
        />
      )}
      <div className="auth-card card">
        <div className="card-body">
          <h2 className="card-title mb-3">Login</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label className="form-label">Username/Gmail</label>
              <input
                type="text"
                name="identifier"
                value={credentials.identifier}
                onChange={handleChange}
                className="form-control"
                placeholder="Enter Username/Gmail"
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Password</label>
              <input
                type="password"
                name="password"
                value={credentials.password}
                onChange={handleChange}
                className="form-control"
                placeholder="Enter your password"
                required
              />
            </div>
            <button type="submit" className="btn btn-primary w-100">
              Sign in
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
