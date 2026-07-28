import "./HowItWorks.css";

const steps = [
  {
    step: "01",
    title: "Create an Account",
    description:
      "Sign up as a developer and become part of the DevSolver community."
  },
  {
    step: "02",
    title: "Post Your Problem",
    description:
      "Share the technical issue you're facing with proper tags and details."
  },
  {
    step: "03",
    title: "Get Community Solutions",
    description:
      "Other developers suggest solutions, improvements, and best practices."
  },
  {
    step: "04",
    title: "Learn & Contribute",
    description:
      "Apply solutions, upvote helpful answers, and help others grow."
  }
];

const HowItWorks = () => {
  return (
    <section className="how">
      <div className="how-container">

        <h2 className="how-title">
          How <span>DevSolver</span> Works
        </h2>

        <div className="how-steps">
          {steps.map((item) => (
            <div key={item.step} className="how-card">
              <div className="how-step">{item.step}</div>
              <h3>{item.title}</h3>
              <p>{item.description}</p>
            </div>
          ))}
        </div>

      </div>
    </section>
  );
};

export default HowItWorks;
