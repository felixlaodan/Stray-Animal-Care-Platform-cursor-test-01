import axios from 'axios';

const apiClient = axios.create({
  // 未来可以在这里设置 baseURL 或超时时间等
});

// 未来实现单点登录时，我们可以在这里添加请求拦截器来附加 Token

export default apiClient; 