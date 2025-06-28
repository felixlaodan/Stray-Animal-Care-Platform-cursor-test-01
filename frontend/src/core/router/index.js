import { createRouter, createWebHistory } from 'vue-router'
import forumRoutes from '@/modules/forum/router.js'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...forumRoutes,
    // 未来在此处扩展其他模块的路由
  ]
})

export default router
