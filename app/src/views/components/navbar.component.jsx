import useGlobalUser from "../../context/user.context";
import Container from "react-bootstrap/Container";
import "bootstrap/dist/css/bootstrap.min.css";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Image from "react-bootstrap/Image";
import logo from "../../assets/logo.png";

export function NavBar() {
  const [user] = useGlobalUser();

  return (
    <Navbar bg="dark" variant="dark" className="mb-5">
      <Container>
        <Navbar.Brand href="/home">
          <Image
            src={logo}
            style={{
              height: "40px",
              width: "40px",
              objectFit: "contain",
            }}
          />
          DreamCatcher
        </Navbar.Brand>
        <Nav className="ms-auto d-flex align-items-center">
          <Nav.Link className="mx-3" href="/contatos">
            Contatos
          </Nav.Link>
          <Nav.Link href="/perfil">@{user.apelido}</Nav.Link>
          <Nav.Link href="/perfil">
            <Image
              roundedCircle
              src={user.imagemPerfil}
              style={{
                height: "40px",
                width: "40px",
                objectFit: "cover",
              }}
            />
          </Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}
