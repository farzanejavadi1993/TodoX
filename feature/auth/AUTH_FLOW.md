# 🔐 Authentication Flow

This document explains how the authentication flow works in the app.

---

## 🏗 Structure

- **AuthViewModel.kt**
    - Manages login/signup using FirebaseAuth
    - Emits `AuthUiState` for loading, success, and error

- **SignInScreen.kt / SignUpScreen.kt**
    - Composable UIs that interact with the ViewModel
    - Email and password fields
    - Buttons call ViewModel functions

- **AuthUiState.kt**
    - Holds UI state: loading, success, error

---

## 🔄 Flow

1. User enters email & password
2. Clicks `Login` or `Register`
3. UI calls corresponding function in `AuthViewModel`
4. FirebaseAuth processes request
5. Result is emitted via StateFlow
6. UI reacts:
    - Shows loading indicator
    - Shows error if failed
    - Shows success or navigates next

---

## 🔍 Manual Testing

- Tested both login & signup in Emulator
- Firebase Console confirms user registration
- Error messages displayed on failure
