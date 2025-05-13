# Smart Hostel Management System

A Java + MySQL based backend system to manage hostel room allocations, check-ins, check-outs, and room status, with a simple Swing UI.

## Features
- Check-in new guests with automatic room allocation
- Check-out guests and mark rooms empty
- View current status of all rooms in a table
- Basic UI built with Java Swing
- Data stored persistently using MySQL

## Technologies Used
- Java (Core + OOP)
- Swing (for UI)
- JDBC (Java Database Connectivity)
- MySQL (backend database)
- Git & GitHub (version control)

## Project Structure
src/
├── model/
│ └── Room.java
├── dao/
│ └── RoomDAO.java
├── database/
│ └── DBConnection.java
├── HostelUI.java
└── Main.java

## How to Run
1. Create a MySQL database:
CREATE DATABASE hostel_db;
USE hostel_db;
CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(100),
    status VARCHAR(20) DEFAULT 'Empty'
);
