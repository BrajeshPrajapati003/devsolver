import "./HowItWorks.css";

import {
  PencilLine,
  MessagesSquare,
  Rocket,
} from "lucide-react";

const steps = [
  {
    icon: <PencilLine size={42} />,
    title: "Post Your Problem",
    description:
      "Describe your programming issue with relevant details and tags.",
  },
  {
    icon: <MessagesSquare size={42} />,
    title: "Receive Solutions",
    description:
      "Developers collaborate by sharing answers, suggestions and best practices.",
  },
  {
    icon: <Rocket size={42} />,
    title: "Learn & Grow",
    description:
      "Apply the solution, improve your skills and help others in return.",
  },
];

const HowItWorks = () => {
  return (
    <section className="how-it-works">

      <div className="container">

        <div className="section-title">

          <h2>How DevSolver Works</h2>

          <p>
            Solving technical problems is simple with our collaborative community.
          </p>

        </div>

        <div className="timeline">

          {steps.map((step, index) => (

            <div className="timeline-item" key={step.title}>

              <div className="step-circle">

                {step.icon}

              </div>

              <span className="step-number">

                Step {index + 1}

              </span>

              <h3>{step.title}</h3>

              <p>{step.description}</p>

            </div>

          ))}

        </div>

      </div>

    </section>
  );
};

export default HowItWorks;