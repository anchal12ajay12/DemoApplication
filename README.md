# DemoApp

DemoApp is a simple Android application that allows users to enroll new users and view the existing ones. It utilizes Firebase for data storage, including the Firebase Realtime Database for user information and Firebase Storage for profile images.

## Features

- **Enroll New Users:** Capture user details such as name, date of birth, gender, country, state, hometown, phone number, and profile picture. The information is stored in the Firebase Realtime Database along with a timestamp.

- **View Existing Users:** Display a list of enrolled users with their basic details. Users can be deleted from the database, which also removes their profile picture from Firebase Storage.

## Prerequisites

- Android Studio installed
- A Firebase project with Realtime Database and Storage enabled

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/AnchalAjay/DemoApp.git
   ```

2. Open the project in Android Studio.

3. Connect the app to your Firebase project:

   - Follow the [Firebase Console](https://console.firebase.google.com/) to create a new project.
   - Set up a Realtime Database and Storage in your Firebase project.
   - Download the `google-services.json` file and place it in the `app` directory of your Android project.

4. Sync your project with the changes.

5. Run the application on an emulator or physical device.

## Libraries Used

- [Glide](https://github.com/bumptech/glide): For efficient loading and caching of images.
- [Firebase UI](https://github.com/firebase/FirebaseUI-Android): Provides a simple way to create interactive UIs for Firebase Realtime Database.

## Contributing

Feel free to open issues and pull requests. Contributions are welcome!
