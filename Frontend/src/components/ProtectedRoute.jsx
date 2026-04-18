import React from "react";
import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function ProtectedRoute({ children }) {
  const auth = useAuth();
  const location = useLocation();

  if (!auth?.isAuthenticated) {
    return (
      <Navigate
        to="/login"
        replace
        state={{ from: location, message: "You need to login first to see parking areas." }}
      />
    );
  }
  return children;
}

export default ProtectedRoute;
