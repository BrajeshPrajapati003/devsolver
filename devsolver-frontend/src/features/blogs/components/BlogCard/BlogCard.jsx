import "./BlogCard.css";

import { Card, Badge, Chip, Avatar } from "../../../../components/ui";

const BlogCard = ({
  title,
  excerpt,
  author,
  tags,
  likes,
  comments,
  createdAt,
  trending,
  onClick,
}) => {
  return (
    <Card className="blog-card" onClick={onClick}>

      {trending && (
        <div className="blog-badge">
          <Badge text="🔥 Trending" />
        </div>
      )}

      <div className="blog-content">

        <h2 className="blog-title">{title}</h2>

        <p className="blog-excerpt">
          {excerpt}
        </p>

        <div className="blog-tags">
          {tags.map((tag) => (
            <Chip
              key={tag}
              label={tag}
            />
          ))}
        </div>

      </div>

      <div className="blog-divider"></div>

      <div className="blog-footer">

        <div className="blog-author">

          <Avatar name={author.name} />

          <div>

            <h4>{author.name}</h4>

            <span>@{author.username}</span>

          </div>

        </div>

        <div className="blog-meta">

          <span>❤️ {likes}</span>

          <span>💬 {comments}</span>

          <span>🕒 {createdAt}</span>

        </div>

      </div>

    </Card>
  );
};

export default BlogCard;