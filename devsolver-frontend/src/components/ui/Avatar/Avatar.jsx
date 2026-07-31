import "./Avatar.css";

const Avatar = ({ name }) => {

  const initials = name
    ?.split(" ")
    .map(word => word[0])
    .join("")
    .toUpperCase();

  return (
    <div className="avatar">
      {initials}
    </div>
  );
};

export default Avatar;