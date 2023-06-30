import { axiosInstance } from "./_base";

export function LoginAPI() {
  async function postLogin({ email, senha }) {
    try {
      const response = await axiosInstance.post(
        "/login",
        {},
        {
          auth: {
            username: email,
            password: senha,
          },
        }
      );
      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  async function postLogout() {
    try {
      const response = await axiosInstance.post("/logout", {}, {});

      return response.data;
    } catch (error) {
      console.log(error);
    }
  }

  return {
    postLogin,
    postLogout,
  };
}
