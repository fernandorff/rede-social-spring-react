import useGlobalUser from "../../context/user.context";
import useGlobalUpdate from "../../context/update.context";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "react-bootstrap/Navbar";
import Form from "react-bootstrap/Form";
import Image from "react-bootstrap/Image";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { dateConverter } from "./../../helpers/dateConverter";
import { PublicacoesAPI } from "./../../api/Publicacoes.api";
import { useState, useEffect } from "react";

const formVazio = {
  textoComentario: "",
};

export function PublicacaoContainer({ publicacao }) {
  const [user] = useGlobalUser();
  const [update, toggleUpdate] = useGlobalUpdate();
  const {
    postCurtirPublicacao,
    getListaComentariosPublicacao,
    postComentarPublicacao,
  } = PublicacoesAPI();
  const [comentarios, setComentarios] = useState();
  const [formInput, setFormInput] = useState(formVazio);

  async function curtirPublicacao() {
    await postCurtirPublicacao({
      publicacaoId: publicacao.id,
      usuarioId: user.id,
    });
    toggleUpdate();
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  async function comentarPublicacao(event) {
    event.preventDefault();
    await postComentarPublicacao({
      usuarioId: user.id,
      publicacaoId: publicacao.id,
      texto: formInput.textoComentario,
    });
    setFormInput(formVazio);
    toggleUpdate();
  }

  useEffect(() => {
    async function fetchData() {
      const comentarios = await getListaComentariosPublicacao({
        publicacaoId: publicacao.id,
      });
      setComentarios(comentarios.reverse());
    }
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [update]);

  return (
    <Card
      key={publicacao.id}
      bg="dark"
      text="light"
      className="my-3 px-5 py-3 pb-5"
    >
      <Card.Body className="text-center">
        <a href={`/usuario/${publicacao.usuarioId || publicacao.id}`}>
          <Image
            roundedCircle
            src={publicacao.usuarioImagemPerfil}
            style={{ height: "50px", width: "50px", objectFit: "cover" }}
          />
          <Card.Subtitle className="my-2">
            @{publicacao.usuarioApelido}
          </Card.Subtitle>
        </a>

        <Card.Title>{publicacao.titulo}</Card.Title>

        <Card.Img
          src={publicacao.imagem}
          style={{
            maxHeight: "400px",
            maxWidth: "800px",
            objectFit: "cover",
          }}
          className="my-3"
        />

        <Card.Text>{publicacao.texto}</Card.Text>
        <Card.Text className="text-end fst-italic fs-6">
          {dateConverter(publicacao.dataCriacao)} - {publicacao.visibilidade}
        </Card.Text>
        <Button variant="light" className="m-3" onClick={curtirPublicacao}>
          <i className="material-icons text-danger">favorite</i>
          {publicacao.quantidadeLikes}
        </Button>
        <Button variant="light" className="m-3">
          <i className="material-icons text-success">chat_bubble_outline</i> (
          {publicacao.quantidadeComentarios})
        </Button>
      </Card.Body>

      <Navbar>
        <Form.Control
          className="ms-5 me-3"
          placeholder="Comentar publicação"
          type="text"
          name="textoComentario"
          value={formInput.textoComentario}
          onChange={handleChange}
        />
        <Button
          type="submit"
          variant="light"
          className="me-5"
          onClick={comentarPublicacao}
        >
          Comentar
        </Button>
      </Navbar>

      {comentarios?.map((comentario) => (
        <Card
          key={comentario.id}
          bg="light"
          text="dark"
          className="mx-3 my-2 p-2 bg-opacity-50"
        >
          <Card.Body className="">
            <Row>
              <Col md={2} className="text-center">
                <Image
                  roundedCircle
                  src={comentario.usuarioImagemPerfil}
                  style={{ height: "50px", width: "50px", objectFit: "cover" }}
                />
                <Card.Subtitle className="my-2">
                  @{comentario.usuarioApelido}
                </Card.Subtitle>
              </Col>
              <Col md={8}>
                <Card.Text>{comentario.texto}</Card.Text>
              </Col>
              <Col md={2}>
                <Card.Text className="text-end fst-italic fs-6">
                  {dateConverter(comentario.dataCriacao)}
                </Card.Text>
              </Col>
            </Row>
          </Card.Body>
        </Card>
      ))}
    </Card>
  );
}
