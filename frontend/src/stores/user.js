import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useUserStore = defineStore('user', () => {
  // === State ===
  // 从 localStorage 初始化 state，以实现登录状态的持久化
  const token = ref(localStorage.getItem('token') || '');
  const user = ref(JSON.parse(localStorage.getItem('user')) || null);

  // === Getters ===
  // 一个计算属性，用于方便地判断用户是否已登录
  const isAuthenticated = computed(() => !!token.value);

  const isAdmin = computed(() => user.value?.role === 'ADMIN');

  // === Actions ===

  /**
   * 登录成功时调用，保存 token 和用户信息
   * @param {object} loginData - 包含 token 和 user 信息的对象
   */
  function loginSuccess({ newToken, newUser }) {
    token.value = newToken;
    user.value = newUser;
    localStorage.setItem('token', newToken);
    localStorage.setItem('user', JSON.stringify(newUser));
  }

  /**
   * 用户登出时调用，清空所有用户状态
   */
  function logout() {
    token.value = '';
    user.value = null;
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  return { 
    token, 
    user, 
    isAuthenticated,
    isAdmin,
    loginSuccess, 
    logout 
  };
}); 