import { Link } from "react-router-dom";
import "./Navbar.css";

import Logo from "../../ui/Logo/Logo";
import Button from "../../ui/Button/Button";

const Navbar = () => {
  return (
    <header className="navbar">
      <div className="container navbar-container">

        <Logo />

        <nav className="nav-links">
          <Link to="/">Home</Link>
          <Link to="/problems">Problems</Link>
        </nav>

        <div className="nav-buttons">
          <Link to="/login">
            <Button text="Login" variant="outline" />
          </Link>

          <Link to="/signup">
            <Button text="Sign Up" />
          </Link>
        </div>

      </div>
    </header>
  );
};

export default Navbar;