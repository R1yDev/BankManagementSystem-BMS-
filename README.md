# 🏦 Banking Management System (BMS) — Java + Swing GUI [Full Guide]

## 📖 Overview
A **Java-based Banking Management System** built to demonstrate core OOP concepts alongside typical banking functionalities — featuring both a **Swing GUI** and an **interactive console mode**.

---

## 👥 User Roles

| Role | Capabilities |
|---|---|
| 🔑 **Admin** | Manage users & accounts, deposits/withdrawals, view statistics, delete accounts/operations |
| 👤 **User** | Log in, view & update personal info, check balance, view account operations |

---

## ✨ Main Features

- 🔐 **Authentication** — Login system for both Admin and User roles
- 🏧 **Account Management** — Add and delete accounts
- 💸 **Bank Operations**
  - Deposit
  - Withdrawal *(with insufficient-funds handling)*
- 📋 **Operation History**
  - View all operations *(Admin)*
  - View operations per specific account *(User)*
- 📊 **Statistics** — Bank-wide stats dashboard
- 🧬 **OOP Concepts Demonstrated**
  - **Inheritance** — `Admin` extends `User`
  - **Has-a relationship** — `Account` holds a `User` + list of `Operation`s
  - **Encapsulation** — private fields with getters/setters

---

## 📂 Project Structure

```text
banking-system/
├── src/
│   ├── Test.java                        # Demo scenarios + interactive mode
│   ├── models/
│   │   ├── User.java
│   │   ├── Admin.java
│   │   ├── Account.java
│   │   └── Operation.java
│   └── services/
│       ├── BankService.java             # Core business logic
│       └── SimpleGraphicInterface.java  # Swing GUI entry point
```

---

## ▶️ How to Run

### 🔑 Login Credentials

| Role | Username | Password |
|---|---|---|
| 🔑 Admin | `admin` | `admin123` |
| 👤 User | `rayenRT` | `pass123` |

---

### 🖥️ Option 1 — Swing GUI

```bash
cd banking-system
javac -d ../../out banking-system/src/**/*.java
java -cp ../../out services.SimpleGraphicInterface
```

### ⌨️ Option 2 — Console Interactive Mode

```bash
cd banking-system
javac -d ../../out banking-system/src/**/*.java
java -cp ../../out Test
```

---

## ⚠️ Notes / Limitations
- **In-memory only** — no real database; all data resets on exit (demonstration purposes).
- `Test.java` covers both **automated scenario-based testing** and a **keyboard-driven interactive mode**.
