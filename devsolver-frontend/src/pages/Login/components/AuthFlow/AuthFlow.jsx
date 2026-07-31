import { useEffect, useState } from "react";
import "./AuthFlow.css";

const steps = [
  "👤 User",
  "🔐 Login Request",
  "☕ Spring Boot API",
  "🛡 Spring Security",
  "🎫 JWT Token",
  "✅ Authenticated",
];

const AuthFlow = () => {
  const [active, setActive] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setActive((prev) => (prev + 1) % steps.length);
    }, 900);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="auth-flow">
      <h2>Authentication Flow</h2>

      <div className="flow-container">
        {steps.map((step, index) => (
          <div key={index} className="flow-item">
            <div className={`flow-box ${active === index ? "active" : ""}`}>
              {step}
            </div>

            {index !== steps.length - 1 && (
              <div className={`flow-arrow ${active === index ? "active-arrow" : ""}`}>
                ↓
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default AuthFlow;