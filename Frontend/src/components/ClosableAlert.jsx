import React from "react";
import "./ClosableAlert.css";

function ClosableAlert({ message, type = "alert-info", onClose, className = "" }) {
  return (
    <div className={`closable-alert alert ${type} ${className}`} role="alert">
      <span className="closable-alert-message">{message}</span>
      {onClose && (
        <button
          type="button"
          className="closable-alert-close"
          onClick={onClose}
          aria-label="Close notification"
        >
          ×
        </button>
      )}
    </div>
  );
}

export default ClosableAlert;
