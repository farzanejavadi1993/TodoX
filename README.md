# ✅ TodoX – Clean & Offline-First To-Do App

TodoX is a **modular Android To-Do application** built with modern Android development practices, designed to work **offline-first** and sync seamlessly with Firebase Firestore.

---

## 🚀 Features

- 📋 Create, update, and delete tasks
- 📴 Works fully offline – with **automatic sync** when internet is available
- 🔄 Powered by **WorkManager** for background sync
- ☁️ Firebase Firestore integration
- ✨ Modern architecture with Clean Architecture principles
- ✅ MVVM + MVI hybrid pattern
- 💉 Dependency Injection with Hilt
- 🧪 Unit & UI Testing support

---

## 🧱 Tech Stack

| Layer         | Libraries & Tools                     |
|---------------|----------------------------------------|
| UI            | Jetpack Compose, Navigation-Compose    |
| Domain        | Kotlin Coroutines, Sealed Classes      |
| Data          | Room, Firebase Firestore, WorkManager  |
| DI            | Hilt (with custom Worker injection)    |
| Testing       | JUnit, Turbine, Coroutine Test         |
| Build System  | Gradle Kotlin DSL + Version Catalog    |
| Modularized   | Feature-first structure (`:task`, `:auth`, etc.) |

---

## 🗂️ Module Structure
app/
├── core/
│ ├── common/
│ ├── domain/
│ └── model/
├── data/
│ └── task/
├── feature/
│ └── task/
└── ui/


---

## 🔁 Offline Sync Flow

1. Task operations (add/delete) are stored in a local queue (Room DB)
2. WorkManager periodically tries to sync with Firestore when online
3. After successful sync, operations are marked as done
4. In case of failure, retry mechanism with exponential backoff

---




UI tests and integration tests coming soon.
