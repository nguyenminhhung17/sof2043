# 💻 Sample Project - SOF2043

---
📝 Project Introduction

Project name: Sales Management Application (Desktop)

Course: SOF2043 – Java Desktop

Description:
This project was developed to apply knowledge of Java Swing for UI design and JDBC for database connectivity with SQL Server.
It supports product management, billing, user management, and provides Admin/Staff role-based access.

---

## ⚙️ Technologies Used
Technology	Description
Java Swing	Build desktop user interface (UI)
JDBC	Connect and interact with SQL Server
SQL Server	Store application data
Maven (optional)	Manage libraries and project structure
MVC Pattern	Organize project using Model–View–Controller

---

## 🔧 Key Features

✅ Login & Role-based authentication (Admin / Staff)

✅ Product management (Add, Edit, Delete, Search)

✅ Billing & sales management

✅ Revenue statistics

✅ Change password, basic security

✅ User-friendly UI with JTable, JComboBox, JDateChooser, etc.
---

## 📁 Project Structure

<pre>Project/
├── src/
│   ├── dao/            # DAO interfaces & implementations
│   │   ├── BillDAO.java
│   │   ├── BillDAOImpl.java
│   │   └── ...
│   ├── model/          # Entity classes
│   │   ├── Bill.java
│   │   ├── User.java
│   │   └── ...
│   ├── view/           # User Interface
│   │   ├── LoginJDialog.java
│   │   ├── DrinkJDialog.java
│   │   └── ...
│   ├── controller/     # Controllers
│   │   ├── LoginController.java
│   │   ├── BillController.java
│   │   └── ...
│   └── utils/          # Common utilities
│       ├── XJdbc.java
│       ├── XDate.java
│       └── ...
│
├── lib/                # External libraries
│   └── mysql-connector-java.jar
├── database/           # Database initialization script
│   └── polycafe.sql
└── README.md           # Project description</pre>


## 1️⃣ Requirements

JDK 21

NetBeans 27 (or later)

SQL Server (2019+ recommended)

MySQL Connector / SQL Server JDBC Driver (already included in /lib)

##  👨‍🎓 Team Member

Leaf
## 📜 License

This project is for educational purposes only – course SOF2043 – Java Desktop.
