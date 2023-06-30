import React from "react";
import { RouterProvider } from "react-router-dom";
import { router } from "./routes";
import { GlobalUserProvider } from "./context/user.context";
import { GlobalUpdateProvider } from "./context/update.context";
import "./app.css";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <GlobalUpdateProvider>
      <GlobalUserProvider>
        <RouterProvider router={router} />
      </GlobalUserProvider>
    </GlobalUpdateProvider>
  );
}
export default App;
