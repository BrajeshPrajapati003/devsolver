import "./Footer.css";
import { Link } from "react-router-dom";

const Footer = () => {
  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <footer className="footer">
      <div className="container footer-container">

        {/* Brand */}
        <div className="footer-brand">
          <h2>DevSolver</h2>

          <p>
            A collaborative platform where developers solve technical
            problems, share knowledge, and grow together.
          </p>
        </div>

        {/* Quick Links */}
        <div className="footer-links">
          <h3>Quick Links</h3>

          <Link to="/">Home</Link>
          <Link to="/problems">Problems</Link>
          <Link to="/login">Login</Link>
          <Link to="/signup">Sign Up</Link>
        </div>

        {/* Resources */}
        <div className="footer-links">
          <h3>Resources</h3>

          <a href="#">About</a>
          <a href="#">Contact</a>
          <a href="#">Privacy Policy</a>
        </div>

        {/* Connect */}
        <div className="footer-links">
          <h3>Connect</h3>

          <a href="https://github.com" target="_blank" rel="noreferrer">
            GitHub
          </a>

          <a href="#">LinkedIn</a>

          <a href="mailto:example@email.com">
            Email
          </a>
        </div>

      </div>

      <div className="container footer-bottom">

        <p>
          © 2026 DevSolver. All Rights Reserved.
        </p>

        <button
          className="scroll-top"
          onClick={scrollToTop}
        >
          ↑
        </button>

      </div>
    </footer>
  );
};

export default Footer;