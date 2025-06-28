import { createRouter, createWebHistory } from 'vue-router'
import PostListView from '../views/PostListView.vue'
import PostDetailView from '../views/PostDetailView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'post-list',
      component: PostListView
    },
    {
      path: '/post/:id',
      name: 'post-detail',
      component: PostDetailView
    }
  ]
})

export default router
