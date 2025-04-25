# 📚 Library Project

Welcome to the **Library Project**! 🎉  
A Java application using **Hibernate** to manage a **MySQL** database and simulate a book lending system.

---

## 🎯 Objectives
- Manage the book catalog 📖  
- Register users and loans 🧑‍🤝‍🧑  
- Demonstrate CRUD operations (Create, Read, Update, Delete)  
- Learn to configure and use Hibernate with MySQL

---

## ✨ Features
- 📘 **Book CRUD**: add, list, edit, and delete books  
- 👥 **User CRUD**: manage library users  
- 🔄 **Loan Management**: track when a user borrows or returns a book  
- 🔧 **Hibernate Configuration** with XML and annotations  
- 🗂️ **Modular and organized project structure**

---

## 🚀 Technologies & Tools
- **Java 8+**  
- **Hibernate ORM 5.x**  
- **MySQL Server 5.7+**  
- **Maven**  
- **Log4j** for logging  
- Recommended IDE: IntelliJ IDEA or Eclipse

---

## 🔧 Prerequisites
1. JDK 8 or higher installed  
2. MySQL installed and running  
3. Maven installed  
4. (Optional) IDE like IntelliJ IDEA or Eclipse  

---

## 🛠️ Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/AndresLapSol/proyectoBiblioteca.git
cd proyectoBiblioteca
```

### 2. Configure the database
- Create a MySQL database, e.g., `library_db`  
- Edit `src/main/resources/hibernate.cfg.xml` with your credentials:
```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/library_db</property>
<property name="hibernate.connection.username">YOUR_USERNAME</property>
<property name="hibernate.connection.password">YOUR_PASSWORD</property>
```

### 3. Build the project
```bash
mvn clean install
```

### 4. Run the application
```bash
mvn exec:java -Dexec.mainClass="org.example.MainApp"
```
- You will see console logs showing the library operations 📋

---

## 📂 Project Structure
```
proyectoBiblioteca/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── entity/      # Entity classes (Book, User, Loan)
│   │   │   ├── dao/         # Data Access Objects
│   │   │   └── util/        # Utilities (HibernateUtil)
│   │   └── resources/
│   │       ├── hibernate.cfg.xml
│   │       └── log4j.properties
│   └── test/
│       └── java/org/example/ # Unit tests
├── sample_catalog.dat       # Example catalog data
├── pom.xml
└── LICENSE
```

---

## 🤝 Contributing
Contributions are welcome!  
1. Fork the repo  
2. Create a branch (`git checkout -b feature/YourFeature`)  
3. Make your changes and commit (`git commit -m "Add new feature"`)  
4. Push to your branch and open a Pull Request 📩

---

## ⚖️ License
This project is licensed under **CC0-1.0**. See the `LICENSE` file for details.

---

## 📫 Contact
Developed by **AndresLapSol**  
GitHub: [AndresLapSol](https://github.com/AndresLapSol)  
Email: `andreslapsol@gmail.com`
