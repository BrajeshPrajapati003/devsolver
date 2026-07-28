import "./HeroSection.css";

const HeroSection = () => {
  return (
    <section className="hero">
      <div className="hero-container">

        {/* Left Content */}
        <div className="hero-content">
          <h1>
            Solve Problems. <span>Ship Faster.</span>
          </h1>

          <p>
            DevSolver is a community-driven platform where developers
            share real-world problems and get reliable solutions from
            other developers.
          </p>

          <div className="hero-actions">
            <button className="btn primary-btn">Get Started</button>
            <button className="btn secondary-btn">Explore</button>
          </div>
        </div>

        {/* Right Visual */}
        <div className="hero-visual">
          <div className="code-card">
            <pre>
  {`// DevSolver
  const solve = (problem) => {
    return community.help(problem);
  };`}
            </pre>
          </div>
        </div>

      </div>
    </section>
  );
};

export default HeroSection;
