import { useState } from "react";
import "./PasswordInput.css";

const PasswordInput = ({
  label,
  placeholder,
  name,
  value,
  onChange,
}) => {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <div className="password-group">

      <label>{label}</label>

      <div className="password-wrapper">

        <input
          type={showPassword ? "text" : "password"}
          name={name}
          value={value}
          placeholder={placeholder}
          onChange={onChange}
        />

        <button
          type="button"
          className="toggle-password"
          onClick={() => setShowPassword(!showPassword)}
        >
          {showPassword ? "Hide" : "Show"}
        </button>

      </div>

    </div>
  );
};

export default PasswordInput;