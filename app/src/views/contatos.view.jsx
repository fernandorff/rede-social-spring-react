import useGlobalUser from "../context/user.context";
import useGlobalUpdate from "../context/update.context";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import { useEffect, useState } from "react";
import { UsuariosAPI } from "../api/Usuarios.api";
import { NavBar } from "./components/navbar.component";
import { AmizadeAPI } from "../api/Amizade.api";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Navbar from "react-bootstrap/Navbar";
import { CardUsuario } from "./components/card-usuario.component";

const formVazio = {
  queryAmigos: "",
  queryUsuarios: "",
};

export function ContatosView() {
  const { getListaDeUsuarios, getProcurarUsuarios } = UsuariosAPI();
  const {
    getListaDeAmigosDoUsuario,
    getListaDeSolicitacoesEnviadas,
    getListaDeSolicitacoesRecebidas,
    postSolicitarAmizade,
    deletetDesfazerAmizade,
    putAceitarSolicitacao,
    getProcurarAmigosDoUsuario,
  } = AmizadeAPI();
  const [user] = useGlobalUser();
  const [update, toggleUpdate] = useGlobalUpdate();
  const [formInput, setFormInput] = useState(formVazio);
  const [amigos, setAmigos] = useState([]);
  const [solicitacoesRecebidas, setSolicitacoesRecebidas] = useState([]);
  const [solicitacoesEnviadas, setSolicitacoesEnviadas] = useState([]);
  const [usuarios, setUsuarios] = useState([]);

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  useEffect(() => {
    async function fetchData() {
      const amigos = await getListaDeAmigosDoUsuario({ usuarioId: user.id });
      setAmigos(amigos);
      const usuarios = await getListaDeUsuarios();
      setUsuarios(usuarios);
      const solicitacoesRecebidas = await getListaDeSolicitacoesRecebidas({
        usuarioId: user.id,
      });
      setSolicitacoesRecebidas(solicitacoesRecebidas);
      const solicitacoesEnviadas = await getListaDeSolicitacoesEnviadas({
        usuarioId: user.id,
      });
      setSolicitacoesEnviadas(solicitacoesEnviadas);
    }
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [update]);

  async function solicitarAmizade(recebendoId) {
    await postSolicitarAmizade({
      solicitandoId: user.id,
      recebendoId: recebendoId,
    });
    toggleUpdate();
  }

  async function desfazerAmizade(amizadeId) {
    await deletetDesfazerAmizade({ amizadeId: amizadeId });
    toggleUpdate();
  }

  async function aceitarSolicitacao(amizadeId) {
    await putAceitarSolicitacao({ amizadeId: amizadeId });
    toggleUpdate();
  }

  async function procurarAmigos(event) {
    event.preventDefault();
    const response = await getProcurarAmigosDoUsuario({
      usuarioId: user.id,
      query: formInput.queryAmigos,
    });
    setAmigos(response);
  }

  async function procurarUsuarios(event) {
    event.preventDefault();
    const response = await getProcurarUsuarios({
      query: formInput.queryUsuarios,
    });
    setUsuarios(response);
  }

  return (
    <>
      <NavBar />
      <Container>
        <Row>
          <Col md={6} className="px-5 pb-5">
            <h2 className="text-center text-light">Solicitacoes Recebidas</h2>
            {solicitacoesRecebidas?.map((usuario) => (
              <CardUsuario objeto={usuario}>
                <Button
                  className="d-flex align-items-center mx-3 ms-auto"
                  variant="success"
                  onClick={() => aceitarSolicitacao(usuario.amizadeId)}
                >
                  <i className="material-icons">check_circle</i>
                </Button>
                <Button
                  className="d-flex align-items-center mx-3"
                  variant="danger"
                  onClick={() => desfazerAmizade(usuario.amizadeId)}
                >
                  <i className="material-icons">cancel</i>
                </Button>
              </CardUsuario>
            ))}
            <hr
              className="my-4 bg-light"
              style={{
                height: "3px",
              }}
            />
            <h2 className="text-center text-light">Solicitacoes Enviadas</h2>
            {solicitacoesEnviadas?.map((usuario) => (
              <CardUsuario objeto={usuario}>
                <Button
                  className="d-flex align-items-center mx-3"
                  variant="danger"
                  onClick={() => desfazerAmizade(usuario.amizadeId)}
                >
                  <i className="material-icons">cancel</i>
                </Button>
              </CardUsuario>
            ))}
            <hr
              className="my-4 bg-light"
              style={{
                height: "3px",
              }}
            />
            <h2 className="text-center text-light">Amigos</h2>
            <Form className="my-3">
              <Navbar>
                <Form.Control
                  placeholder="Procurar amigos"
                  type="text"
                  name="queryAmigos"
                  value={formInput.queryAmigos}
                  onChange={handleChange}
                  className="ms-3"
                />
                <Button
                  variant="light"
                  className="d-flex align-items-center mx-3"
                  onClick={procurarAmigos}
                >
                  <i className="material-icons">search</i>
                </Button>
              </Navbar>
            </Form>
            {amigos?.map((amigo) => (
              <CardUsuario objeto={amigo}>
                <Button
                  className="d-flex align-items-center mx-3"
                  variant="danger"
                  onClick={() => desfazerAmizade(amigo.amizadeId)}
                >
                  <i className="material-icons">cancel</i>
                </Button>
              </CardUsuario>
            ))}
          </Col>
          <Col md={6} className="px-5 pb-5">
            <h2 className="text-center text-light">Usuários da Rede</h2>
            <Navbar>
              <Form.Control
                placeholder="Procurar usuários"
                type="text"
                name="queryUsuarios"
                value={formInput.queryUsuarios}
                onChange={handleChange}
                className="ms-3"
              />
              <Button
                variant="light"
                className="d-flex align-items-center mx-3"
                onClick={procurarUsuarios}
              >
                <i className="material-icons">search</i>
              </Button>
            </Navbar>
            {usuarios
              ?.filter(
                (usuario) =>
                  usuario.id !== user.id &&
                  !solicitacoesRecebidas.some(
                    (solicitacao) => solicitacao.usuarioId === usuario.id
                  ) &&
                  !solicitacoesEnviadas.some(
                    (solicitacao) => solicitacao.usuarioId === usuario.id
                  ) &&
                  !amigos.some((amigo) => amigo.usuarioId === usuario.id)
              )
              .map((usuario) => (
                <CardUsuario objeto={usuario}>
                  <Button
                    className="d-flex align-items-center mx-3"
                    variant="success"
                    onClick={() => solicitarAmizade(usuario.id)}
                  >
                    <i className="material-icons">person_add</i>
                  </Button>
                </CardUsuario>
              ))}
          </Col>
        </Row>
      </Container>
    </>
  );
}
