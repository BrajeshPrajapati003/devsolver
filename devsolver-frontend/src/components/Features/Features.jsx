import FeatureCard from "./FeatureCard";
import "./Features.css";

const Features = () => {
  return (
    <section className="features">
      <div className="features-container">

        <h2 className="features-title">
          Built by Developers, <span>for Developers</span>
        </h2>

        <div className="features-grid">
          <FeatureCard
            icon="🧠"
            title="Ask Real Problems"
            description="Post real-world coding issues you face while building projects."
          />

          <FeatureCard
            icon="🤝"
            title="Community Solutions"
            description="Get answers from developers who have already solved similar problems."
          />

          <FeatureCard
            icon="🏷️"
            title="Tag-Based Discovery"
            description="Find problems and solutions easily using technology-specific tags."
          />

          <FeatureCard
            icon="🚀"
            title="Grow Together"
            description="Learn, contribute, and build your developer profile."
          />
        </div>

      </div>
    </section>
  );
};

export default Features;
