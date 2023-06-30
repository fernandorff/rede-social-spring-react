import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { LoginAPI } from "../api/Login.api";
import useGlobalUser from "../context/user.context";
import { UsuariosAPI } from "./../api/Usuarios.api";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import logo from "../assets/logo.png";
import Image from "react-bootstrap/Image";

const formVazio = {
  email: "",
  senha: "",
  nomeCompletoCadastro: "",
  apelidoCadastro: "",
  emailCadastro: "",
  senhaCadastro: "",
  dataNascimentoCadastro: "",
};

export function LoginView() {
  const { postLogin } = LoginAPI();
  const { postIncluirUsuario } = UsuariosAPI();
  const navigate = useNavigate();
  const [user, setUser] = useGlobalUser();

  const [formInput, setFormInput] = useState(formVazio);
  const [cadastrar, setCadastrar] = useState(false);

  useEffect(() => {
    if (user) {
      navigate("/home");
    }
  }, [user, navigate]);

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  function switchCadastrar() {
    setCadastrar(!cadastrar);
    setFormInput(formVazio);
  }

  async function handleLogin(event) {
    event.preventDefault();
    const user = await postLogin({
      email: formInput.email,
      senha: formInput.senha,
    });
    if (user) {
      setUser(user);
    }
  }

  async function handleIncluirUsuario(event) {
    event.preventDefault();
    await postIncluirUsuario({
      nomeCompleto: formInput.nomeCompletoCadastro,
      email: formInput.emailCadastro,
      senha: formInput.senhaCadastro,
      dataNascimento: formInput.dataNascimentoCadastro,
      apelido: formInput.apelidoCadastro,
    });
    setFormInput(formVazio);
    setCadastrar(!cadastrar);
  }

  return (
    <div className="dreamCatcherBackground">
      <Row>
        <Col md={6}></Col>

        <Col
          md={6}
          className="d-flex align-items-center p-5 bg-dark bg-opacity-75"
          style={{ height: "100vh" }}
        >
          <Container className="d-flex justify-content-center flex-column ">
            {cadastrar ? (
              <Card className="m-5 p-4">
                <Card.Body className="bg-opacity-50">
                  <Card.Title className="text-center">Novo Usuário</Card.Title>

                  <Form
                    onSubmit={handleIncluirUsuario}
                    className="d-flex flex-column p-1"
                  >
                    <Form.Group className="mb-3">
                      <Form.Label>Nome Completo</Form.Label>
                      <Form.Control
                        placeholder="E-mail"
                        type="text"
                        name="nomeCompletoCadastro"
                        value={formInput.nomeCompletoCadastro}
                        onChange={handleChange}
                      />
                    </Form.Group>
                    <Form.Group className="mb-3">
                      <Form.Label>Apelido</Form.Label>
                      <Form.Control
                        placeholder="Apelido"
                        type="text"
                        name="apelidoCadastro"
                        value={formInput.apelidoCadastro}
                        onChange={handleChange}
                      />
                    </Form.Group>
                    <Form.Group className="mb-3">
                      <Form.Label>E-mail</Form.Label>
                      <Form.Control
                        placeholder="Usuário"
                        type="text"
                        name="emailCadastro"
                        value={formInput.emailCadastro}
                        onChange={handleChange}
                      />
                    </Form.Group>
                    <Form.Group className="mb-3">
                      <Form.Label>Senha</Form.Label>
                      <Form.Control
                        placeholder="Usuário"
                        type="text"
                        name="senhaCadastro"
                        value={formInput.senhaCadastro}
                        onChange={handleChange}
                      />
                    </Form.Group>
                    <Form.Group className="mb-3">
                      <Form.Label>Data de Nascimento</Form.Label>
                      <Form.Control
                        type="date"
                        name="dataNascimentoCadastro"
                        value={formInput.dataNascimentoCadastro}
                        onChange={handleChange}
                      />
                    </Form.Group>
                    <div className="d-flex justify-content-between my-3">
                      <Button type="submit" variant="success">
                        Cadastrar
                      </Button>
                      <Button
                        variant="outline-secondary"
                        onClick={switchCadastrar}
                      >
                        {cadastrar ? `Ir para login` : `Ir para cadastro`}
                      </Button>
                    </div>
                  </Form>
                </Card.Body>
              </Card>
            ) : (
              <>
                <Image
                  src={logo}
                  style={{
                    height: "200px",
                    width: "auto",
                    objectFit: "contain",
                  }}
                />
                <h3 className="text-center text-light m-0">Bem-vindo ao</h3>
                <h1 className="text-center text-light">Dream Catcher</h1>
                <Card className="m-5 p-4">
                  <Card.Body>
                    <Form
                      onSubmit={handleLogin}
                      className="d-flex flex-column p-1"
                    >
                      <Form.Group className="mb-3">
                        <Form.Label>E-mail</Form.Label>
                        <Form.Control
                          placeholder="E-mail"
                          type="text"
                          name="email"
                          value={formInput.email}
                          onChange={handleChange}
                        />
                      </Form.Group>
                      <Form.Group className="mb-3">
                        <Form.Label>Senha</Form.Label>
                        <Form.Control
                          placeholder="Usuário"
                          type="text"
                          name="senha"
                          value={formInput.senha}
                          onChange={handleChange}
                        />
                      </Form.Group>
                      <div className="d-flex justify-content-between my-3">
                        <Button type="submit">Entrar</Button>
                        <Button
                          variant="outline-secondary"
                          onClick={switchCadastrar}
                        >
                          {cadastrar ? `Ir para login` : `Cadastre-se agora`}
                        </Button>
                      </div>
                    </Form>
                  </Card.Body>
                </Card>
              </>
            )}
          </Container>
        </Col>
      </Row>
    </div>
  );
}
