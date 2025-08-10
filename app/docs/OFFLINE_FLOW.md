# 📴 Offline-First Flow

This document explains how the app behaves offline and how it syncs when the network becomes available again.

---

## High-Level Data Path


---

## Write Path (Add / Delete)

1. UI triggers `addTask(title)` or `removeTask(id)` in `TaskViewModel`.
2. Repository performs **local-first** write:
    - Insert/Delete into Room immediately (instant UI update).
    - Then attempts a **best-effort** write to Firebase (wrapped in `runCatching`).
3. If online, Firebase is updated near real-time.
4. If offline, local DB remains the source of truth until connectivity returns.

---

## Read Path

- The UI observes a `Flow<List<TaskModel>>` from Room.
- Changes appear instantly in the UI thanks to Room + Flow.

---

## Offline Behavior

- Adding or deleting tasks **works offline** and persists in Room.
- After reconnecting, pending operations are re-attempted (best-effort).
- UI remains responsive since it’s driven by local data.

---

## Future Work (Sprint 2 – Day 3)

- Introduce an operation queue for robust sync/retry.
- Conflict resolution policies.
- Two-way sync (merging remote changes into Room).
