import "./EmptyState.css";

const EmptyState = ({
  title,
  subtitle,
}) => {
  return (
    <div className="empty-state">

      <h2>{title}</h2>

      <p>{subtitle}</p>

    </div>
  );
};

export default EmptyState;