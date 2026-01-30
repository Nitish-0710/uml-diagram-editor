![Java](https://img.shields.io/badge/Java-17%2B-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-GUI-blue)
![UML](https://img.shields.io/badge/UML-Diagram%20Editor-yellow)
![Platform](https://img.shields.io/badge/Platform-Windows-lightgrey)
![Executable](https://img.shields.io/badge/Executable-.exe-success)
![Project](https://img.shields.io/badge/Type-Academic-green)


# 🧩 Unified UML Diagram Editor

A **JavaFX-based Unified UML Diagram Editor** that allows users to create, edit, and manage multiple UML diagram types within a single application. The editor provides an intuitive drag-and-drop interface, dynamic toolbars, editable diagram elements, and automatic arrow-based connections.

This project is designed for **academic use, learning UML concepts, and lightweight diagram creation**, without relying on heavy external tools.

---

## 📌 Supported Diagram Types

* **Class Diagram**
* **Activity Diagram**
* **Object Diagram**
* **Use Case Diagram**

Each diagram type has its own contextual toolbar and supported elements.

---

## ✨ Key Features

* 🧠 **Unified Editor** – Switch between diagram types in a single window
* 🖱️ **Drag & Drop** – Move elements freely on the canvas
* 🔗 **Smart Connections** – Arrow connections auto-adjust when shapes move
* ✏️ **Double-Click Editing** – Edit names, attributes, methods, labels, etc.
* 🧰 **Dynamic Toolbars** – Tools change based on selected diagram type
* 🗑️ **Delete with Cleanup** – Removes shapes along with all connections
* 📄 **Multi-Page Canvas Ready** (via tab structure)
* 🪟 **Executable Version Available** (.exe)

---

## 🖼️ Screenshots

### 📘 Class Diagram

![Class Diagram](Screenshots/Class%20Diagram.png)

### 🔁 Activity Diagram

![Activity Diagram](Screenshots/Activity%20Diagram.png)

### Object Diagram

![Object Diagram](Screenshots/Object%20Diagram.png)

### 👤 Use Case Diagram

![Use Case Diagram](Screenshots/Use%20Case%20Diagram.png)

---

## 🗂️ Project Structure

```
src/
└── main/
    ├── core/
    │   ├── BaseDiagramEditor.java
    │   ├── DiagramElement.java
    │   ├── Connectable.java
    │   ├── Connection.java
    │   └── ConnectionUtils.java
    │
    ├── shapes/
    │   ├── BaseShape.java
    │   ├── ClassShape.java
    │   ├── ObjectShape.java
    │   ├── ActivityNode.java
    │   ├── ActorShape.java
    │   └── UseCaseShape.java
    │
    ├── ui/
    │   ├── EditDialog.java
    │   ├── ShapeEditingHelper.java
    │   └── ToolbarBuilder.java
    │
    ├── UnifiedDiagramEditor.java
    ├── MainLauncher.java
    └── JavaFXLauncher.java
```

---

## 🧠 Architecture Overview

* **BaseDiagramEditor**
  Abstract editor handling canvas, selection, dragging, and connection logic.

* **DiagramElement & Connectable**
  Common contracts for all shapes and connectable elements.

* **ConnectionUtils**
  Handles smart arrow drawing between shape borders with real-time updates.

* **Shapes Package**
  Concrete UML components (Class, Interface, Activity nodes, Actors, Use Cases).

* **UI Package**
  Editing dialogs, mouse interaction helpers, and toolbar generation.

* **UnifiedDiagramEditor**
  Central controller managing diagram switching and user actions.

---

## ▶️ How to Run

### Option 1: Run from Source

1. Install **Java JDK (11 or above)**
2. Ensure **JavaFX** is properly configured
3. Run:

   ```bash
   MainLauncher.java
   ```

---

### Option 2: Run Executable (.exe) ✅

The project has been successfully:

* Compiled into a **.jar**
* Converted into a **Windows .exe executable**

➡️ **No Java or IDE required** to run the `.exe` file.
Simply double-click to launch the application.

> This makes the project easy to distribute and demo on any Windows machine.

---

## 🎯 Intended Use

* UML diagram practice for students
* Academic mini / major project
* Demonstrating JavaFX GUI + OOP design
* Lightweight UML modeling tool

---

## 🚀 Future Enhancements (Optional)

* Export diagrams as **PNG / PDF**
* Save & load diagrams from files
* Zoom and pan support
* Undo / redo actions
* Sequence & State diagrams
* Dark mode UI

---

## 👨‍💻 Team & Credits

Created by:
**Nitish Sahu**
**Palash Sahuji**
**Manthan Sali**
**Aditya Rana**

---

## 📜 License

This project is intended for **educational and academic use**.
Feel free to fork, modify, and enhance it.

---
