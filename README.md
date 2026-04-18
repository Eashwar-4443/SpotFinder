# 🚗 Parking Management System

A full-stack web application designed to simplify and automate parking operations.  
Built with **Spring Boot**, **React + Bootstrap**, and **MySQL**, the system provides secure authentication, role-based access, and efficient management of parking slots, vehicles, and reservations.

---

## ✨ Features
- 🔐 **User Authentication**: Secure login with JWT and role-based access (Admin/User).
- 🅿️ **Slot Management**: Add, update, delete, book, and release slots with real-time availability.
- 🚘 **Vehicle Management**: Register and manage vehicles linked to users.
- 📅 **Reservation Module**: Create, update, and cancel reservations with conflict prevention.
- 📊 **Admin Dashboard**: Monitor users, vehicles, slots, and reservations.
- 📱 **Responsive UI**: Mobile-friendly design with Bootstrap styling.

---

## 🛠️ Tech Stack
- **Frontend**: React, Bootstrap, Axios
- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Database**: MySQL
- **Tools**: Postman (API testing), Maven, GitHub

---

## ⚙️ Project Structure
SpotFinder/
│── backend/                # Spring Boot backend
│   ├── src/main/java/...   # Controllers, Services, Repositories
│   └── src/main/resources/ # application.properties
│── frontend/               # React + Bootstrap frontend
│   ├── src/components/     # Reusable UI components
│   ├── src/pages/          # Page-level views
│   └── src/services/       # Axios API calls
│── docs/                   # ER diagrams, documentation
│── README.md
│── .gitignore

ER Diagram
Entities:

User → manages login and role

Vehicle → linked to user

Slot → parking slot details

Reservation → booking details linked to user + vehicle + slot


Future Enhancements
💳 Payment gateway integration (Stripe/Razorpay)

📩 Notifications (Email/SMS on booking)

🏢 Multi-lot support

📈 Analytics dashboard for admins
