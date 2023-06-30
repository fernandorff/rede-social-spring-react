import { createBrowserRouter } from "react-router-dom";
import { Navigate } from "react-router-dom";
import useGlobalUser from "./context/user.context";
import { LoginView } from "./views/login.view";
import { HomeView } from "./views/home.view";
import { PerfilView } from "./views/perfil.view";
import { ContatosView } from "./views/contatos.view";
import { UsuarioView } from "./views/usuario.view";

function PrivateRoute({ Screen }) {
  const [user] = useGlobalUser();

  if (user) {
    return <Screen />;
  }

  return <Navigate to={"/"} />;
}

export const router = createBrowserRouter([
  {
    path: "/",
    element: <LoginView />,
  },
  {
    path: "/home",
    element: <PrivateRoute Screen={HomeView} />,
  },
  {
    path: "/perfil",
    element: <PrivateRoute Screen={PerfilView} />,
  },
  {
    path: "/contatos",
    element: <PrivateRoute Screen={ContatosView} />,
  },
  {
    path: "/usuario/:usuarioIdParam",
    element: <PrivateRoute Screen={UsuarioView} />,
  },
]);
