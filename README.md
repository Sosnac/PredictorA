# ⚽ PredictorA

<div align="center">

![PredictorA Banner](docs/banner.svg)

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Platform-Android-green?logo=android)](https://play.google.com/store)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple?logo=kotlin)](https://kotlinlang.org)
[![Node.js](https://img.shields.io/badge/Node.js-20.x-green?logo=node.js)](https://nodejs.org)
[![CI/CD](https://github.com/yourusername/PredictorA/actions/workflows/ci.yml/badge.svg)](https://github.com/yourusername/PredictorA/actions)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)
[![Open Source](https://img.shields.io/badge/Open%20Source-%E2%9D%A4-red)](https://opensource.org)

**Real-time football predictions and live match analytics — open source, community-driven.**

[Features](#features) • [Tech Stack](#tech-stack) • [Getting Started](#getting-started) • [API Docs](#api-docs) • [Contributing](#contributing) • [License](#license)

</div>

---

## 🎯 Overview

**PredictorA** is an open-source Android application that delivers real-time football (soccer) predictions, live match analysis, and deep statistical insights powered by machine learning. Built with modern Android architecture (Jetpack Compose + MVVM + Clean Architecture) and a Node.js/FastAPI backend, PredictorA is designed for football fans, data scientists, and developers alike.

> **Currently in active development** — contributions are welcome!

---

## ✨ Features

- 🔐 **Secure Authentication** — JWT-based login/register with biometric support
- ⚡ **Real-Time Predictions** — Live ML-powered match outcome predictions
- 📊 **Live Match Analysis** — Real-time stats: xG, possession, heat maps, pass networks
- 🏆 **League Coverage** — Premier League, La Liga, Bundesliga, Serie A, Ligue 1, Champions League
- 📈 **Historical Analytics** — Deep historical data with trend visualization
- 🔔 **Smart Notifications** — Push alerts for predictions, goals, and match events
- 🌙 **Dark/Light Theme** — Dynamic theming with Material You support
- 📱 **Offline Mode** — Cached predictions available without internet

---

## 🛠 Tech Stack

### Android (Frontend)
| Technology | Version | Purpose |
|-----------|---------|---------|
| **Kotlin** | 2.0 | Primary language |
| **Jetpack Compose** | BOM 2024.x | Declarative UI |
| **MVVM + Clean Architecture** | — | Architecture pattern |
| **Hilt** | 2.51 | Dependency Injection |
| **Retrofit + OkHttp** | 2.11 | HTTP networking |
| **Room** | 2.6 | Local database |
| **DataStore** | 1.1 | Preferences storage |
| **Coroutines + Flow** | 1.8 | Async/reactive data |
| **Coil** | 2.6 | Image loading |
| **Navigation Compose** | 2.8 | Screen navigation |
| **Firebase** | BOM 33.x | Auth, FCM, Analytics |

### Backend
| Technology | Version | Purpose |
|-----------|---------|---------|
| **Node.js** | 20 LTS | API server runtime |
| **Express.js** | 4.x | REST API framework |
| **Python / FastAPI** | 3.12 | ML prediction service |
| **PostgreSQL** | 16 | Primary database |
| **Redis** | 7 | Caching & pub/sub |
| **Socket.IO** | 4.x | Real-time events |
| **JWT** | — | Authentication tokens |
| **Docker** | — | Containerization |

### ML / Data
| Technology | Purpose |
|-----------|---------|
| **scikit-learn** | Prediction models |
| **pandas / numpy** | Data processing |
| **Apache Kafka** | Real-time data streaming |
| **Football-Data.org API** | Live match data source |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17+
- Node.js 20+
- Python 3.12+
- Docker & Docker Compose
- PostgreSQL 16 (or use Docker)

### 1. Clone the Repository
```bash
git clone https://github.com/Sosnac/PredictorA.git
cd PredictorA
```

### 2. Backend Setup
```bash
cd backend
cp .env.example .env        # Fill in your API keys
npm install
docker-compose up -d        # Start Postgres + Redis
npm run migrate             # Run DB migrations
npm run dev                 # Start dev server → http://localhost:3000
```

### 3. Android Setup
```bash
cd android
cp local.properties.example local.properties   # Add your SDK path & API base URL
```
Open `android/` in Android Studio → Sync Gradle → Run on device/emulator.

### 4. Environment Variables
```env
# backend/.env
NODE_ENV=development
PORT=3000
DATABASE_URL=postgresql://user:password@localhost:5432/predictora
REDIS_URL=redis://localhost:6379
JWT_SECRET=your_super_secret_key_here
JWT_REFRESH_SECRET=your_refresh_secret_here
FOOTBALL_DATA_API_KEY=your_football_data_api_key
FIREBASE_SERVICE_ACCOUNT_KEY=path/to/serviceAccountKey.json
```

---

## 📁 Project Structure

```
PredictorA/
├── android/                          # Android application
│   └── app/src/main/java/com/predictora/
│       ├── auth/                     # Authentication logic
│       ├── data/
│       │   ├── api/                  # Retrofit API interfaces
│       │   ├── models/               # Data models / DTOs
│       │   └── repository/           # Repository pattern
│       ├── ui/
│       │   ├── login/                # Login screen
│       │   ├── register/             # Register screen
│       │   ├── home/                 # Dashboard
│       │   ├── predictions/          # Predictions feed
│       │   ├── analysis/             # Live analysis
│       │   └── profile/              # User profile
│       ├── utils/                    # Utility classes
│       └── di/                       # Hilt DI modules
├── backend/
│   └── src/
│       ├── api/routes/               # API route handlers
│       ├── models/                   # DB models (Prisma)
│       ├── services/                 # Business logic
│       ├── middleware/               # Auth, rate-limit, etc.
│       └── config/                   # App configuration
├── docs/                             # Documentation
├── scripts/                          # Dev & deployment scripts
└── .github/
    ├── workflows/                    # CI/CD pipelines
    └── ISSUE_TEMPLATE/               # GitHub issue templates
```

---

## 📡 API Documentation

Base URL: `https://api.predictora.app/v1`

| Endpoint | Method | Auth | Description |
|---------|--------|------|-------------|
| `/auth/register` | POST | ❌ | Register new user |
| `/auth/login` | POST | ❌ | Login, returns JWT |
| `/auth/refresh` | POST | ✅ | Refresh access token |
| `/matches/live` | GET | ✅ | Get live matches |
| `/matches/{id}/prediction` | GET | ✅ | Get match prediction |
| `/matches/{id}/analysis` | GET | ✅ | Get live analysis |
| `/leagues` | GET | ✅ | List all leagues |
| `/user/profile` | GET | ✅ | Get user profile |

Full API docs available at `/docs` (Swagger UI) when running locally.

---

## 🤝 Contributing

We love contributions! PredictorA is community-driven.

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m 'feat: add amazing feature'`
4. Push to the branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

Please read [CONTRIBUTING.md](CONTRIBUTING.md) and follow our [Code of Conduct](CODE_OF_CONDUCT.md).

### Good First Issues
Check the [`good first issue`](https://github.com/yourusername/PredictorA/labels/good%20first%20issue) label to get started.

---

## 📜 License

This project is licensed under the **Apache License 2.0** — see the [LICENSE](LICENSE) file for details.

> **Why Apache 2.0?** It allows free use, modification, and distribution — including in commercial apps — while providing patent protection for contributors and users. Ideal for open-source Android apps distributed on the Play Store.

---

## 🌟 Acknowledgements

- [Football-Data.org](https://www.football-data.org/) for the live match data API
- [Statsbomb](https://statsbomb.com/open-data/) for open football analytics data
- The Android and Kotlin open-source community

---

<div align="center">
  <strong>⭐ Star this repo if you find it useful!</strong><br/>
  Made with ❤️ by the PredictorA community
</div>
