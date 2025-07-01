import axios from 'axios';
import { useUserStore } from '@/stores/user.js'; // 引入 user store
import { ElMessage } from 'element-plus';

// 创建 axios 实例
const request = axios.create({
  // 在这里指定后端服务的基础URL
  baseURL: 'http://localhost:8080',
  timeout: 5000, // 请求超时时间
});

// 请求拦截器
request.interceptors.request.use(
  (config) => {
    // 获取 user store
    const userStore = useUserStore();
    // 如果用户已登录（store 中有 token），则为请求头添加 Authorization
    if (userStore.isAuthenticated) {
      config.headers.Authorization = `Bearer ${userStore.token}`;
    }
    return config;
  },
  (error) => {
    // 对请求错误做些什么
    console.log(error);
    return Promise.reject(error);
  }
);

// 响应拦截器 (可选，但推荐添加以统一处理错误)
request.interceptors.response.use(
  response => {
    const res = response.data;
    // 如果后端返回的不是成功状态码，可以在这里统一处理
    if (res.code !== 200) {
      // 例如，对于 token 过期或无效的情况，可以引导用户重新登录
      if (res.code === 401 || res.code === 403) {
        ElMessage.error('认证失败或已过期，请重新登录');
        // 清除本地登录状态并跳转到登录页
        useUserStore().logout();
        window.location.href = '/login'; 
      }
      return Promise.reject(new Error(res.message || 'Error'));
    } else {
      // 成功响应，直接返回 data 字段
      return res;
    }
  },
  error => {
    ElMessage.error(error.message || '网络请求错误');
    return Promise.reject(error);
  }
);

export default request;