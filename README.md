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
   - **Set up a Realtime Database and Storage in your Firebase project.**
      - To set up Firebase for your Android project, you need to follow these steps. These instructions assume that you already have a Google account:

            ### Step 1: Create a Firebase Project
            
            1. Go to the [Firebase Console](https://console.firebase.google.com/).
            2. Click on "Add Project."
            3. Enter a name for your project and select your country/region.
            4. Optionally, enable Google Analytics for your project.
            5. Click on "Create Project."
            
            ### Step 2: Add an Android App to Your Firebase Project
            
            1. In the Firebase Console, click on "Add app" and select the Android icon.
            2. Enter your Android package name (you can find this in your `build.gradle` file).
            3. Optionally, enter an app nickname.
            4. Click on "Register app."
            
            ### Step 3: Download the Configuration File
            
            1. After registering your app, click on "Continue to console."
            2. Click on the gear icon next to "Project Overview" and select "Project settings."
            3. In the "Your apps" section, locate your Android app.
            4. Click on the Android icon to download the `google-services.json` file.
            
            ### Step 4: Add the Configuration File to Your Android App
            
            1. Move the downloaded `google-services.json` file to the `app` directory of your Android Studio project.
               
            ### Step 5: Add Firebase SDK to Your App
            
            1. In your app-level `build.gradle` file, add the following dependencies:
            
               ```gradle
               implementation 'com.google.firebase:firebase-database:23.0.0'
               implementation 'com.google.firebase:firebase-storage:22.0.0'
               ```
            
               Note: Replace the version numbers with the latest available versions.
            
            2. Sync your project with the changes.
            
            ### Step 6: Enable Firebase Services
            
            1. Go back to the Firebase Console.
            2. In the left-hand navigation, click on "Database" and follow the prompts to set up the Realtime Database.
            3. In the left-hand navigation, click on "Storage" and follow the prompts to set up Firebase Storage.
            
            ### Step 7: Configure Realtime Database Rules
            
            1. In the Firebase Console, go to "Database."
            2. Click on "Create Database."
            3. Choose "Start in test mode" for simplicity. You can adjust the rules later based on your security needs.
            
            ### Step 8: Run Your App
            
            1. After completing the setup, run your Android app on an emulator or physical device.
            2. Verify that your app is able to connect to Firebase, and you can perform the desired actions.
            
            That's it! You've successfully set up Firebase for your Android project. You can now use Firebase Realtime Database and Storage in your app. 
               - Download the `google-services.json` file and place it in the `app` directory of your Android project.

4. Sync your project with the changes.

5. Run the application on an emulator or physical device.

## Libraries Used

- [Glide](https://github.com/bumptech/glide): For efficient loading and caching of images.
- [Firebase UI](https://github.com/firebase/FirebaseUI-Android): Provides a simple way to create interactive UIs for Firebase Realtime Database.

## Contributing

Feel free to open issues and pull requests. Contributions are welcome!
