![PredictorA🚀](https://capsule-render.vercel.app/api?type=waving&color=gradient&height=200&section=header&text=PredictorA🚀&fontSize=70&fontAlignY=40&desc=Your%AI%Powered%20Assistance&descAlignY=60&descAlign=50)

**Author**: David Sosnac 

Welcome to **PredictorA**, a full-stack application featuring a powerful Node.js backend and a sleek modern Android frontend.
**PredictorA** is a software that delivers real-time football (soccer) predictions, live match analysis, and deep statistical insights powered by machine learning. Built with modern Android architecture (Jetpack Compose + MVVM + Clean Architecture) and a Node.js/FastAPI backend, **PredictorA** is designed for football fans, data scientists, and developers alike.

Currently in active development — contributions are welcome!

## 📂 Project Structure

This repository is organized as a monorepo containing both the backend and the mobile application:

* `/backend` - Node.js server, PostgreSQL database (Dockerized), and Prisma ORM.
* `/android` - Android application built with Kotlin, Jetpack Compose, and Hilt.

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed on your machine:
* [Git](https://git-scm.com/)
* [Node.js](https://nodejs.org/) (v18+ recommended)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Must be running)
* [Android Studio](https://developer.android.com/studio)

---

## ⚡ Quick Start (Recommended)

The easiest way to get the project up and running is to use our automated launcher script. It will install dependencies, set up the database, and start the backend server for you.

**1. Clone the Repository**
```bash
git clone https://github.com/Sosnac/PredictorA.git
cd PredictorA
```
**2.Run the Launcher Script**

Make the script executable and run it:
```bash
chmod +x launcher.sh
./launcher.sh
```
**3. Run the Android App**

• Open Android Studio.

• Select File > Open and choose the android/ folder inside the cloned repository.

• Allow Gradle to sync completely.

• Click the Run button to launch the app on your emulator or physical device.

## ⚙️ Manual Setup

If you prefer to set up the environments manually, follow these steps instead of using the Quick Start script.

Backend Setup
```bash
cd backend
cp .env.example .env        # Fill in your environment variables if needed
npm install                 # Install dependencies
docker-compose up -d        # Start PostgreSQL container
npm run migrate             # Run database migrations
npm run dev                 # Start the development server
```

Android Setup
```bash
cd android
cp local.properties.example local.properties # Create local properties
```

• Open the android/ folder in Android Studio.

• Sync Gradle.

• Run on your device/emulator.

**🤝 Contributing**

Please read the Contributing.md file for details on our code of conduct and the process for submitting pull requests to us.

**📄 License**

This project is licensed under the terms found in the LICENSE file. See the Certificate and Licensing report for additional details.
