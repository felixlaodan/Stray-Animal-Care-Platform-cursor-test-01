<template>
  <div class="p-4 sm:p-6 lg:p-8 bg-gray-50 min-h-screen">
    <div class="max-w-7xl mx-auto">
      <h1 class="text-3xl font-bold text-gray-800 mb-6 text-center">领养中心</h1>
      
      <div v-if="loading" class="text-center py-10">
        <p>正在加载可爱的家人们...</p>
      </div>
      
      <div v-else-if="animals.length === 0" class="text-center py-10 bg-white rounded-lg shadow">
        <p class="text-gray-500">暂时没有等待领养的动物哦。</p>
      </div>

      <div v-else>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
          <AnimalCard 
            v-for="animal in animals" 
            :key="animal.id" 
            :animal="animal"
            @view-detail="handleViewDetail"
            @adopt="handleAdopt"
          />
        </div>
        <div class="flex justify-center mt-8">
          <el-pagination
            background
            layout="prev, pager, next"
            :total="pagination.total"
            :page-size="pagination.pageSize"
            v-model:current-page="pagination.currentPage"
            @current-change="handlePageChange"
            hide-on-single-page
          />
        </div>
      </div>
    </div>

    <!-- 弹窗组件 -->
    <AnimalDetailModal v-model="detailModal.visible" :animal-id="detailModal.animalId" />
    <AdoptionFormModal v-model="adoptionModal.visible" :animal-to-adopt="adoptionModal.animal" @adoption-success="onAdoptionSuccess" />

  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { getAdoptionInfosPage } from '@/modules/rescue/api';
import { ElMessage } from 'element-plus';
import AnimalCard from '../components/AnimalCard.vue';
import AnimalDetailModal from '../components/AnimalDetailModal.vue';
import AdoptionFormModal from '../components/AdoptionFormModal.vue';
import { useUserStore } from '@/stores/user';
import { useRouter } from 'vue-router';

const userStore = useUserStore();
const router = useRouter();

const loading = ref(true);
const animals = ref([]);
const pagination = reactive({
  currentPage: 1,
  pageSize: 12,
  total: 0,
});

const detailModal = reactive({
  visible: false,
  animalId: null,
});

const adoptionModal = reactive({
  visible: false,
  animal: null,
});

const fetchAdoptionInfos = async () => {
  loading.value = true;
  try {
    const params = { pageNum: pagination.currentPage, pageSize: pagination.pageSize };
    const res = await getAdoptionInfosPage(params);
    if (res.code === 200 && res.data) {
      animals.value = res.data.records;
      pagination.total = parseInt(res.data.total, 10) || 0;
    } else {
      ElMessage.error(res.message || '获取领养信息失败');
    }
  } catch (error) {
    ElMessage.error('网络请求失败');
  } finally {
    loading.value = false;
  }
};

const handlePageChange = (newPage) => {
  pagination.currentPage = newPage;
  fetchAdoptionInfos();
};

const handleViewDetail = (id) => {
  detailModal.animalId = id;
  detailModal.visible = true;
};

const handleAdopt = (animal) => {
  if (!userStore.isAuthenticated) {
    ElMessage.warning('请先登录再进行领养操作');
    router.push('/login');
    return;
  }
  adoptionModal.animal = animal;
  adoptionModal.visible = true;
};

const onAdoptionSuccess = (adoptedAnimalId) => {
  // 刷新当前页数据，或者在前端直接更新状态
  const adoptedAnimal = animals.value.find(a => a.id === adoptedAnimalId);
  if (adoptedAnimal) {
    adoptedAnimal.adoptionStatus = 'ADOPTED';
  }
};

onMounted(() => {
  fetchAdoptionInfos();
});
</script> 