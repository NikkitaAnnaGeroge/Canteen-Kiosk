# 🧾 AdminController — Canteen Management System

The **AdminController** is the central controller responsible for managing all **administrator operations** within the Canteen Management System. It serves as the bridge between the backend services and the user interface, handling admin authentication, menu management, and order tracking.

---

## 🚀 Features

### 🔐 Admin Authentication

* Displays a secure login dialog for administrators.
* Verifies credentials through the `AdminService`.
* Grants access to admin features only upon successful authentication.

### 🍽 Menu Management

* Allows viewing, adding, updating, and deleting menu items.
* Automatically refreshes the displayed menu after changes.
* Validates inputs such as name, price, and category before submission.

### 📦 Order Management

* Enables viewing of all customer orders.
* Retrieves detailed information for each order.
* Updates order statuses (e.g., Pending → Completed).
* Refreshes the order list automatically after updates.

### 🧠 Validation and Error Handling

* Validates menu input fields before adding or updating.
* Prevents invalid or empty data entries.
* Displays clear success or error messages using dialogs.

---

## 🧩 Responsibilities

The **AdminController** acts as a **communication bridge** between the following layers:

* **Model Layer:** Data classes like `MenuItem` and `Order`.
* **Service Layer:** Business logic handled by `MenuService`, `OrderService`, and `AdminService`.
* **View Layer:** User interface components such as `AdminView` and `AdminLoginDialog`.

---

## 🧱 Design Pattern

This controller follows the **Model–View–Controller (MVC)** architecture:

* **Model:** Represents application data (`MenuItem`, `Order`).
* **View:** Manages the graphical interface (`AdminView`, `AdminLoginDialog`).
* **Controller:** Handles user interactions and coordinates updates (`AdminController`).

