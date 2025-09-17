🧠 MVI Architecture – feature:task Module

This module implements the Model-View-Intent (MVI) pattern for managing the task list feature in a unidirectional and testable way.

⸻

🧱 Architecture Overview

UI (Compose Screen)
⇅
ViewModel (MviViewModel)
⇅
State (TaskListState)
⇅
Reducer (TaskListReducer)
⇅
Event (TaskListEvent)
⇅
UseCases (add/delete/update/get)
⇅
Repository -> Local DB (Room) / Sync (Firestore)

🔄 Unidirectional Flow
1. User interacts with UI
2. UI sends TaskListEvent to TaskListViewModel
3. ViewModel triggers logic and updates state using updateState { ... }
4. State (TaskListState) is collected in Compose and reflected in UI
5. Side-effects (like errors or navigation) are triggered via TaskListEffect

📦 Folder Structure

feature/
└── task/
├── presentation/
│   ├── TaskListRoute.kt
│   ├── TaskListScreen.kt
│   ├── TaskItem.kt
│   └── TaskListViewModel.kt
├── mvi/
│   ├── TaskListState.kt
│   ├── TaskListEvent.kt
│   ├── TaskListEffect.kt
│   └── TaskListReducer.kt

✅ Benefits
• Clean separation of concerns
• Predictable state
• Easier testing
• Scalable and maintainable codebase