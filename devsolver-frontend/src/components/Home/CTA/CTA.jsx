import { Link } from "react-router-dom";
import "./CTA.css";

import Button from "../../ui/Button/Button";

const CTA = () => {
  return (
    <section className="cta">

      <div className="container">

        <div className="cta-card">

          <div className="cta-content">

            <span className="cta-tag">
              🚀 Join the Community
            </span>

            <h2>
              Ready to Solve Your
              <br />
              Next Technical Challenge?
            </h2>

            <p>
              Join DevSolver today to ask questions, share knowledge,
              and collaborate with developers from different technologies.
            </p>

            <Link to="/signup">
              <Button text="Create Free Account" />
            </Link>

          </div>

          <div className="cta-glow glow-1"></div>
          <div className="cta-glow glow-2"></div>

        </div>

      </div>

    </section>
  );
};

export default CTA;