import "./Logo.css";

function Logo({ compact = false }) {
  return (
    <div className="logo">
      <div className="logo-box">
        <span className="logo-d">D</span>
        <span className="logo-s">S</span>
      </div>

      {!compact && (
        <div className="logo-text">
          <h2>DevSolver</h2>
          <p>Developer Community</p>
        </div>
      )}
    </div>
  );
}

export default Logo;