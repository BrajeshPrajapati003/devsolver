import Navbar from "../../components/layout/Navbar/Navbar";
import Hero from "../../components/home/Hero/Hero";
import Features from "../../components/Home/Features/Features";
import HowItWorks from "../../components/Home/HowItWorks/HowItWorks";

const Home = () => {
  return (
    <>
      <Navbar />
      <Hero />
      <Features />
      <HowItWorks />
    </>
  );
};

export default Home;