import AdminLayout from '@/admin/AdminLayout.vue';
import UploadManagement from '@/admin/views/UploadManagement.vue'; 
import UserManagement from '@/admin/views/UserManagement.vue';
import UploadStray from '@/modules/rescue/views/UploadStray.vue'; // 复用用户端的组件

const adminRoutes = [
  {
    path: '/admin',
    component: AdminLayout,
    redirect: '/admin/upload-management',
    children: [
      {
        path: 'upload-management',
        name: 'UploadManagement',
        component: UploadManagement,
        meta: { title: '流浪动物管理' }
      },
      {
        path: 'user-management',
        name: 'UserManagement',
        component: UserManagement,
        meta: { title: '用户管理' }
      },
      {
        path: 'upload-stray/edit/:id',
        name: 'AdminEditUpload',
        component: UploadStray, // 复用
        meta: { title: '编辑上报记录' }
      },
      {
        path: 'upload-stray/new',
        name: 'AdminNewUpload',
        component: UploadStray, // 复用
        meta: { title: '新增上报记录' }
      }
    ]
  }
];

export default adminRoutes;