import "./Login.css";
import { Link } from "react-router-dom";

import Button from "../../components/ui/Button/Button";
import Input from "../../components/ui/Input/Input";
import AuthFlow from "./components/AuthFlow/AuthFlow";
import PasswordInput from "../../components/ui/PasswordInput/PasswordInput";

const Login = () => {
  return (
    <section className="login-page">

      <div className="login-card">

        {/* LEFT */}

        <div className="login-left">

          <h1>
            Welcome Back to <span>DevSolver</span>
          </h1>

          <p>
            Continue solving technical problems and collaborate
            with developers around the world.
          </p>

          <form className="login-form">

            <Input
              label="Email"
              type="email"
              placeholder="Enter your email"
            />

            <PasswordInput 
              label="Password"
              placeholder="Enter your password"
            />

            <div className="forgot-password">
              <Link to="#">Forgot Password?</Link>
            </div>

            <Button text="Login" />

          </form>

          <p className="login-footer">
            Don't have an account?
            <Link to="/signup"> Sign Up</Link>
          </p>

        </div>

        {/* RIGHT */}

        <div className="login-right">
          <AuthFlow />
        </div>

      </div>

    </section>
  );
};

export default Login;