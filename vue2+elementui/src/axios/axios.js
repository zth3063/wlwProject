import axios from "axios"

let config = {
    baseURL: "http://localhost:9201",
    timeout: 1000,
    withCredentials:true
}

const instance = axios.create(config)

export default instance