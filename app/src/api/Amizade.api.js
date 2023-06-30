import { axiosInstance } from "./_base";

export function AmizadeAPI() {
  async function getListaDeAmigosDoUsuario({ usuarioId }) {
    try {
      const response = await axiosInstance.get(`/amigos/${usuarioId}`);
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function getProcurarAmigosDoUsuario({ usuarioId, query }) {
    try {
      const response = await axiosInstance.get(
        `/amigos/${usuarioId}/procurar?query=${query}`
      );
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function getListaDeSolicitacoesRecebidas({ usuarioId }) {
    try {
      const response = await axiosInstance.get(
        `/amigos/${usuarioId}/recebidas`
      );
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function getListaDeSolicitacoesEnviadas({ usuarioId }) {
    try {
      const response = await axiosInstance.get(`/amigos/${usuarioId}/enviadas`);
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function postSolicitarAmizade({ solicitandoId, recebendoId }) {
    try {
      const response = await axiosInstance.post(
        `/amigos/${solicitandoId}/solicitar/${recebendoId}`
      );
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function deletetDesfazerAmizade({ amizadeId }) {
    try {
      const response = await axiosInstance.delete(
        `/amigos/${amizadeId}/desfazer`
      );
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function putAceitarSolicitacao({ amizadeId }) {
    try {
      const response = await axiosInstance.put(`/amigos/${amizadeId}/aceitar`);
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  return {
    getListaDeAmigosDoUsuario,
    getListaDeSolicitacoesRecebidas,
    getListaDeSolicitacoesEnviadas,
    postSolicitarAmizade,
    deletetDesfazerAmizade,
    putAceitarSolicitacao,
    getProcurarAmigosDoUsuario,
  };
}
