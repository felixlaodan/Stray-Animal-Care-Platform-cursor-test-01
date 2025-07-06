import EndStray from './views/EndStray.vue';
import UploadStray from './views/UploadStray.vue';
import UploadRecords from './views/UploadRecords.vue';
import AdoptionInfoView from './views/AdoptionInfoView.vue';
import AdoptionRecordView from './views/AdoptionRecordView.vue';
import AI from './views/AI.vue';
import Team from '@/views/Team.vue';
import RescueBaseView from './views/RescueBaseView.vue';

const routes = [
  {
    path: '/rescue/adoption-info',
    name: 'AdoptionInfoView',
    component: AdoptionInfoView,
  },
  {
    path: '/rescue/adoption-records',
    name: 'AdoptionRecordView',
    component: AdoptionRecordView,
    meta: { requiresAuth: true }
  },
  {
    path: '/rescue/end-stray',
    name: 'EndStray',
    component: EndStray,
    meta: { requiresAuth: true }
  },
  {
    path: '/rescue/upload-stray/:id?',
    name: 'UploadStray',
    component: UploadStray,
    meta: { requiresAuth: true }
  },
  {
    path: '/rescue/upload-records',
    name: 'UploadRecords',
    component: UploadRecords,
    meta: { requiresAuth: true }
  },
  {
    path: '/rescue/base',
    name: 'RescueBaseView',
    component: RescueBaseView,
  },
  {
    path: '/ai',
    name: 'AI',
    component: AI,
    meta: { requiresAuth: true }
  },
  {
    path: '/team',
    name: 'Team',
    component: Team,
    meta: { requiresAuth: true }
  }
];

export default routes;
