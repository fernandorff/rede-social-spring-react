import { axiosInstance } from "./_base";

export function UsuariosAPI() {
  async function getListaDeUsuarios() {
    try {
      const usuario = await axiosInstance.get(`/usuarios`);
      return usuario.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function getDetalhesUsuario({ usuarioId }) {
    try {
      const usuario = await axiosInstance.get(`/usuarios/${usuarioId}`);
      return usuario.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function getPublicacoesDoUsuario({ visitadoId, visitandeId }) {
    try {
      const usuario = await axiosInstance.get(
        `/usuarios/${visitadoId}/publicacoes/${visitandeId}`
      );
      return usuario.data.content;
    } catch (error) {
      console.log(error);
    }
  }

  async function getProcurarUsuarios({ query }) {
    try {
      const response = await axiosInstance.get(
        `/usuarios/procurar?query=${query}`
      );
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function postIncluirUsuario({
    nomeCompleto,
    apelido,
    email,
    senha,
    dataNascimento,
  }) {
    try {
      const response = await axiosInstance.post("/usuarios", {
        nomeCompleto: nomeCompleto,
        apelido: apelido,
        email: email,
        senha: senha,
        dataNascimento: dataNascimento,
        permissoes: ["ADMIN"],
      });
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function putEditarUsuario({
    nomeCompleto,
    apelido,
    imagemPerfil,
    usuarioId,
  }) {
    try {
      const response = await axiosInstance.put(`/usuarios/${usuarioId}`, {
        nomeCompleto: nomeCompleto,
        apelido: apelido,
        imagemPerfil: imagemPerfil,
      });
    } catch (error) {
      console.log(error);
    }
  }

  return {
    getDetalhesUsuario,
    postIncluirUsuario,
    getPublicacoesDoUsuario,
    getListaDeUsuarios,
    getProcurarUsuarios,
    putEditarUsuario,
  };
}
