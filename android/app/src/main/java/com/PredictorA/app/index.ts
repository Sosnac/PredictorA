import "express-async-errors";
import express, { Express } from "express";
import http from "http";
import cors from "cors";
import helmet from "helmet";
import compression from "compression";
import morgan from "morgan";
import { Server as SocketIOServer } from "socket.io";
import { rateLimit } from "express-rate-limit";
import { env } from "./config/env";
import { logger } from "./utils/logger";
import { errorHandler } from "./middleware/errorHandler";
import { notFound } from "./middleware/notFound";
import { authRouter } from "./api/routes/auth.routes";
import { matchRouter } from "./api/routes/match.routes";
import { predictionRouter } from "./api/routes/prediction.routes";
import { userRouter } from "./api/routes/user.routes";
import { setupWebSocket } from "./services/websocket.service";
import { prisma } from "./config/database";

const app: Express = express();
const server = http.createServer(app);

// ── Socket.IO ─────────────────────────────────────────────
const io = new SocketIOServer(server, {
  cors: {
    origin: env.CORS_ORIGIN,
    methods: ["GET", "POST"],
    credentials: true,
  },
});
setupWebSocket(io);

// ── Middleware ────────────────────────────────────────────
app.use(helmet());
app.use(
  cors({
    origin: env.CORS_ORIGIN,
    credentials: true,
    methods: ["GET", "POST", "PUT", "DELETE", "PATCH"],
  })
);
app.use(compression());
app.use(morgan(env.NODE_ENV === "production" ? "combined" : "dev"));
app.use(express.json({ limit: "10mb" }));
app.use(express.urlencoded({ extended: true }));

// Global rate limiter
app.use(
  rateLimit({
    windowMs: 15 * 60 * 1000, // 15 minutes
    max: 200,
    standardHeaders: true,
    legacyHeaders: false,
    message: { error: "Too many requests. Please try again later." },
  })
);

// ── Health Check ─────────────────────────────────────────
app.get("/health", (_req, res) => {
  res.json({
    status: "ok",
    service: "PredictorA API",
    version: "1.0.0",
    timestamp: new Date().toISOString(),
  });
});

// ── API Routes ────────────────────────────────────────────
const API_V1 = "/v1";
app.use(`${API_V1}/auth`, authRouter);
app.use(`${API_V1}/matches`, matchRouter);
app.use(`${API_V1}/predictions`, predictionRouter);
app.use(`${API_V1}/user`, userRouter);

// Swagger docs
if (env.NODE_ENV !== "production") {
  const { setupSwagger } = await import("./config/swagger");
  setupSwagger(app);
}

// ── Error Handling ───────────────────────────────────────
app.use(notFound);
app.use(errorHandler);

// ── Start Server ─────────────────────────────────────────
const PORT = env.PORT;

async function start() {
  try {
    await prisma.$connect();
    logger.info("✅ Database connected");

    server.listen(PORT, () => {
      logger.info(`🚀 PredictorA API running on port ${PORT}`);
      logger.info(`📡 WebSocket server ready`);
      if (env.NODE_ENV !== "production") {
        logger.info(`📚 Swagger docs: http://localhost:${PORT}/docs`);
      }
    });
  } catch (error) {
    logger.error("❌ Failed to start server:", error);
    process.exit(1);
  }
}

process.on("SIGTERM", async () => {
  logger.info("SIGTERM received, shutting down gracefully...");
  await prisma.$disconnect();
  server.close(() => process.exit(0));
});

start();

export { app, io };
