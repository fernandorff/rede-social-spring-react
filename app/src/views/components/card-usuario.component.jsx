import Container from "react-bootstrap/Container";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "react-bootstrap/Navbar";
import Image from "react-bootstrap/Image";

export function CardUsuario({ objeto, children }) {
  return (
    <Navbar bg="dark" variant="dark" className="m-3 shadow-sm">
      <Container>
        <Navbar.Brand>
          <a href={`/usuario/${objeto.usuarioId || objeto.id}`}>
            <Image
              className="mx-3"
              roundedCircle
              src={objeto.imagemPerfil || objeto.usuarioImagemPerfil}
              style={{
                height: "50px",
                width: "50px",
                objectFit: "cover",
              }}
            />
            @{objeto.apelido || objeto.usuarioApelido}
          </a>
        </Navbar.Brand>

        {children}
      </Container>
    </Navbar>
  );
}
