import "./Landing.css";

function Landing() {
  return (
    <div className="landing">
      <header className="landing-navbar">
        <div className="logo">
          <span className="logo-highlight">&lt;/&gt;</span>
          <h2>DevSolver</h2>
        </div>

        <div className="nav-buttons">
          <button className="btn btn-secondary">Sign In</button>
          <button className="btn btn-primary">Get Started</button>
        </div>
      </header>

      <main className="hero">
        <div className="hero-content">
          <p className="hero-tag">Developer Community Platform</p>

          <h1>
            Debug.
            <br />
            Share.
            <br />
            Grow.
          </h1>

          <p className="hero-description">
            DevSolver is a platform where developers publish technical blogs,
            share debugging experiences, learn from the community, and grow
            together.
          </p>

          <div className="hero-buttons">
            <button className="btn btn-primary">Create Account</button>
            <button className="btn btn-outline">Explore Blogs</button>
          </div>
        </div>

        <div className="hero-card">
          <div className="code-window">
            <div className="window-header">
              <span className="dot red"></span>
              <span className="dot yellow"></span>
              <span className="dot green"></span>
            </div>

            <pre>
{`function solveBug() {
    const knowledge = shareExperience();
    return knowledge;
}

console.log("Welcome to DevSolver 🚀");`}
            </pre>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Landing;