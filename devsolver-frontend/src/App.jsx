import Button from "./components/ui/Button/Button";

function App() {
  return (
    <div
      className="container"
      style={{
        paddingTop: "80px",
        display: "flex",
        gap: "20px",
      }}
    >
      <Button text="Login" />
      <Button text="Sign Up" variant="outline" />
    </div>
  );
}

export default App;