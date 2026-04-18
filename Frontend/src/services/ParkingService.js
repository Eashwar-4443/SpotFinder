const BASE_URL = import.meta.env.VITE_PARKING_URL || "http://localhost:8080/parking";

function getAuthHeaders() {
  const token = localStorage.getItem("spotfinder_token");
  return token ? { Authorization: `Bearer ${token}` } : {};
}

async function handleResponse(response) {
  const text = await response.text();
  let body = null;
  if (text) {
    try {
      body = JSON.parse(text);
    } catch {
      body = text;
    }
  }

  if (!response.ok) {
    const message = body?.message || body?.error || body || `Server returned ${response.status}`;
    throw new Error(message);
  }

  return body ?? { success: true };
}

export async function getSlots() {
  const res = await fetch(`${BASE_URL}/slots`, {
    headers: getAuthHeaders(),
    cache: "no-store",
  });
  return handleResponse(res);
}

export async function createSlot(slot) {
  const res = await fetch(`${BASE_URL}/slots`, {
    method: "POST",
    headers: { "Content-Type": "application/json", ...getAuthHeaders() },
    body: JSON.stringify(slot),
  });
  return handleResponse(res);
}

export async function updateSlot(id, slot) {
  const res = await fetch(`${BASE_URL}/slots/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json", ...getAuthHeaders() },
    body: JSON.stringify(slot),
  });
  return handleResponse(res);
}

export async function deleteSlot(id) {
  const res = await fetch(`${BASE_URL}/slots/${id}`, {
    method: "DELETE",
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}

export async function bookSlot(id) {
  const res = await fetch(`${BASE_URL}/slots/book/${id}`, {
    method: "POST",
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}

export async function releaseSlot(id) {
  const res = await fetch(`${BASE_URL}/slots/release/${id}`, {
    method: "POST",
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}

export async function getVehicles() {
  const res = await fetch(`${BASE_URL}/vehicles`, {
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}

export async function createVehicle(vehicle) {
  const res = await fetch(`${BASE_URL}/vehicles`, {
    method: "POST",
    headers: { "Content-Type": "application/json", ...getAuthHeaders() },
    body: JSON.stringify(vehicle),
  });
  return handleResponse(res);
}

export async function updateVehicle(id, vehicle) {
  const res = await fetch(`${BASE_URL}/vehicles/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json", ...getAuthHeaders() },
    body: JSON.stringify(vehicle),
  });
  return handleResponse(res);
}

export async function deleteVehicle(id) {
  const res = await fetch(`${BASE_URL}/vehicles/${id}`, {
    method: "DELETE",
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}

export async function getParkingAreas() {
  const res = await fetch(`${BASE_URL}/areas`, {
    headers: getAuthHeaders(),
    cache: "no-store",
  });
  return handleResponse(res);
}

export async function getParkingArea(id) {
  const res = await fetch(`${BASE_URL}/areas/${id}`, {
    headers: getAuthHeaders(),
    cache: "no-store",
  });
  return handleResponse(res);
}

function getRoleHeader() {
  const role = localStorage.getItem("spotfinder_role");
  return role ? { "X-User-Role": role } : {};
}

export async function createParkingArea(area) {
  const res = await fetch(`${BASE_URL}/areas`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      ...getAuthHeaders(),
      ...getRoleHeader(),
    },
    body: JSON.stringify(area),
  });
  return handleResponse(res);
}

export async function updateParkingArea(id, area) {
  const res = await fetch(`${BASE_URL}/areas/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json", ...getAuthHeaders() },
    body: JSON.stringify(area),
  });
  return handleResponse(res);
}

export async function deleteParkingArea(id) {
  const res = await fetch(`${BASE_URL}/areas/${id}`, {
    method: "DELETE",
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}

export async function getReservations() {
  const res = await fetch(`${BASE_URL}/reservations`, {
    headers: getAuthHeaders(),
    cache: "no-store",
  });
  const body = await handleResponse(res);
  console.debug("Reservations response:", body);
  return body;
}

export async function getReservationById(id) {
  const res = await fetch(`${BASE_URL}/reservations/${id}`, {
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}

export async function createReservation(reservation) {
  const res = await fetch(`${BASE_URL}/reservations`, {
    method: "POST",
    headers: { "Content-Type": "application/json", ...getAuthHeaders() },
    body: JSON.stringify(reservation),
  });
  return handleResponse(res);
}

export async function updateReservation(id, reservation) {
  const res = await fetch(`${BASE_URL}/reservations/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json", ...getAuthHeaders() },
    body: JSON.stringify(reservation),
  });
  return handleResponse(res);
}

export async function deleteReservation(id) {
  const res = await fetch(`${BASE_URL}/reservations/${id}`, {
    method: "DELETE",
    headers: getAuthHeaders(),
  });
  return handleResponse(res);
}
