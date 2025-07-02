import PostListView from './views/PostListView.vue';
import PostDetailView from './views/PostDetailView.vue';
import CreatePostView from './views/CreatePostView.vue';
import EditPostView from './views/EditPostView.vue';

const routes = [
  {
    path: '/posts',
    name: 'PostList',
    component: PostListView,
  },
  {
    path: '/posts/:id',
    name: 'PostDetail',
    component: PostDetailView,
  },
  {
    path: '/create-post',
    name: 'CreatePost',
    component: CreatePostView,
  },
  {
    path: '/posts/edit/:id',
    name: 'EditPost',
    component: EditPostView,
  },
];

export default routes; 