import { LoginAPI } from "../api/Login.api";
import useGlobalUser from "../context/user.context";
import useGlobalUpdate from "../context/update.context";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.min.css";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { useEffect, useState } from "react";
import { UsuariosAPI } from "../api/Usuarios.api";
import { NavBar } from "./components/navbar.component";
import Image from "react-bootstrap/Image";
import { PublicacaoContainer } from "./components/publicacao-container.component";

export function PerfilView() {
  const [user, setUser] = useGlobalUser();
  const formVazio = {
    editarApelido: user.apelido,
    editarImagemPerfil: user.imagemPerfil,
    editarNomeCompleto: user.nomeCompleto,
  };
  const { postLogout } = LoginAPI();
  const { getDetalhesUsuario, getPublicacoesDoUsuario, putEditarUsuario } =
    UsuariosAPI();
  const [update] = useGlobalUpdate();
  const [formInput, setFormInput] = useState(formVazio);
  const [publicacoes, setPublicacoes] = useState([]);
  const [mostrarEdicao, setMostrarEdicao] = useState(false);

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  async function editarUsuario() {
    await putEditarUsuario({
      usuarioId: user.id,
      nomeCompleto: formInput.editarNomeCompleto,
      apelido: formInput.editarApelido,
      imagemPerfil: formInput.editarImagemPerfil,
    });
    const updatingUser = await getDetalhesUsuario({
      usuarioId: user.id,
    });
    setUser(updatingUser);
    setMostrarEdicao(!mostrarEdicao);
  }

  useEffect(() => {
    async function fetchData() {
      const publicacoes = await getPublicacoesDoUsuario({
        visitandeId: user.id,
        visitadoId: user.id,
      });
      setPublicacoes(publicacoes);
    }
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [update]);

  async function handleLogout() {
    await postLogout();
    localStorage.removeItem("user");
    setUser(null);
  }

  return (
    <>
      <NavBar />
      <Container className="pb-5">
        <Card className="m-3 p-5">
          <Card.Body>
            <Row>
              <Col md={3} className="text-center">
                <Image
                  className=""
                  roundedCircle
                  src={user.imagemPerfil}
                  style={{
                    height: "200px",
                    width: "200px",
                    objectFit: "cover",
                  }}
                />
              </Col>
              <Col
                md={7}
                className="d-flex flex-column justify-content-around pe-5"
              >
                {mostrarEdicao ? (
                  <>
                    <Form.Label>Editar Nome</Form.Label>
                    <Form.Control
                      placeholder="Insira novo nome"
                      type="text"
                      name="editarNomeCompleto"
                      value={formInput.editarNomeCompleto}
                      onChange={handleChange}
                    />
                    <Form.Label>Editar Apelido</Form.Label>
                    <Form.Control
                      placeholder="Insira novo apelido"
                      type="text"
                      name="editarApelido"
                      value={formInput.editarApelido}
                      onChange={handleChange}
                    />
                    <Form.Label>Editar Imagem de Perfil</Form.Label>
                    <Form.Control
                      placeholder="Insira novo link da imagem de perfil"
                      type="text"
                      name="editarImagemPerfil"
                      value={formInput.editarImagemPerfil}
                      onChange={handleChange}
                    />
                    <Button
                      onClick={editarUsuario}
                      variant="success"
                      className="my-3 text-center mx-auto"
                    >
                      Confirmar
                    </Button>
                  </>
                ) : (
                  <>
                    <Card.Title>{user.nomeCompleto}</Card.Title>
                    <Card.Subtitle>@{user.apelido}</Card.Subtitle>
                    <Card.Subtitle>Email: {user.email}</Card.Subtitle>
                    <Card.Subtitle>
                      Data de Nascimento: {user.dataNascimento}
                    </Card.Subtitle>
                  </>
                )}
              </Col>
              <Col md={2} className="d-flex flex-column justify-content-around">
                <Button
                  onClick={() => {
                    setMostrarEdicao(!mostrarEdicao);
                    setFormInput(formVazio);
                  }}
                  variant="success"
                  className="my-2"
                >
                  Editar
                </Button>
                <Button
                  onClick={handleLogout}
                  variant="danger"
                  className="my-2"
                >
                  Logout
                </Button>
              </Col>
            </Row>
          </Card.Body>
        </Card>
        <h2 className="text-center text-light my-5">Diário de Sonhos</h2>
        {publicacoes?.map((publicacao) => (
          <PublicacaoContainer key={publicacao.id} publicacao={publicacao} />
        ))}
      </Container>
    </>
  );
}
