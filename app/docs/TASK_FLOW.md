
---

## 📲 Add Task Flow

1. User enters a task title and taps the "Add" button.
2. The `TaskViewModel` calls `addTask(title)`.
3. The request is delegated to `TaskRepository`, which forwards it to `FirebaseTaskDataSource`.
4. The new task is stored in Firestore under the `tasks` collection.
5. The Flow is updated in ViewModel, and the UI is recomposed with the new task list.

---

## 🗑 Delete Task Flow

1. User clicks the delete icon on a task item.
2. The `TaskViewModel` calls `removeTask(id)`.
3. This is passed through the Repository to the data source.
4. The task is removed from Firestore.
5. ViewModel gets the updated Flow, triggering a UI update.

---

## 🛠 Technologies Used

- **Jetpack Compose** – Modern UI toolkit
- **Kotlin StateFlow** – Reactive UI state management
- **Hilt** – Dependency injection framework
- **Firebase Firestore** – Cloud-hosted NoSQL database
- **MVVM + Clean Architecture** – Modular, testable code structure
