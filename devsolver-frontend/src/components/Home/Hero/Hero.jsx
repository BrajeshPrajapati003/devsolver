import "./Hero.css";
import Button from "../../ui/Button/Button";

const Hero = () => {
  return (
    <section className="hero">
      <div className="container hero-container">

        {/* LEFT */}

        <div className="hero-left">

          <span className="hero-tag">
            🚀 Built For Developers
          </span>

          <h1>
            Solve Technical
            <br />
            Problems Together.
          </h1>

          <p>
            DevSolver is a collaborative platform where developers can
            post programming problems, share solutions, and learn from
            one another in a growing tech community.
          </p>

          <div className="hero-buttons">
            <Button text="Get Started" />
            <Button text="Browse Problems" variant="outline" />
          </div>

        </div>

        {/* RIGHT */}

        <div className="hero-right">

          <div className="editor">

            <div className="editor-header">

              <div className="window-buttons">
                <span className="red"></span>
                <span className="yellow"></span>
                <span className="green"></span>
              </div>

              <div className="editor-title">
                devsolver.js
              </div>

            </div>

            <div className="editor-body">

              <div><span className="line">1</span><span className="keyword">const</span> <span className="variable">devSolver</span> = {"{"}</div>

              <div><span className="line">2</span>&nbsp;&nbsp;<span className="property">platform</span>: <span className="string">"Developer Community"</span>,</div>

              <div><span className="line">3</span>&nbsp;&nbsp;<span className="property">mission</span>: <span className="string">"Solve. Learn. Grow."</span>,</div>

              <div><span className="line">4</span>&nbsp;&nbsp;<span className="property">features</span>: [</div>

              <div><span className="line">5</span>&nbsp;&nbsp;&nbsp;&nbsp;<span className="string">"Post Problems"</span>,</div>

              <div><span className="line">6</span>&nbsp;&nbsp;&nbsp;&nbsp;<span className="string">"Community Solutions"</span>,</div>

              <div><span className="line">7</span>&nbsp;&nbsp;&nbsp;&nbsp;<span className="string">"Tag-Based Search"</span>,</div>

              <div><span className="line">8</span>&nbsp;&nbsp;&nbsp;&nbsp;<span className="string">"Secure Authentication"</span></div>

              <div><span className="line">9</span>&nbsp;&nbsp;],</div>

              <div><span className="line">10</span>&nbsp;&nbsp;<span className="property">status</span>: <span className="boolean">"Ready to Help"</span></div>

              <div><span className="line">11</span>{"}"}</div>

              <br />

              <div><span className="line">12</span><span className="function">console</span>.log(<span className="string">"Welcome to DevSolver 🚀"</span>);</div>

            </div>

          </div>

        </div>

      </div>
    </section>
  );
};

export default Hero;