import PostListView from './views/PostListView.vue';
import PostDetailView from './views/PostDetailView.vue';
import CreatePostView from './views/CreatePostView.vue';
import EditPostView from './views/EditPostView.vue';

export default [
  {
    path: '/posts',
    name: 'post-list',
    component: PostListView
  },
  {
    path: '/posts/:id',
    name: 'post-detail',
    component: PostDetailView
  },
  {
    path: '/create-post',
    name: 'create-post',
    component: CreatePostView
  },
  {
    path: '/posts/edit/:id',
    name: 'edit-post',
    component: EditPostView,
  }
]; 