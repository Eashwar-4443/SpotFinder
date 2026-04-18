import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { getVehicles, deleteVehicle } from "../services/ParkingService";
import ParkingForm from "../components/ParkingForm";
import ClosableAlert from "../components/ClosableAlert";

function Vehicles() {
  const auth = useAuth();
  const [vehicles, setVehicles] = useState([]);
  const [editingVehicle, setEditingVehicle] = useState(null);
  const [message, setMessage] = useState("");

  const loadVehicles = async () => {
    setVehicles(await getVehicles());
  };

  useEffect(() => {
    let active = true;
    const fetchVehicles = async () => {
      const result = await getVehicles();
      if (active) {
        setVehicles(result);
      }
    };

    fetchVehicles();

    return () => {
      active = false;
    };
  }, []);

  const handleCreate = () => {
    setEditingVehicle({ ownerName: "", plateNumber: "" });
  };

  const handleEdit = (vehicle) => {
    setEditingVehicle(vehicle);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this vehicle permanently?")) return;
    const result = await deleteVehicle(id);
    setMessage(result || "Vehicle removed.");
    await loadVehicles();
  };

  const handleFormSaved = async () => {
    setEditingVehicle(null);
    await loadVehicles();
  };

  return (
    <div>
      <h2 className="mb-3">Vehicles</h2>

      {auth.isAdmin && (
        <div className="mb-4">
          <button className="btn btn-success" onClick={handleCreate}>
            Add vehicle
          </button>
        </div>
      )}

      {message && (
        <ClosableAlert message={message} onClose={() => setMessage("")} />
      )}

      {auth.isAdmin && editingVehicle && (
        <ParkingForm
          initialVehicle={editingVehicle}
          existingVehicles={vehicles}
          onSave={handleFormSaved}
          onCancel={() => setEditingVehicle(null)}
          submitLabel={editingVehicle?.id ? "Update vehicle" : "Create vehicle"}
        />
      )}

      {!auth.isAdmin && (
        <div className="alert alert-secondary">
          You can view registered vehicles here. Admin users can add, edit, or delete vehicles.
        </div>
      )}

      <div className="row gy-3 mt-4">
        {vehicles.length === 0 ? (
          <div className="col-12">
            <div className="alert alert-warning">No vehicles available.</div>
          </div>
        ) : (
          vehicles.map((vehicle) => (
            <div className="col-md-4" key={vehicle.id}>
              <div className="card shadow-sm h-100">
                <div className="card-body d-flex flex-column">
                  <h5 className="card-title">{vehicle.ownerName}</h5>
                  <p className="card-text mb-1"><strong>Plate:</strong> {vehicle.plateNumber}</p>
                  {vehicle.parkingSlot ? (
                    <>
                      <p className="card-text mb-1">
                        <strong>Parked at:</strong> Slot {vehicle.parkingSlot.slotNumber}
                      </p>
                      {vehicle.parkingSlot.area?.name && (
                        <p className="card-text mb-1"><strong>Area:</strong> {vehicle.parkingSlot.area.name}</p>
                      )}
                    </>
                  ) : (
                    <p className="card-text mb-1 text-muted">Not currently parked</p>
                  )}
                  {vehicle.make && <p className="card-text mb-1"><strong>Make:</strong> {vehicle.make}</p>}
                  {vehicle.model && <p className="card-text mb-3"><strong>Model:</strong> {vehicle.model}</p>}
                  {auth.isAdmin && (
                    <div className="mt-auto d-flex gap-2 flex-wrap">
                      <button className="btn btn-outline-primary" onClick={() => handleEdit(vehicle)}>
                        Edit
                      </button>
                      <button className="btn btn-danger" onClick={() => handleDelete(vehicle.id)}>
                        Delete
                      </button>
                    </div>
                  )}
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default Vehicles;
