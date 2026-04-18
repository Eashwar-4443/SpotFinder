import React, { useEffect, useState } from "react";
import { createVehicle, updateVehicle } from "../services/ParkingService";
import ClosableAlert from "./ClosableAlert";

function ParkingForm({
  initialVehicle = { ownerName: "", plateNumber: "" },
  onSave,
  onCancel,
  submitLabel = "Submit",
  existingVehicles = [],
}) {
  const [vehicle, setVehicle] = useState(initialVehicle);
  const [message, setMessage] = useState("");
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    setVehicle(initialVehicle);
    setMessage("");
  }, [initialVehicle]);

  const handleChange = (e) => {
    setMessage("");
    setVehicle({ ...vehicle, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");

    const ownerName = vehicle.ownerName?.trim();
    const plateNumber = vehicle.plateNumber?.trim();
    if (!ownerName || !plateNumber) {
      setMessage("Please enter an owner name and plate number.");
      return;
    }

    const duplicateVehicle = existingVehicles.find(
      (item) =>
        item.id !== vehicle.id &&
        item.plateNumber?.trim().toLowerCase() === plateNumber.toLowerCase()
    );

    if (duplicateVehicle) {
      setMessage("Plate number already exists, try a new one.");
      return;
    }

    setSubmitting(true);
    try {
      if (vehicle.id) {
        await updateVehicle(vehicle.id, { ...vehicle, ownerName, plateNumber });
      } else {
        await createVehicle({ ownerName, plateNumber });
      }
      setVehicle({ ownerName: "", plateNumber: "" });
      setMessage("");
      if (onSave) onSave();
    } catch (error) {
      const errorMessage = error.message || "Failed to save vehicle. Please try again.";
      if (/(plate.*exists|duplicate.*plate|unique.*plate|already.*plate|plate.*already)/i.test(errorMessage)) {
        setMessage("Plate number already exists, try a new one.");
      } else {
        setMessage(errorMessage);
      }
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <form className="card p-3 shadow-sm" onSubmit={handleSubmit}>
      <h5 className="mb-3">{submitLabel}</h5>
      {message && <ClosableAlert message={message} onClose={() => setMessage("")} />}
      <div className="mb-3">
        <label className="form-label">Owner Name</label>
        <input
          type="text"
          className="form-control"
          name="ownerName"
          value={vehicle.ownerName}
          onChange={handleChange}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Plate Number</label>
        <input
          type="text"
          className="form-control"
          name="plateNumber"
          value={vehicle.plateNumber}
          onChange={handleChange}
          required
        />
      </div>
      <div className="d-flex gap-2">
        <button type="submit" className="btn btn-success" disabled={submitting}>
          {submitting ? "Saving..." : submitLabel}
        </button>
        {onCancel && (
          <button type="button" className="btn btn-outline-secondary" onClick={onCancel}>
            Cancel
          </button>
        )}
      </div>
    </form>
  );
}

export default ParkingForm;
