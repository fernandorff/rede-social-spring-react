import useGlobalUser from "../context/user.context";
import useGlobalUpdate from "../context/update.context";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.min.css";
import { useEffect, useState } from "react";
import { PublicacoesAPI } from "../api/Publicacoes.api";
import { NavBar } from "./components/navbar.component";
import { PublicacaoContainer } from "./components/publicacao-container.component";

const formVazio = {
  tituloPostagem: "",
  textoPostagem: "",
  imagemPostagem: "",
  visibilidadePostagem: "PUBLICO",
};

export function HomeView() {
  const { getListaPublicacoes, postIncluirPostagem } = PublicacoesAPI();
  const [user] = useGlobalUser();
  const [update, toggleUpdate] = useGlobalUpdate();
  const [formInput, setFormInput] = useState(formVazio);
  const [publicacoes, setPublicacoes] = useState([]);

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  async function submeterPostagem(event) {
    event.preventDefault();
    console.log(formInput);
    await postIncluirPostagem({
      usuarioId: user.id,
      titulo: formInput.tituloPostagem,
      texto: formInput.textoPostagem,
      imagem: formInput.imagemPostagem,
      visibilidade: formInput.visibilidadePostagem,
    });
    setFormInput(formVazio);
    toggleUpdate();
  }

  useEffect(() => {
    async function fetchData() {
      const publicacoes = await getListaPublicacoes();
      setPublicacoes(publicacoes);
      console.log(publicacoes);
    }
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [update]);

  console.log(publicacoes);

  return (
    <>
      <NavBar />
      <Container>
        <Card className="m-3">
          <Card.Body>
            <Card.Title className="text-center">
              Escreva seu sonho aqui
            </Card.Title>
            <Form
              className="d-flex flex-column p-1"
              onSubmit={submeterPostagem}
            >
              <Form.Group className="mb-3">
                <Form.Label>Titulo</Form.Label>
                <Form.Control
                  placeholder="Titulo da postagem"
                  type="text"
                  name="tituloPostagem"
                  value={formInput.tituloPostagem}
                  onChange={handleChange}
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Texto</Form.Label>
                <Form.Control
                  as="textarea"
                  rows={3}
                  placeholder="Texto da postagem"
                  type="text"
                  name="textoPostagem"
                  value={formInput.textoPostagem}
                  onChange={handleChange}
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Imagem</Form.Label>
                <Form.Control
                  placeholder="Link da imagem"
                  type="text"
                  name="imagemPostagem"
                  value={formInput.imagemPostagem}
                  onChange={handleChange}
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Visibilidade</Form.Label>
                <Form.Select
                  name="visibilidadePostagem"
                  value={formInput.visibilidadePostagem}
                  onChange={handleChange}
                >
                  <option value={"PUBLICO"}>Público</option>
                  <option value={"PRIVADO"}>Privado</option>
                </Form.Select>
              </Form.Group>
              <Button type="submit" variant="success">
                Publicar
              </Button>
            </Form>
          </Card.Body>
        </Card>

        <h2 className="text-center text-light my-5">Publicações recentes</h2>

        {publicacoes?.map((publicacao) => (
          <PublicacaoContainer key={publicacao.id} publicacao={publicacao} />
        ))}

        <hr />
      </Container>
    </>
  );
}
