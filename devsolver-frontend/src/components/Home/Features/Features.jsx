import "./Features.css";

import {
  FileQuestion,
  MessageCircleCode,
  Tags,
  ShieldCheck,
} from "lucide-react";

const features = [
  {
    icon: <FileQuestion size={42} />,
    title: "Post Problems",
    description:
      "Ask technical questions and share coding challenges with the developer community.",
  },
  {
    icon: <MessageCircleCode size={42} />,
    title: "Community Solutions",
    description:
      "Receive answers from developers and collaborate to solve complex problems.",
  },
  {
    icon: <Tags size={42} />,
    title: "Smart Tags",
    description:
      "Categorize posts using technology tags for quick search and better organization.",
  },
  {
    icon: <ShieldCheck size={42} />,
    title: "Secure Authentication",
    description:
      "Create an account securely and manage your profile with confidence.",
  },
];

const Features = () => {
  return (
    <section className="features">
      <div className="container">

        <div className="section-title">
          <h2>Everything You Need to Grow</h2>

          <p>
            DevSolver provides the tools developers need to collaborate,
            learn, and solve technical challenges together.
          </p>
        </div>

        <div className="feature-grid">
          {features.map((feature) => (
            <div className="feature-card" key={feature.title}>

              <div className="feature-icon">
                {feature.icon}
              </div>

              <h3>{feature.title}</h3>

              <p>{feature.description}</p>

            </div>
          ))}
        </div>

      </div>
    </section>
  );
};

export default Features;