## Quick Start (one copy/paste block)

```bash
# 1) Start PostgreSQL (Docker)
cd summarization-app/docker
docker compose up -d
docker ps

# 2) Run Backend (Spring Boot)
cd ../backend
./mvnw.cmd clean spring-boot:run
# Backend: http://localhost:8081
# Health:  http://localhost:8081/api/health

# 3) Run Frontend (React + Vite)
cd ../frontend
npm install
npm run dev
# Frontend:     http://localhost:5173
# Proxy health: http://localhost:5173/api/health
```bash
