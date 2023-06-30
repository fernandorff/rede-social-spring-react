import useGlobalUser from "../context/user.context";
import useGlobalUpdate from "../context/update.context";
import Container from "react-bootstrap/Container";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.min.css";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { useEffect, useState } from "react";
import { UsuariosAPI } from "../api/Usuarios.api";
import { NavBar } from "./components/navbar.component";
import Image from "react-bootstrap/Image";
import { PublicacaoContainer } from "./components/publicacao-container.component";
import { useParams } from "react-router-dom";

export function UsuarioView() {
  const [user] = useGlobalUser();
  const { getDetalhesUsuario, getPublicacoesDoUsuario } = UsuariosAPI();
  const [update] = useGlobalUpdate();
  const [publicacoes, setPublicacoes] = useState([]);
  const { usuarioIdParam } = useParams();
  const [usuarioVisitado, setUsuarioVisitado] = useState();

  useEffect(() => {
    async function fetchUsuario() {
      const usuario = await getDetalhesUsuario({
        usuarioId: usuarioIdParam,
      });
      setUsuarioVisitado(usuario);
    }
    fetchUsuario();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [update]);

  useEffect(() => {
    async function fetchPublicacoes() {
      const publicacoes = await getPublicacoesDoUsuario({
        visitandeId: user.id,
        visitadoId: usuarioIdParam,
      });
      setPublicacoes(publicacoes);
    }
    fetchPublicacoes();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [update]);

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
                  src={usuarioVisitado?.imagemPerfil}
                  style={{
                    height: "200px",
                    width: "200px",
                    objectFit: "cover",
                  }}
                />
              </Col>
              <Col md={7} className="d-flex flex-column justify-content-around">
                <Card.Title>{usuarioVisitado?.nomeCompleto}</Card.Title>
                <Card.Title>@{usuarioVisitado?.apelido}</Card.Title>
                <Card.Subtitle>Email: {usuarioVisitado?.email}</Card.Subtitle>
                <Card.Subtitle>
                  Data de Nascimento: {usuarioVisitado?.dataNascimento}
                </Card.Subtitle>
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
