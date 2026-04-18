/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useMemo, useState } from "react";

const AuthContext = createContext(null);

function getAuthFromStorage() {
  const token = localStorage.getItem("spotfinder_token");
  const username = localStorage.getItem("spotfinder_username");
  const userId = localStorage.getItem("spotfinder_user_id");
  const role = localStorage.getItem("spotfinder_role");
  const isAdmin = role?.toLowerCase() === "admin";
  const parsedUserId = userId ? Number(userId) : null;

  if (!token || !username) {
    return { token: null, user: null, roles: [], isAuthenticated: false, isAdmin: false };
  }

  return {
    token,
    user: { id: parsedUserId, username, roles: role ? [role] : [], isAdmin },
    roles: role ? [role] : [],
    isAuthenticated: true,
    isAdmin,
  };
}

export function AuthProvider({ children }) {
  const [authState, setAuthState] = useState(() => getAuthFromStorage());

  const login = ({ token, username, role, id }) => {
    localStorage.setItem("spotfinder_token", token);
    localStorage.setItem("spotfinder_username", username);
    localStorage.setItem("spotfinder_role", role || "");
    if (id !== undefined && id !== null) {
      localStorage.setItem("spotfinder_user_id", id);
    }
    setAuthState(getAuthFromStorage());
  };

  const logout = () => {
    localStorage.removeItem("spotfinder_token");
    localStorage.removeItem("spotfinder_username");
    localStorage.removeItem("spotfinder_role");
    localStorage.removeItem("spotfinder_user_id");
    setAuthState({ token: null, user: null, roles: [], isAuthenticated: false, isAdmin: false });
  };

  const value = useMemo(
    () => ({ ...authState, login, logout }),
    [authState]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  return useContext(AuthContext);
}
