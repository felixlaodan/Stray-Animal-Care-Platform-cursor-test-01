import { createRouter, createWebHistory } from 'vue-router'
import forumRoutes from '@/modules/forum/router.js'
import userRoutes from '@/modules/user/router.js'
import { useUserStore } from '@/stores/user.js'
import { ElMessage } from 'element-plus'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/posts'
    },
    ...forumRoutes,
    ...userRoutes,
    // 未来在此处扩展其他模块的路由
  ]
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  
  // 定义需要登录才能访问的路由名称
  const requiresAuth = ['create-post'] // 使用正确的路由名称

  // 检查目标路由是否需要认证
  if (requiresAuth.includes(to.name) && !userStore.isAuthenticated) {
    // 如果需要认证但用户未登录，则重定向到登录页
    ElMessage.warning('请先登录以访问该页面')
    next({ name: 'login', query: { redirect: to.fullPath } }) // 保存用户想去的页面路径，登录后可以跳回
  } else if ((to.name === 'login' || to.name === 'register') && userStore.isAuthenticated) {
    // 如果用户已登录，但试图访问登录或注册页，则将他们重定向到首页
    next({ name: 'post-list' }) // 使用正确的路由名称
  } else {
    // 其他情况，正常放行
    next()
  }
})

export default router
