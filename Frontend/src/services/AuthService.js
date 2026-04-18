const BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";
const USER_URL = `${BASE_URL}/parking/users`;
const LOGIN_URL = import.meta.env.VITE_LOGIN_URL || `${BASE_URL}/parking/login`;

async function parseBody(response) {
  const text = await response.text();
  if (!text) return null;
  try {
    return JSON.parse(text);
  } catch {
    return text;
  }
}

async function handleResponse(response) {
  const body = await parseBody(response);
  if (!response.ok) {
    const message =
      body?.message || body?.error || body?.errors || body || `Server returned ${response.status}`;
    throw new Error(message);
  }
  return body ?? { success: true };
}

export async function login(credentials) {
  const res = await fetch(`${LOGIN_URL}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(credentials),
  });
  return handleResponse(res);
}

export async function register(user) {
  const res = await fetch(`${USER_URL}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  return handleResponse(res);
}

export async function getUsers() {
  const res = await fetch(`${USER_URL}`);
  return handleResponse(res);
}

export async function getUserById(id) {
  const res = await fetch(`${USER_URL}/${id}`);
  return handleResponse(res);
}

export async function updateUser(id, user) {
  const res = await fetch(`${USER_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  return handleResponse(res);
}

export async function deleteUser(id) {
  const res = await fetch(`${USER_URL}/${id}`, {
    method: "DELETE",
  });
  return handleResponse(res);
}
