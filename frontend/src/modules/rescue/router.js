import AdoptionInfo from './views/AdoptionInfo.vue';
import EndStray from './views/EndStray.vue';
import UploadStray from './views/UploadStray.vue';
import UploadRecords from './views/UploadRecords.vue';

const routes = [
  {
    path: '/rescue/adoption-info',
    name: 'AdoptionInfo',
    component: AdoptionInfo,
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
  },
  {
    path: '/rescue/upload-records',
    name: 'UploadRecords',
    component: UploadRecords,
  },
];

export default routes; 