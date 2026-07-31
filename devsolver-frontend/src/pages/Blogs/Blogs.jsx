import "./Blogs.css";

import MainLayout from "../../components/layout/MainLayout/MainLayout";

import blogs from "../../data/blogs";

import BlogCard from "../../features/blogs/components/BlogCard/BlogCard";

const Blogs = () => {
  return (
    <MainLayout>

      <section className="blogs-page">

        <div className="container">

          <div className="blogs-header">

            <h1>Community Blogs</h1>

            <p>
              Discover articles shared by developers around the world.
            </p>

          </div>

          <div className="blogs-list">

            {blogs.map((blog) => (

              <BlogCard
                key={blog.id}
                {...blog}
              />

            ))}

          </div>

        </div>

      </section>

    </MainLayout>
  );
};

export default Blogs;