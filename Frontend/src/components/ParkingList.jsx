import React, { useCallback, useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import ClosableAlert from "./ClosableAlert";
import {
  getSlots,
  getReservations,
  releaseSlot,
  createSlot,
  updateSlot,
  deleteSlot,
  createReservation,
  deleteReservation,
} from "../services/ParkingService";

function ParkingList({ areaId }) {
  const auth = useAuth();
  const [slots, setSlots] = useState([]);
  const [reservations, setReservations] = useState([]);
  const [message, setMessage] = useState("");
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [editingSlot, setEditingSlot] = useState(null);
  const [slotForm, setSlotForm] = useState({ slotNumber: "", isAvailable: true });
  const [selectedSlot, setSelectedSlot] = useState(null);
  const [bookingMode, setBookingMode] = useState("park");
  const [bookingForm, setBookingForm] = useState({ startTime: "", endTime: "", plateNumber: "" });

  const normalizeCollection = (value) => {
    if (!value) return [];
    if (Array.isArray(value)) return value;
    if (value.data && Array.isArray(value.data)) return value.data;
    if (value.reservations && Array.isArray(value.reservations)) return value.reservations;
    if (value.slots && Array.isArray(value.slots)) return value.slots;
    return [];
  };

  const getSlotKey = (slot) => String(slot.id ?? slot.slotId ?? slot.slot_number ?? slot.slotNumber ?? slot.number ?? "");

  const getReservationSlotKey = (reservation) => {
    if (!reservation) return "";
    return String(
      reservation.slotId ??
        reservation.slot_id ??
        reservation.slot?.id ??
        reservation.slot?.slotId ??
        reservation.slot?.slot_number ??
        reservation.slot?.slotNumber ??
        reservation.parkingSlot?.id ??
        reservation.parkingSlot?.slotId ??
        reservation.parkingSlot?.slotNumber ??
        ""
    );
  };

  const loadSlots = useCallback(async () => {
    const [slotsData, reservationsData] = await Promise.all([getSlots(), getReservations()]);
    setSlots(normalizeCollection(slotsData));
    setReservations(normalizeCollection(reservationsData));
  }, []);

  useEffect(() => {
    loadSlots();
  }, [loadSlots]);

  const isReservationExpired = (reservation) => {
    if (!reservation || !reservation.endTime) return false;
    const end = new Date(reservation.endTime);
    return !isNaN(end.getTime()) && end.getTime() <= Date.now();
  };

  const activeReservations = React.useMemo(
    () => reservations.filter((reservation) => !isReservationExpired(reservation)),
    [reservations]
  );

  const reservationKeys = React.useMemo(() => {
    const keys = new Set();

    activeReservations.forEach((reservation) => {
      const reservationKey = getReservationSlotKey(reservation);
      if (reservationKey) keys.add(reservationKey);

      const reservationNumber = String(
        reservation.slot?.slotNumber ??
          reservation.slot?.slot_number ??
          reservation.parkingSlot?.slotNumber ??
          reservation.parkingSlot?.slot_number ??
          ""
      );
      if (reservationNumber) keys.add(reservationNumber);

      if (reservation.slot?.id != null) keys.add(String(reservation.slot.id));
      if (reservation.parkingSlot?.id != null) keys.add(String(reservation.parkingSlot.id));
      if (reservation.slotId != null) keys.add(String(reservation.slotId));
      if (reservation.slot_id != null) keys.add(String(reservation.slot_id));
    });

    return keys;
  }, [activeReservations]);

  const getReservationForSlot = (slot) => {
    const slotKey = getSlotKey(slot);
    const slotNumber = String(slot.slotNumber ?? slot.slot_number ?? "");

    return activeReservations.find((reservation) => {
      const reservationKey = getReservationSlotKey(reservation);
      const reservationNumber = String(
        reservation.slot?.slotNumber ??
          reservation.slot?.slot_number ??
          reservation.parkingSlot?.slotNumber ??
          reservation.parkingSlot?.slot_number ??
          ""
      );

      return (
        reservationKey === slotKey ||
        reservationNumber === slotNumber ||
        String(reservation.slot?.id) === slotKey ||
        String(reservation.parkingSlot?.id) === slotKey ||
        reservationKeys.has(slotKey) ||
        (slotNumber && reservationKeys.has(slotNumber))
      );
    });
  };

  const getBookedBy = (slot) => {
    const reservation = getReservationForSlot(slot);
    if (reservation) {
      return (
        reservation.username ||
        reservation.user?.username ||
        reservation.user?.name ||
        reservation.userName ||
        reservation.user?.id ||
        reservation.user?.name ||
        ""
      );
    }

    return slot.bookedBy || slot.bookedByUser || "";
  };

  const userBookedSlot = activeReservations.some((reservation) => {
    if (!auth.user) return false;
    const userId = String(auth.user.id);
    return (
      String(reservation.userId) === userId ||
      String(reservation.user_id) === userId ||
      String(reservation.user?.id) === userId ||
      String(reservation.user?.userId) === userId ||
      reservation.username === auth.user.username
    );
  });

  const userReservations = React.useMemo(() => {
    if (!auth.user) return [];
    const userId = String(auth.user.id);
    return activeReservations.filter((reservation) =>
      String(reservation.userId) === userId ||
      String(reservation.user_id) === userId ||
      String(reservation.user?.id) === userId ||
      String(reservation.user?.userId) === userId ||
      reservation.username === auth.user.username
    );
  }, [activeReservations, auth.user]);

  const getReservationSlotInfo = (reservation) => {
    return (
      reservation.slot?.slotNumber ??
      reservation.slot?.slot_number ??
      reservation.parkingSlot?.slotNumber ??
      reservation.parkingSlot?.slot_number ??
      reservation.slotId ??
      reservation.slot_id ??
      "Unknown"
    );
  };

  const getReservationAreaName = (reservation) => {
    return (
      reservation.slot?.area?.name ??
      reservation.parkingSlot?.area?.name ??
      reservation.area?.name ??
      ""
    );
  };

  const getSlotAvailability = (slot) => {
    const reservation = getReservationForSlot(slot);
    const reserved = !!reservation;
    const statusBooked = reservation
      ? false
      : String(slot.status || slot.Status || "").toUpperCase() === "BOOKED";
    const slotAvailableField =
      !statusBooked &&
      slot.isAvailable !== false &&
      slot.available !== false &&
      slot.is_available !== false;
    return !reserved && slotAvailableField && !slot.bookedBy && !slot.bookedByUser;
  };

  const handleBookClick = (slot) => {
    setSelectedSlot(slot);
    setBookingMode("book");
    setBookingForm({ startTime: "", endTime: "", plateNumber: "" });
    setMessage("");
  };

  const handleParkClick = (slot) => {
    setSelectedSlot(slot);
    setBookingMode("park");
    setBookingForm({ startTime: "", endTime: "", plateNumber: "" });
    setMessage("");
  };

  const handleBookingChange = (e) => {
    const { name, value } = e.target;
    setBookingForm((prev) => ({ ...prev, [name]: value }));
  };

  const submitBooking = async (e) => {
    e.preventDefault();
    if (!selectedSlot) return;

    if (bookingMode === "book") {
      if (!bookingForm.startTime || !bookingForm.endTime) {
        setMessage("Please enter both start and end time.");
        return;
      }

      const start = new Date(bookingForm.startTime);
      const end = new Date(bookingForm.endTime);
      if (isNaN(start.getTime()) || isNaN(end.getTime())) {
        setMessage("Please enter valid start and end times.");
        return;
      }

      if (end <= start) {
        setMessage("End time must be later than start time.");
        return;
      }
    }

    if (!bookingForm.plateNumber?.trim()) {
      setMessage("Please enter vehicle number.");
      return;
    }

    try {
      await createReservation({
        userId: Number(auth.user?.id),
        slotId: selectedSlot.id,
        plateNumber: bookingForm.plateNumber.trim(),
        startTime: bookingMode === "book" ? bookingForm.startTime : null,
        endTime: bookingMode === "book" ? bookingForm.endTime : null,
      });

      setMessage(bookingMode === "park" ? "Slot parked successfully." : "Slot booked successfully.");
      setSelectedSlot(null);
      setBookingForm({ startTime: "", endTime: "", plateNumber: "" });
      await loadSlots();
    } catch (error) {
      setMessage(error.message || "Could not book slot.");
    }
  };

  const handleCancelBooking = async (reservation) => {
    if (!reservation?.id) {
      setMessage("Unable to cancel this booking.");
      return;
    }

    try {
      const result = await deleteReservation(reservation.id);
      if (reservation?.slot?.id || reservation.slotId || reservation.slot_id) {
        const slotId = reservation.slot?.id ?? reservation.slotId ?? reservation.slot_id;
        await releaseSlot(slotId);
      }
      setMessage(result || "Booking cancelled successfully.");
    } catch (error) {
      setMessage(error.message || "Unable to cancel this booking.");
    }

    await loadSlots();
  };

  const handleRelease = async (id) => {
    const result = await releaseSlot(id);
    setMessage(result);
    await loadSlots();
  };

  const openSlotForm = (slot = null) => {
    setEditingSlot(slot);
    setSlotForm({
      slotNumber: slot?.slotNumber ?? "",
      isAvailable: slot?.isAvailable ?? slot?.available ?? true,
    });
    setIsFormOpen(true);
  };

  const closeSlotForm = () => {
    setEditingSlot(null);
    setSlotForm({ slotNumber: "", isAvailable: true });
    setIsFormOpen(false);
  };

  const handleFormChange = (e) => {
    const { name, value, type, checked } = e.target;
    setSlotForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSlotSave = async (e) => {
    e.preventDefault();
    setMessage("");

    const slotNumber = slotForm.slotNumber?.trim();
    if (!slotNumber) {
      setMessage("Please enter a slot number.");
      return;
    }

    const duplicateSlot = slots.find(
      (slot) => String(slot.slotNumber).trim().toLowerCase() === slotNumber.toLowerCase()
    );

    if (editingSlot) {
      if (duplicateSlot && duplicateSlot.id !== editingSlot.id) {
        setMessage("Parking slot already exists, try a new one.");
        return;
      }
    } else if (duplicateSlot) {
      setMessage("Parking slot already exists, try a new one.");
      return;
    }

    try {
      if (editingSlot) {
        const payload = {
          ...slotForm,
          status: slotForm.isAvailable ? "AVAILABLE" : "BOOKED",
          ...(editingSlot.area?.id ? { area: { id: editingSlot.area.id } } : areaId ? { area: { id: areaId } } : {}),
        };
        const updatedSlot = await updateSlot(editingSlot.id, payload);
        setSlots((prevSlots) => prevSlots.map((slot) => slot.id === updatedSlot.id ? updatedSlot : slot));
        setMessage("Parking slot updated.");
      } else {
        const newSlot = areaId
          ? { ...slotForm, status: slotForm.isAvailable ? "AVAILABLE" : "BOOKED", area: { id: areaId } }
          : { ...slotForm, status: slotForm.isAvailable ? "AVAILABLE" : "BOOKED" };
        const createdSlot = await createSlot(newSlot);
        setSlots((prevSlots) => [...prevSlots, createdSlot]);
        setMessage("Parking slot added.");
      }
      closeSlotForm();
    } catch (error) {
      const errorMessage = error.message || "Failed to save parking slot.";
      if (/(exists|duplicate|already)/i.test(errorMessage)) {
        setMessage("Parking slot already exists, try a new one.");
      } else {
        setMessage(errorMessage);
      }
    } finally {
      await loadSlots();
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this slot permanently?")) return;
    const result = await deleteSlot(id);
    setMessage(result);
    await loadSlots();
  };

  const filteredSlots = areaId
    ? slots.filter((slot) => {
        const slotAreaId =
          slot.area?.id ?? slot.areaId ?? slot.area_id ?? slot.area?.area_id;
        return String(slotAreaId) === String(areaId);
      })
    : slots;

  return (
    <div>
      {auth.isAdmin && (
        <div className="mb-4">
          <button className="btn btn-success" onClick={() => openSlotForm()}>
            Add parking slot
          </button>
        </div>
      )}

      {message && <ClosableAlert message={message} onClose={() => setMessage("")} />}

      {auth.user && !auth.isAdmin && (
        <div className="card p-3 mb-4 shadow-sm">
          <h5 className="mb-3">My Booking List</h5>
          {userReservations.length === 0 ? (
            <div className="alert alert-secondary mb-0">You have no active bookings.</div>
          ) : (
            <div className="list-group">
              {userReservations.map((reservation) => (
                <div className="list-group-item" key={reservation.id}>
                  <div className="fw-bold">Slot: {getReservationSlotInfo(reservation)}</div>
                  <div>Area: {getReservationAreaName(reservation) || "Unknown"}</div>
                  {(reservation.startTime || reservation.endTime) && (
                    <div className="text-muted small">
                      {reservation.startTime ? `From ${reservation.startTime}` : ""}
                      {reservation.startTime && reservation.endTime ? " to " : ""}
                      {reservation.endTime ? reservation.endTime : ""}
                    </div>
                  )}
                  {reservation.slot?.id && (
                    <button
                      type="button"
                      className="btn btn-sm btn-outline-danger mt-2"
                      onClick={() => handleCancelBooking(reservation)}
                    >
                      Cancel booking
                    </button>
                  )}
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {isFormOpen && (
        <div className="card p-3 mb-4 shadow-sm">
          <h5 className="mb-3">{editingSlot ? "Edit Parking Slot" : "Add Parking Slot"}</h5>
          <form onSubmit={handleSlotSave}>
            <div className="mb-3">
              <label className="form-label">Slot Number</label>
              <input
                type="text"
                name="slotNumber"
                value={slotForm.slotNumber}
                onChange={handleFormChange}
                className="form-control"
                placeholder="Enter slot number"
                required
              />
            </div>
            <div className="form-check mb-3">
              <input
                type="checkbox"
                id="isAvailable"
                name="isAvailable"
                checked={slotForm.isAvailable}
                onChange={handleFormChange}
                className="form-check-input"
              />
              <label htmlFor="isAvailable" className="form-check-label">
                Available
              </label>
            </div>
            <div className="d-flex gap-2">
              <button type="submit" className="btn btn-success">
                {editingSlot ? "Update Slot" : "Save Slot"}
              </button>
              <button type="button" className="btn btn-outline-secondary" onClick={closeSlotForm}>
                Cancel
              </button>
            </div>
          </form>
        </div>
      )}

      {!auth.isAdmin && selectedSlot && (
        <form className="card p-3 mb-4 shadow-sm" onSubmit={submitBooking}>
          <h5 className="mb-3">
            {bookingMode === "park" ? "Park Slot" : "Book Slot"} {selectedSlot.slotNumber}
          </h5>
          {bookingMode === "book" && (
            <>
              <div className="mb-3">
                <label className="form-label">Start Time</label>
                <input
                  type="datetime-local"
                  className="form-control"
                  name="startTime"
                  value={bookingForm.startTime}
                  onChange={handleBookingChange}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">End Time</label>
                <input
                  type="datetime-local"
                  className="form-control"
                  name="endTime"
                  value={bookingForm.endTime}
                  onChange={handleBookingChange}
                  required
                />
              </div>
            </>
          )}
          <div className="mb-3">
            <label className="form-label">Vehicle Number</label>
            <input
              type="text"
              className="form-control"
              name="plateNumber"
              value={bookingForm.plateNumber}
              onChange={handleBookingChange}
              placeholder="Enter vehicle number"
              required
            />
          </div>
          <div className="d-flex gap-2">
            <button type="submit" className="btn btn-success">
              {bookingMode === "park" ? "Confirm Park" : "Confirm Booking"}
            </button>
            <button type="button" className="btn btn-outline-secondary" onClick={() => setSelectedSlot(null)}>
              Cancel
            </button>
          </div>
          <small className="text-muted">Only one active booking is allowed per user.</small>
        </form>
      )}

      <div className="row">
        {filteredSlots.map((slot) => {
          const reservation = getReservationForSlot(slot);
          const bookedBy = getBookedBy(slot);
          const isBookedByMe =
            auth.user &&
            reservation &&
            (String(reservation.userId) === String(auth.user.id) ||
              String(reservation.user_id) === String(auth.user.id) ||
              String(reservation.user?.id) === String(auth.user.id) ||
              reservation.username === auth.user.username);
          const isAvailable = getSlotAvailability(slot);
          const statusBooked = String(slot.status || slot.Status || "").toUpperCase() === "BOOKED";
          const canBook = !auth.isAdmin && isAvailable && !userBookedSlot;
          const canRelease = isBookedByMe || (auth.isAdmin && (!isAvailable || statusBooked));
          const bookingInfo = reservation
            ? `${reservation.startTime ? `From ${reservation.startTime}` : ""}${
                reservation.startTime && reservation.endTime ? " to " : ""
              }${reservation.endTime ? reservation.endTime : ""}`
            : null;

          return (
            <div className="col-md-4 mb-3" key={slot.id}>
              <div className={`card shadow-sm h-100 ${isAvailable ? "slot-card-available" : "slot-card-booked"}`}>
                <div className="card-body d-flex flex-column">
                  <h5 className="card-title">Slot {slot.slotNumber}</h5>
                  <p className={isAvailable ? "text-success" : "text-danger"}>
                    {isAvailable ? "Available" : isBookedByMe ? "Booked by you" : "Booked"}
                  </p>
                  {bookingInfo && <p className="card-text mb-2">{bookingInfo}</p>}
                  {bookedBy && !isAvailable && auth.isAdmin && (
                    <p className="card-text mb-2">Booked by: {bookedBy}</p>
                  )}
                  {bookedBy && !isAvailable && isBookedByMe && !auth.isAdmin && (
                    <p className="card-text mb-2">Booked by: You</p>
                  )}
                  <div className="mt-auto d-flex flex-wrap gap-2">
                    {!auth.isAdmin && (
                      <>
                        <button className="btn btn-primary" onClick={() => handleParkClick(slot)} disabled={!canBook}>
                          Park
                        </button>
                        <button className="btn btn-danger" onClick={() => handleBookClick(slot)} disabled={!canBook}>
                          Book
                        </button>
                      </>
                    )}
                    {canRelease && (
                      <button className="btn btn-outline-secondary" onClick={() => handleRelease(slot.id)}>
                        Release
                      </button>
                    )}
                    {auth.isAdmin && (
                      <>
                        {isAvailable && (
                          <button className="btn btn-outline-primary" onClick={() => openSlotForm(slot)}>
                            Edit
                          </button>
                        )}
                        <button className="btn btn-danger" onClick={() => handleDelete(slot.id)}>
                          Delete
                        </button>
                      </>
                    )}
                  </div>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default ParkingList;
