import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/Home/Home";
import Login from "./pages/Login/Login";
import Signup from "./pages/Signup/Signup";
import Blogs from "./pages/Blogs/Blogs";
import NotFound from "./pages/NotFound/NotFound";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/blogs" element={<Blogs />} />

        {/* Catch all invalid URLs */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;