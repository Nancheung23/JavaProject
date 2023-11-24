# Java Projects - TAMK Software Engineering 2023

A collection of Java projects I've developed for the software engineering program at Tampere University of Applied Sciences in 2023. Each project is briefly described with its main rules and functionalities.

## 1. Lottery Machine
**Description:** A Java application that simulates a lottery draw system.

**Rules and Features:**
- Draw of 7 main numbers and 3 bonus numbers from a pool of 1 to 39.
- Functions:
  - `sort()`: Orders numbers from largest to smallest.
  - `checkDuplicate()`: Ensures no duplicate numbers.
  - `random()`: Generates random lottery numbers.
- User System: Implements classes for `User`, `Ticket`, and `TicketOperator`.
- Prize structure based on the combination of main and bonus numbers, ranging from €5 to €15,000,000.
- Extra Functions:
  - User continuation check.
  - Input type validation (integer check).

## 2. Standing Table
**Description:** A system to manage and display standings for a sports league.

**Rules and Features:**
- Input game information, including team names and goals.
- Record player names and goals in a hashmap; return null for zero goals.
- Main functionalities include adding games, displaying standings, player list, and searching for players.
- Utilizes ArrayLists to manage games and teams information.
- Implements an inner class `Pair` for pairing player information.
- Extra Functions:
  - Input verification.
  - Improved data structures and output formatting.

## 3. Accommodation System
**Description:** A system to manage room bookings for a hotel.

**Rules and Features:**
- Main functionalities: registration, cancellation, displaying all bookings, and searching.
- Three booking modes: basic, advanced, and deluxe.
- Room availability checks and allocation.
- Deluxe mode allows user choice or random room selection.
- Integration of room availability across all modes.
- Extra Functions:
  - Cancellation by room or order ID.
  - Search functionality for room and order IDs.
  - Validation of user input, including phone numbers.

## 4. ATM System
**Description:** A basic ATM system simulating banking operations.

**Rules and Features:**
- User functionalities: registration, login, deposit, withdrawal, transfer, balance check, and password modification.
- Validation of user input during registration (integer and double).
- Password checks during login with a maximum of three attempts.
- Transfer functionality requires at least two accounts.
- Extra Functions:
  - Enhanced password strength requirements.
  - Navigation options at each step for user convenience.

---

For more information or to discuss these projects, feel free to contact me at yannan.zhang@tuni.fi
