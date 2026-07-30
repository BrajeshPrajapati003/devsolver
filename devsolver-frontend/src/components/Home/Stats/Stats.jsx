import "./Stats.css";

const stats = [
  {
    number: "1200+",
    label: "Problems Posted",
  },
  {
    number: "3500+",
    label: "Solutions Shared",
  },
  {
    number: "950+",
    label: "Active Developers",
  },
  {
    number: "25+",
    label: "Technologies",
  },
];

const Stats = () => {
  return (
    <section className="stats">

      <div className="container">

        <div className="section-title">

          <h2>Growing Every Day</h2>

          <p>
            Join developers collaborating, learning, and solving
            programming challenges together.
          </p>

        </div>

        <div className="stats-grid">

          {stats.map((stat) => (
            <div className="stat-card" key={stat.label}>

              <h3>{stat.number}</h3>

              <p>{stat.label}</p>

            </div>
          ))}

        </div>

      </div>

    </section>
  );
};

export default Stats;