import "./Landing.css";
import Button from "../../components/ui/Button/Button";
import Logo from "../../components/ui/Logo/Logo";

function Landing() {
  return (
    <div className="landing">
      <header className="landing-navbar">
        <Logo />

        <div className="nav-buttons">
          <Button variant="secondary">
             Sign In
          </Button>
          <Button variant="primary">
              Get Started
          </Button>
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
            <Button variant="primary">
              Get Started
            </Button>
            <Button variant="outline">
              Explore Blogs
            </Button>
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