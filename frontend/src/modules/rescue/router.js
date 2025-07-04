import EndStray from './views/EndStray.vue';
import UploadStray from './views/UploadStray.vue';
import UploadRecords from './views/UploadRecords.vue';
import AdoptionInfoView from './views/AdoptionInfoView.vue';
import AdoptionRecordView from './views/AdoptionRecordView.vue';

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
  },
  {
    path: '/rescue/upload-stray',
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
];

export default routes; 