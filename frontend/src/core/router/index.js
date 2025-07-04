import { createRouter, createWebHistory } from 'vue-router'
import forumRoutes from '@/modules/forum/router.js'
import userRoutes from '@/modules/user/router.js'
import rescueRoutes from '@/modules/rescue/router.js'
import HomeView from '@/views/Home.vue'
import { useUserStore } from '@/stores/user.js'
import { ElMessage } from 'element-plus'
import LoginView from '@/modules/user/views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView
    },
    ...forumRoutes,
    ...userRoutes,
    ...rescueRoutes,
    {
      path: '/:pathMatch(.*)*',
      redirect: '/'
    }
  ]
})

// 全局前置导航守卫
router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)

  if (requiresAuth && !userStore.isAuthenticated) {
    ElMessage.warning('该功能需要登录后才能访问')
    next({ name: 'Login' })
  } else {
    next()
  }
})

export default router
