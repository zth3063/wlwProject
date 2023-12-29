import axios from "axios"

let config = {
    timeout: 1000,
    withCredentials:true
}

const instance = axios.create(config)

export default instance