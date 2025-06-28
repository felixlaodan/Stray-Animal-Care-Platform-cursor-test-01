import PostListView from './views/PostListView.vue';
import PostDetailView from './views/PostDetailView.vue';

export default [
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
]; 