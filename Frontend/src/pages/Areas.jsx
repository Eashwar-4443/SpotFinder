import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getParkingAreas, createParkingArea } from "../services/ParkingService";
import { useAuth } from "../context/AuthContext";
import ClosableAlert from "../components/ClosableAlert";

function Areas() {
  const auth = useAuth();
  const [areas, setAreas] = useState([]);
  const [message, setMessage] = useState("");
  const [name, setName] = useState("");
  const [location, setLocation] = useState("");
  const [submitting, setSubmitting] = useState(false);
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    getParkingAreas()
      .then(setAreas)
      .catch((error) => setMessage(error.message || "Failed to load areas."));
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage("");
    if (!name.trim() || !location.trim()) {
      setMessage("Please enter both a name and a location.");
      return;
    }

    if (areas.some((area) => area.name?.trim().toLowerCase() === name.trim().toLowerCase())) {
      setMessage("Parking area already exists, try a new one.");
      return;
    }

    setSubmitting(true);
    try {
      await createParkingArea({ name: name.trim(), location: location.trim() });
      const updatedAreas = await getParkingAreas();
      setAreas(updatedAreas);
      setName("");
      setLocation("");
      setMessage("Parking area created successfully.");
      setShowForm(false);
    } catch (error) {
      const message = error.message || "Failed to create parking area.";
      if (/(exists|duplicate|already)/i.test(message)) {
        setMessage("Parking area already exists, try a new one.");
      } else {
        setMessage(message);
      }
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div>
      <h2 className="mb-3">Parking Areas</h2>
      {message && (
        <ClosableAlert message={message} onClose={() => setMessage("")} />
      )}

      {auth?.isAdmin && (
        <div className="mb-4">
          <button
            type="button"
            className="btn btn-primary mb-3"
            onClick={() => setShowForm((prev) => !prev)}
          >
            {showForm ? "Hide Add Parking Area" : "Add Parking Area"}
          </button>
          {showForm && (
            <div className="card shadow-sm">
              <div className="card-body">
                <h5 className="card-title">Add New Parking Area</h5>
                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Area Name</label>
                    <input
                      type="text"
                      className="form-control"
                      value={name}
                      onChange={(e) => setName(e.target.value)}
                      placeholder="Enter parking area name"
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Location</label>
                    <input
                      type="text"
                      className="form-control"
                      value={location}
                      onChange={(e) => setLocation(e.target.value)}
                      placeholder="Enter parking area location"
                      required
                    />
                  </div>
                  <div className="d-flex gap-2">
                    <button type="submit" className="btn btn-success" disabled={submitting}>
                      {submitting ? "Creating..." : "Create Parking Area"}
                    </button>
                    <button
                      type="button"
                      className="btn btn-outline-secondary"
                      onClick={() => setShowForm(false)}
                    >
                      Cancel
                    </button>
                  </div>
                </form>
              </div>
            </div>
          )}
        </div>
      )}

      <div className="row gy-3">
        {areas.length === 0 ? (
          <div className="col-12">
            <div className="alert alert-secondary">No parking areas found.</div>
          </div>
        ) : (
          areas.map((area) => (
            <div className="col-md-4" key={area.id}>
              <div className="card shadow-sm h-100">
                <div className="card-body d-flex flex-column">
                  <h5 className="card-title">{area.name}</h5>
                  <p className="card-text">{area.location}</p>
                  <Link to={`/areas/${area.id}`} className="btn btn-primary mt-auto">
                    View parking lots
                  </Link>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default Areas;
