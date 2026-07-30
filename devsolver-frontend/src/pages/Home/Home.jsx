import Navbar from "../../components/layout/Navbar/Navbar";
import Hero from "../../components/home/Hero/Hero";
import Features from "../../components/Home/Features/Features";
import HowItWorks from "../../components/Home/HowItWorks/HowItWorks";
import Stats from "../../components/Home/Stats/Stats";
import CTA from "../../components/Home/CTA/CTA";

const Home = () => {
  return (
    <>
      <Navbar />
      <Hero />
      <Features />
      <HowItWorks />
      <Stats />
      <CTA />
    </>
  );
};

export default Home;