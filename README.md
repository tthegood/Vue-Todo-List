# Vue Todo Monorepo

A small monorepo combining:

- **Vue 3 Todo List** front‑end (Vite + Composition API)
- **Java Spring Boot Todo Service** backend (`services/java-todo-service`)

---

## 🚀 Prerequisites

- **Node.js** v16+ & **npm**
- **Java 17 JDK**
- **Maven** (or use the Maven Wrapper)

---

## 📁 Project Structure

```
vue-app/
├── README.md
├── package.json
├── vite.config.js
├── index.html
├── src/
│   ├── main.js
│   ├── App.vue
│   └── components/
│       └── TodoList.vue
└── services/
    └── java-todo-service/
        ├── pom.xml
        └── src/
            ├── main/java/com/yourorg/todo/…
            └── main/resources/
```

---

## 🛠 Setup

1. **Clone the repo**
   ```bash
   git clone https://github.com/tthegood/Vue-Todo-List.git
   cd vue-app
   ```

2. **Install front‑end dependencies**
   ```bash
   npm install
   ```

3. **Build or run the Java service**
   ```bash
   cd services/java-todo-service
   mvn clean package -DskipTests
   # or, if you prefer the wrapper:
   # ./mvnw clean package -DskipTests
   ```

---

## ▶️ Running

- **Both servers together**
  ```bash
  npm start
  ```
  - Front‑end → http://localhost:5173
  - API → http://localhost:8080/api/todos

- **Front‑end only**
  ```bash
  npm run serve
  ```

- **API only**
  ```bash
  npm run start:api
  ```

---

## 📦 Production Build

- **Front‑end**:
  ```bash
  npm run build
  ```
  → outputs static files to `dist/`.

- **API**:
  Your fat‑JAR is in
  `services/java-todo-service/target/java-todo-service-1.0.0.jar`.

---

## 🔄 Workflow Tips

- After changing Java code, rerun:
  ```bash
  cd services/java-todo-service
  mvn clean package -DskipTests
  ```
- To just restart the API (without rebuilding):
  ```bash
  cd services/java-todo-service
  mvn spring-boot:run
  ```
- You can also call the Maven Wrapper if you added it:
  ```bash
  npm run start:api   # will invoke mvnw.cmd on Windows
  ```
