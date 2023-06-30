import axios from "axios";

const API_URL = "http://localhost:8080";

export const axiosInstance = axios.create({
  baseURL: API_URL,
  timeout: 20000,
  withCredentials: true,
});
