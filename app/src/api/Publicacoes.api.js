import { axiosInstance } from "./_base";

export function PublicacoesAPI() {
  async function getListaPublicacoes() {
    try {
      const publicacoes = await axiosInstance.get(`/publicacoes`);
      return publicacoes.data.content;
    } catch (error) {
      console.log(error);
    }
  }

  async function getDetalhePublicacao({ publicacaoId }) {
    try {
      const publicacoes = await axiosInstance.get(
        `/publicacoes/${publicacaoId}`
      );
      return publicacoes.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function postComentarPublicacao({ publicacaoId, usuarioId, texto }) {
    try {
      const publicacoes = await axiosInstance.post(
        `/publicacoes/${publicacaoId}/comentar/${usuarioId}`,
        {
          texto: texto,
        }
      );
      return publicacoes.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function postCurtirPublicacao({ publicacaoId, usuarioId }) {
    try {
      const publicacoes = await axiosInstance.post(
        `/publicacoes/${publicacaoId}/curtir/${usuarioId}`
      );
      return publicacoes.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function getListaComentariosPublicacao({ publicacaoId }) {
    try {
      const publicacoes = await axiosInstance.get(
        `/publicacoes/${publicacaoId}/comentarios`
      );
      return publicacoes.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function postIncluirPostagem({
    usuarioId,
    titulo,
    texto,
    imagem,
    visibilidade,
  }) {
    try {
      const publicacao = await axiosInstance.post(`/publicacoes/${usuarioId}`, {
        titulo: titulo,
        texto: texto,
        imagem: imagem,
        visibilidade: visibilidade,
      });
      return publicacao;
    } catch (error) {
      console.log(error);
    }
  }

  return {
    getListaPublicacoes,
    getListaComentariosPublicacao,
    postCurtirPublicacao,
    getDetalhePublicacao,
    postIncluirPostagem,
    postComentarPublicacao,
  };
}
