<template>
  <div class="p-4 sm:p-6 lg:p-8 bg-gray-50 min-h-screen">
    <div class="max-w-7xl mx-auto">
      <h1 class="text-3xl font-bold text-gray-800 mb-6">我的领养记录</h1>
      
      <div v-if="loading" class="text-center py-10">
        <p>正在加载您的领养记录...</p>
      </div>
      
      <div v-else-if="records.length === 0" class="text-center py-10 bg-white rounded-lg shadow">
        <p class="text-gray-500">您还没有任何领养记录。</p>
        <el-button type="primary" class="mt-4" @click="$router.push('/rescue/adoption-info')">去领养中心看看</el-button>
      </div>

      <div v-else class="bg-white rounded-lg shadow overflow-hidden">
        <el-table :data="records" stripe style="width: 100%" row-key="id">
          
          <el-table-column label="被领养动物" width="250">
            <template #default="scope">
              <div class="flex items-center">
                <el-image
                  v-if="scope.row.animalInfo && scope.row.animalInfo.coverImageUrl"
                  :src="'http://localhost:8080' + scope.row.animalInfo.coverImageUrl"
                  fit="cover"
                  class="w-16 h-16 rounded-md mr-4"
                ></el-image>
                <div>
                  <div class="font-bold">{{ scope.row.animalInfo ? scope.row.animalInfo.name : 'N/A' }}</div>
                  <div class="text-sm text-gray-500">{{ scope.row.animalInfo ? scope.row.animalInfo.species : 'N/A' }}</div>
                </div>
              </div>
            </template>
          </el-table-column>
          
          <el-table-column prop="adopterName" label="领养人姓名" width="150"></el-table-column>
          <el-table-column prop="adopterPhone" label="联系电话" width="180"></el-table-column>
          <el-table-column prop="adopterIdCard" label="身份证号" width="200"></el-table-column>
          <el-table-column prop="adoptionReason" label="领养理由" show-overflow-tooltip></el-table-column>
          
          <el-table-column prop="createTime" label="申请时间" sortable width="200">
             <template #default="scope">
              {{ new Date(scope.row.createTime).toLocaleString() }}
            </template>
          </el-table-column>

          <el-table-column label="操作" width="100" fixed="right">
            <template #default="scope">
              <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            </template>
          </el-table-column>

        </el-table>
      </div>
    </div>

    <!-- 编辑表单弹窗 -->
    <AdoptionFormModal 
      v-model="editModal.visible" 
      :animal-to-adopt="editModal.recordToEdit.animalInfo"
      :record-to-edit="editModal.recordToEdit"
      @adoption-success="onUpdateSuccess" 
    />
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { getMyAdoptionRecords } from '@/modules/rescue/api';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
import AdoptionFormModal from '../components/AdoptionFormModal.vue';

const router = useRouter();
const loading = ref(true);
const records = ref([]);

const editModal = reactive({
  visible: false,
  recordToEdit: {},
});

const fetchMyRecords = async () => {
  loading.value = true;
  try {
    const res = await getMyAdoptionRecords();
    if (res.code === 200 && res.data) {
      records.value = res.data;
    } else {
      ElMessage.error(res.message || '获取领养记录失败');
    }
  } catch (error) {
    ElMessage.error('网络请求失败');
  } finally {
    loading.value = false;
  }
};

const handleEdit = (record) => {
  editModal.recordToEdit = JSON.parse(JSON.stringify(record)); // 深拷贝以避免响应式问题
  editModal.visible = true;
};

const onUpdateSuccess = () => {
  fetchMyRecords(); // 编辑成功后刷新列表
};

onMounted(() => {
  fetchMyRecords();
});
</script> 