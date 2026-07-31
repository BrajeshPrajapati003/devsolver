import "./Signup.css";
import { Link } from "react-router-dom";

import Button from "../../components/ui/Button/Button";
import Input from "../../components/ui/Input/Input";
import PasswordInput from "../../components/ui/PasswordInput/PasswordInput";
import AuthFlow from "../Login/components/AuthFlow/AuthFlow";

const Signup = () => {
  return (
    <section className="signup-page">

      <div className="signup-card">

        {/* Left */}

        <div className="signup-left">

          <h1>
            Join <span>DevSolver</span>
          </h1>

          <p>
            Create your account and become part of a community where
            developers solve problems, share knowledge, and grow together.
          </p>

          <form className="signup-form">

            <Input
              label="Full Name"
              type="text"
              placeholder="Enter your full name"
            />

            <Input
              label="Email"
              type="email"
              placeholder="Enter your email"
            />

            <PasswordInput
              label="Password"
              placeholder="Create a password"
            />

            <PasswordInput
              label="Confirm Password"
              placeholder="Confirm your password"
            />

            <Button text="Create Account" />

          </form>

          <p className="signup-footer">
            Already have an account?
            <Link to="/login"> Login</Link>
          </p>

        </div>

        {/* Right */}

        <div className="signup-right">

          <AuthFlow
            title="Registration Flow"
            steps={[
              "👤 New Developer",
              "📝 Register",
              "☕ Spring Boot API",
              "🛡 Validate User",
              "💾 Save User",
              "✅ Account Created"
            ]}
          />

        </div>

      </div>

    </section>
  );
};

export default Signup;