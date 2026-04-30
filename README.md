# 🚀 DevSolver Backend

DevSolver is a scalable backend system for a developer-focused content platform, built using Spring Boot. It enables users to create, share, and interact with technical content through features like authentication, posting, tagging, commenting, voting, and bookmarking.

---

## 🧠 Overview

DevSolver is designed with real-world backend architecture principles:
- Stateless authentication using JWT
- Clean REST API design
- Scalable data handling with pagination and filtering
- Proper entity relationships and normalization
- Secure and maintainable service-layer logic

---

## ⚙️ Tech Stack

- Backend: Spring Boot, Spring Security, Spring Data JPA  
- Database: PostgreSQL  
- Authentication: JWT (JSON Web Tokens)  
- Build Tool: Maven  
- Other: Lombok, Hibernate  

---

## 🔐 Authentication

- User registration and login
- Password encryption using BCrypt
- JWT-based stateless authentication
- Secure access to protected endpoints

---

## 📝 Features

### 👤 User
- Register and login
- Update profile (username, bio)
- Upload avatar (file handling)
- Fetch current user details

---

### 📰 Posts
- Create, update, delete posts
- Retrieve posts with:
  - Pagination
  - Sorting
  - Filtering (by user, tag)
- Each post includes:
  - Author
  - Tags
  - Vote counts

---

### 🏷️ Tags
- Many-to-many relationship with posts
- Automatic creation and reuse
- Normalized (case-insensitive)

---

### 💬 Comments
- Add comments to posts
- Delete comments with ownership validation
- Timestamped using Instant (UTC-based)

---

### 🔖 Bookmarks
- Save and unsave posts
- Retrieve bookmarked posts
- Unique constraint (user + post)

---

### 🗳️ Voting System
- Upvote and downvote posts
- One vote per user per post
- Toggle behavior:
  - Same vote → removes vote
  - Different vote → updates vote
- Dynamic vote counts

---

## 🏗️ Architecture

```text

                ┌───────────────┐
                │   User Module │
                └──────┬────────┘
                       │
        ┌──────────────┼──────────────┐
        ▼              ▼              ▼
   Post Module    Comment Module   Auth Module
        │              │              │
        ▼              ▼              ▼
   Tag System      Voting System   JWT Security
        │              │
        ▼              ▼
   Bookmark System   Pagination

```

## 📊 API Design Highlights

- RESTful endpoint structure (/api/...)
- DTO-based request and response handling
- Consistent API response patterns
- Pagination using Pageable
- Query-based filtering:

GET /api/posts?userId=1  
GET /api/posts?tag=spring  

---

## 🗄️ Database Design

- Normalized schema with proper relationships:
  - User → Post (One-to-Many)
  - Post ↔ Tag (Many-to-Many)
  - User ↔ Post (Vote)
  - User ↔ Post (Bookmark)
  - Post → Comment (One-to-Many)

- Constraints:
  - Unique user-post vote
  - Unique user-post bookmark
  - Unique tag names

---

## 📂 Project Structure

controller/   → REST endpoints  
service/      → business logic  
repository/   → database access  
entity/       → JPA models  
dto/          → request/response objects  
security/     → JWT + filters  
util/         → helpers (JWT, etc.)  

---

## 🚀 Getting Started

### 1. Clone the repository

git clone https://github.com/your-username/devsolver-backend.git  
cd devsolver-backend  

---

### 2. Configure database

Update application.yml:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/devsolver_db
    username: your_username
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update

---

### 3. Run the application

./mvnw spring-boot:run  

---

### 4. Test APIs

Use:
- Postman
- cURL
- Swagger (optional future integration)

---

## 🔑 Example Endpoints

POST   /api/auth/register        → Register user  
POST   /api/auth/login           → Login user  
GET    /api/posts                → Get all posts  
POST   /api/posts                → Create post  
GET    /api/posts/{id}           → Get post by ID  
PUT    /api/posts/{id}           → Update post  
DELETE /api/posts/{id}           → Delete post  
POST   /api/posts/{id}/comments  → Add comment  
POST   /api/posts/{id}/vote      → Vote on post  
POST   /api/posts/{id}/bookmark  → Bookmark post  

---

## 🔒 Security Notes

- JWT used for authentication
- Stateless session management
- Passwords stored securely using hashing
- Sensitive data is not exposed in API responses

---

## 🚀 Future Improvements

- API documentation using Swagger/OpenAPI
- Role-based authorization
- Image storage using AWS S3
- Redis caching for performance
- Rate limiting for API protection

---

## 💡 Key Learnings

- Designing scalable REST APIs  
- Implementing JWT authentication  
- Managing complex entity relationships  
- Writing clean service-layer logic  
- Preventing backend performance issues  

---

## 📌 Author

Brajesh Prajapati  
Backend Developer | Java | Spring Boot  

---

## ⭐ Support

If you found this project useful, consider giving it a star on GitHub.
