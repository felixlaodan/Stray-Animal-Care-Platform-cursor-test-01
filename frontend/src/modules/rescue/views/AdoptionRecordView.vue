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
        <el-table :data="records" stripe style="width: 100%">
          <el-table-column prop="adopterName" label="领养人姓名" width="150"></el-table-column>
          <el-table-column prop="adopterPhone" label="联系电话" width="180"></el-table-column>
          <el-table-column prop="adopterIdCard" label="身份证号" width="200"></el-table-column>
          <el-table-column prop="adoptionReason" label="领养理由"></el-table-column>
          <el-table-column prop="createTime" label="申请时间" sortable width="180">
             <template #default="scope">
              {{ new Date(scope.row.createTime).toLocaleString() }}
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getMyAdoptionRecords } from '@/modules/rescue/api';
import { ElMessage } from 'element-plus';

const loading = ref(true);
const records = ref([]);

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

onMounted(() => {
  fetchMyRecords();
});
</script> 