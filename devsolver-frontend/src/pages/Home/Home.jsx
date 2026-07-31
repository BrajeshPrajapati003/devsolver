// import Navbar from "../../components/layout/Navbar/Navbar";
import Hero from "../../components/home/Hero/Hero";
import Features from "../../components/Home/Features/Features";
import HowItWorks from "../../components/Home/HowItWorks/HowItWorks";
import Stats from "../../components/Home/Stats/Stats";
import CTA from "../../components/Home/CTA/CTA";
// import Footer from "../../components/layout/Footer/Footer";
import MainLayout from "../../components/layout/MainLayout/MainLayout";

const Home = () => {
  return (
    <>
      <MainLayout>
      <Hero />
      <Features />
      <HowItWorks />
      <Stats />
      <CTA />
      </ MainLayout>
    </>
  );
};

export default Home;