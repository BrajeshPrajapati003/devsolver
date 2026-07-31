const blogs = [
  {
    id: 1,
    title: "Building JWT Authentication with Spring Boot",
    excerpt:
      "Learn how to secure your REST APIs using Spring Security and JWT authentication.",
    author: {
      name: "Ankit Kumar Singh",
      username: "ankit",
    },
    tags: ["Spring Boot", "JWT", "Security"],
    likes: 24,
    comments: 8,
    createdAt: "2 hours ago",
    trending: true,
  },

  {
    id: 2,
    title: "Understanding React Hooks",
    excerpt:
      "A beginner-friendly guide to useState, useEffect, and custom hooks.",
    author: {
      name: "John Doe",
      username: "johndoe",
    },
    tags: ["React", "JavaScript"],
    likes: 16,
    comments: 5,
    createdAt: "5 hours ago",
    trending: false,
  },

  {
    id: 3,
    title: "Docker Basics for Developers",
    excerpt:
      "Containerize your applications and simplify deployment with Docker.",
    author: {
      name: "Alice",
      username: "alice",
    },
    tags: ["Docker", "DevOps"],
    likes: 42,
    comments: 18,
    createdAt: "Yesterday",
    trending: true,
  },
];

export default blogs;