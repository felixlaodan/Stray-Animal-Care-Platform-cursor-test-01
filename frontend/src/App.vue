<script setup>
import { RouterLink, RouterView, useRouter } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { useUserStore } from '@/stores/user.js'
import { ElMessage } from 'element-plus'

const userStore = useUserStore()
const router = useRouter()

const handleLogout = () => {
  // 调用 store 中的注销 action
  userStore.logout()
  ElMessage.success('您已成功注销')
  // 注销后跳转到登录页
  router.push('/login')
}
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="城市流浪动物信息救助平台" />
      <nav>
        <!-- 基础导航 -->
        <RouterLink to="/posts">首页</RouterLink>
        <RouterLink to="/create-post">发布帖子</RouterLink>
        
        <!-- 用户状态导航 -->
        <div v-if="userStore.isAuthenticated" class="user-info">
          <span>欢迎, {{ userStore.user?.nickname || '用户' }}</span>
          <el-button type="primary" link @click="handleLogout">注销</el-button>
        </div>
        <div v-else>
          <RouterLink to="/login">登录</RouterLink>
          <RouterLink to="/register">注册</RouterLink>
        </div>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

/* 导航链接基础样式 - 使用深色文字提高可读性 */
nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
  color: #333 !important;
  text-decoration: none;
  font-weight: 500;
}

nav a:hover {
  color: #409eff !important;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #409eff !important;
  font-weight: 600;
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

.user-info span {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
  color: #333 !important;
  font-weight: 500;
}

nav a:first-of-type {
  border: 0;
}

.user-info {
  display: inline-block;
}

/* Element Plus 按钮样式覆盖 */
.user-info :deep(.el-button--primary.is-link) {
  color: #409eff !important;
  font-weight: 500;
  padding: 0;
}

.user-info :deep(.el-button--primary.is-link:hover) {
  color: #66b1ff !important;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;
    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
