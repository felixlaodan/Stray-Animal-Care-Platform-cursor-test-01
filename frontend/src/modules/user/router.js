import LoginView from './views/LoginView.vue';
import RegisterView from './views/RegisterView.vue';

const userRoutes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
  },
];

export default userRoutes; 