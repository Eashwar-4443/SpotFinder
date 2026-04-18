import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import ClosableAlert from "../components/ClosableAlert";
import './Home.css';

function Home() {
  const location = useLocation();
  const [message, setMessage] = useState(() => location.state?.message || "");

  return (
    <div className="home-container">
      <div className="home-overlay" />
      <div className="home-content text-center">
        {message && (
          <div className="mb-4">
            <ClosableAlert message={message} onClose={() => setMessage("")} type="alert-success" />
          </div>
        )}
        <h1 className="home-title">
          <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="#dc3545" className="me-2" style={{ verticalAlign: 'middle' }}>
            <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5S10.62 6.5 12 6.5s2.5 1.12 2.5 2.5S13.38 11.5 12 11.5z" />
          </svg>
          SpotFinder
        </h1>
        <p className="home-subtitle">
          A Perfect Parking Manager
        </p>
      </div>
    </div>
  );
}

export default Home;
