adm# Banking Management System (BMS)

## Role of this project
This project is a **Java console-based Banking Management System** built to demonstrate common OOP concepts and typical banking functionalities.

It provides two main user roles:
- **Admin**: manages users and accounts, performs deposits/withdrawals, views operations/statistics, and can delete accounts/operations.
- **User**: can log in, view and update personal information, check balances, and view the operations of their accounts.

## Main features
- **Authentication / login** for Admin and Users
- **Account management**: add/delete accounts
- **Bank operations**:
  - Deposit
  - Withdrawal (with insufficient-funds handling)
- **Operation history**:
  - List all operations (admin)
  - List operations for a specific user account
- **Statistics**:
  - Display bank-related statistics
- **OOP demonstrations**:
  - **Inheritance** (e.g., `Admin` extends `User`)
  - **Has-a relationship** (e.g., `Account` holds a `User` and a list of `Operation`s)
  - **Encapsulation** (private fields with getters/setters)

## Project structure
- `banking-system/src/Test.java`
  - Runs multiple scenarios demonstrating the system behavior (login, CRUD actions, deposits/withdrawals, OOP principles, and interactive mode).
- `banking-system/src/models/`
  - Domain classes such as `User`, `Admin`, `Account`, and `Operation`.
- `banking-system/src/services/`
  - `BankService` (business logic) and `SimpleGraphicInterface` (if present in the project).

## How to run
**Login credentials (used in the provided interactive/demo flow):**
- **Admin**: `admin` / `admin123`
- **User**: `rayenRT` / `pass123`
This project supports **two run modes**:

### Option 1 — Graphic interface (Swing GUI)
Run the Swing UI from `SimpleGraphicInterface`.

```bash
cd banking-system
javac -d ../../out banking-system/src/**/*.java
java -cp ../../out services.SimpleGraphicInterface
```

### Option 2 — Menu (Console interactive mode)
Run `Test`, then choose options from the console menu.

```bash
cd banking-system
javac -d ../../out banking-system/src/**/*.java
java -cp ../../out Test
```

## Notes
- This is a **console application** (no real database). Data is handled in-memory for demonstration/testing purposes.
- The `Test` class includes both **scenario-based tests** and an **interactive keyboard-driven mode**.

