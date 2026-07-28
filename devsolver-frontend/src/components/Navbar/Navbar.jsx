import "./Navbar.css";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        
        {/* Logo */}
        <div className="navbar-logo">
          Dev<span>Solver</span>
        </div>

        {/* Links */}
        <ul className="navbar-links">
          <li>Home</li>
          <li>Explore</li>
          <li>About</li>
        </ul>

        {/* Auth Buttons */}
        <div className="navbar-auth">
          <button className="btn login-btn">Login</button>
          <button className="btn signup-btn">Sign Up</button>
        </div>

      </div>
    </nav>
  );
};

export default Navbar;
