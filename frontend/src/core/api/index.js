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
    // 任何2xx范围内的状态码都会触发该函数
    const res = response.data;

    // 健壮性修复：
    // 1. 如果响应体没有 code 字段 (例如直接返回文件流或普通JSON)，直接视为成功
    // 2. 如果有 code 字段，则严格按照业务约定判断
    if (res && res.code !== undefined && res.code != 200) {
      ElMessage.error(res.message || '业务错误，请联系管理员');

      if (res.code === 401 || res.code === 403) {
        useUserStore().logout();
        window.location.href = '/login';
      }
      
      return Promise.reject(new Error(res.message || 'Error'));
    }
    
    // 对于所有其他情况（包括 code === 200 或没有 code 字段），都返回响应体
    return res;
  },
  error => {
    // 超出 2xx 范围的状态码都会触发该函数
    console.error('网络请求错误:', error);
    ElMessage.error(error.message || '网络错误，请检查您的连接');
    return Promise.reject(error);
  }
);

export default request;