# 🧱 Architecture Overview - TodoX

## 🧩 Structure

- Project is modularized by feature and layer.
- Each feature (e.g. tasklist, auth, project) has its own presentation and DI logic.
- Core modules contain shared logic, design system, and base UI components.

## 📦 Modules

- `app` – Main application and navigation
- `core:designsystem` – Material3 Theme, typography, color
- `core:ui` – Shared Composables like buttons, app bars
- `core:domain` – Interfaces and use cases
- `core:data` – Firebase and local data sources
- `feature:tasklist` – Task list UI and logic
- `feature:auth`, `project`, `search`, `setting` – Coming soon

## 🔁 Architecture Pattern

- Clean Architecture + MVI
- ViewModel uses `StateFlow` and emits `UiState`
- View subscribes with `collectAsState()`
