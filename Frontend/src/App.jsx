import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import Navbar from "./components/navbar.jsx";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Areas from "./pages/Areas";
import ParkingLots from "./pages/ParkingLots";
import Vehicles from "./pages/Vehicles";
import ProtectedRoute from "./components/ProtectedRoute.jsx";

function App() {
  return (
    <AuthProvider>
      <Router>
        <Navbar />
        <div className="container-fluid px-0 mt-4">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/areas" element={<ProtectedRoute><Areas /></ProtectedRoute>} />
            <Route path="/areas/:id" element={<ProtectedRoute><ParkingLots /></ProtectedRoute>} />
            <Route path="/parkinglots" element={<ProtectedRoute><ParkingLots /></ProtectedRoute>} />
            <Route path="/vehicles" element={<ProtectedRoute><Vehicles /></ProtectedRoute>} />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
