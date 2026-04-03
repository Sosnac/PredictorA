#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e 

echo "🚀 Starting PredictorA Setup..."

# ==========================================
# 1. BACKEND SETUP
# ==========================================
echo "📦 Setting up the Backend..."

# Navigate to the backend folder
cd backend

# Copy environment variables if the .env file doesn't exist yet
if [ ! -f ".env" ]; then
    echo "Creating .env file..."
    cp .env.example .env
fi

# Install Node.js dependencies
echo "Installing npm dependencies..."
npm install

# Start PostgreSQL using Docker Compose in detached mode
echo "Starting Docker containers..."
docker-compose up -d

# Run Prisma database migrations
echo "Running database migrations..."
npm run migrate

# Start the backend development server in the background
echo "Starting backend dev server..."
npm run dev &
BACKEND_PID=$! # Capture the process ID of the backend server

# Go back to the root directory
cd ..

# ==========================================
# 2. ANDROID SETUP
# ==========================================
echo "📱 Setting up Android..."

# Navigate to the android folder
cd android

# Copy local properties if it doesn't exist yet
if [ ! -f "local.properties" ]; then
    if [ -f "local.properties.example" ]; then
        echo "Creating local.properties file..."
        cp local.properties.example local.properties
    else
        echo "⚠️ No local.properties.example found. You may need to create local.properties manually."
    fi
fi

# Go back to the root directory
cd ..

# ==========================================
# 3. FINISH
# ==========================================
echo "✅ Setup Complete!"
echo "🛠️  The backend is currently running in the background."
echo "📱 To run the mobile app, open the 'android/' folder in Android Studio, sync Gradle, and run it on your emulator."
echo "🛑 Press [CTRL+C] at any time to stop the backend server and exit."

# Wait for the backend process so the script doesn't exit immediately
wait $BACKEND_PID
