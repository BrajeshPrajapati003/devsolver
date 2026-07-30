import { Link } from "react-router-dom";
import "./Logo.css";

const Logo = () => {
  return (
    <Link to="/" className="logo">
      <span className="logo-highlight">&lt;/&gt;</span>
      <span className="logo-text">DevSolver</span>
    </Link>
  );
};

export default Logo;