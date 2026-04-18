import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { register } from "../services/AuthService";
import { useAuth } from "../context/AuthContext";
import ClosableAlert from "../components/ClosableAlert";
import "./AuthPage.css";

function Register() {
  const [form, setForm] = useState({ email: "", username: "", password: "", role: "USER" });
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
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const email = form.email.trim();
    if (!email) {
      setMessage("Please enter your Gmail address.");
      return;
    }
    if (!email.toLowerCase().endsWith("@gmail.com")) {
      setMessage("Please enter a valid @gmail.com email address.");
      return;
    }
    if (!form.username.trim()) {
      setMessage("Please enter a username.");
      return;
    }
    if (!form.password) {
      setMessage("Please enter a password.");
      return;
    }

    try {
      const data = await register({ email, username: form.username.trim(), password: form.password, role: "USER" });
      if (data?.username || data?.success) {
        setMessage("Registration successful. Redirecting to login...");
        setTimeout(() => navigate("/login"), 1000);
      } else {
        setMessage(data?.message || "Registration completed successfully.");
        setTimeout(() => navigate("/login"), 1000);
      }
    } catch (error) {
      const message = error.message || "Failed to register. Please try again.";

      if (/(user exists|username.*exists|duplicate.*username|unique.*username|username.*already)/i.test(message)) {
        setMessage("Username already exists, try a new one.");
      } else if (/(email.*exists|duplicate.*email|unique.*email|email.*already)/i.test(message)) {
        setMessage("Email already exists, try a new one.");
      } else if (/user exists|invalid data/i.test(message)) {
        setMessage("Username or email already exists, try a new one.");
      } else {
        setMessage(message);
      }
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
          <h2 className="card-title mb-3">Register</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label className="form-label">Gmail</label>
              <input
                type="email"
                name="email"
                value={form.email}
                onChange={handleChange}
                className="form-control"
                placeholder="you@gmail.com"
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Username</label>
              <input
                type="text"
                name="username"
                value={form.username}
                onChange={handleChange}
                className="form-control"
                placeholder="Enter your username"
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Password</label>
              <input
                type="password"
                name="password"
                value={form.password}
                onChange={handleChange}
                className="form-control"
                placeholder="Create a password"
                required
              />
            </div>
            <button type="submit" className="btn btn-success w-100">
              Create account
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Register;
