import { useEffect, useState } from "react";
import "./AuthFlow.css";

const AuthFlow = ({ title = "Authentication Flow", steps = [] }) => {
  const [active, setActive] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setActive((prev) => (prev + 1) % steps.length);
    }, 900);

    return () => clearInterval(interval);
  }, [steps.length]);

  return (
    <div className="auth-flow">

      <h2>{title}</h2>

      <div className="flow-container">

        {steps.map((step, index) => (

          <div key={index} className="flow-item">

            <div
              className={`flow-box ${
                active === index ? "active" : ""
              }`}
            >
              {step}
            </div>

            {index !== steps.length - 1 && (
              <div
                className={`flow-arrow ${
                  active === index
                    ? "active-arrow"
                    : ""
                }`}
              >
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