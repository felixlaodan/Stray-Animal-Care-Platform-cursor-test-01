<template>
  <el-dialog v-model="visible" title="动物详细信息" width="60%">
    <div v-if="loading" class="text-center">加载中...</div>
    <div v-else-if="error" class="text-center text-red-500">{{ error }}</div>
    <div v-else-if="animalDetail">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="名字">{{ animalDetail.name }}</el-descriptions-item>
        <el-descriptions-item label="物种">{{ animalDetail.species }}</el-descriptions-item>
        <el-descriptions-item label="性别">{{ animalDetail.gender }}</el-descriptions-item>
        <el-descriptions-item label="状态">{{ animalDetail.adoptionStatus === 'AVAILABLE' ? '可领养' : '已领养' }}</el-descriptions-item>
        <el-descriptions-item label="健康状况">{{ animalDetail.healthStatus }}</el-descriptions-item>
        <el-descriptions-item label="绝育状况">{{ animalDetail.sterilizationStatus }}</el-descriptions-item>
        <el-descriptions-item label="补充描述" :span="2">{{ animalDetail.description }}</el-descriptions-item>
      </el-descriptions>
      <h4 class="font-semibold mt-4 mb-2">照片集</h4>
      <div class="flex flex-wrap gap-4">
        <el-image 
          v-for="url in animalDetail.imageUrls" :key="url"
          :src="'http://localhost:8080' + url"
          fit="cover"
          class="w-32 h-32 rounded-lg"
          :preview-src-list="animalDetail.imageUrls.map(u => 'http://localhost:8080' + u)"
          :initial-index="animalDetail.imageUrls.indexOf(url)"
          lazy
        ></el-image>
      </div>
    </div>
    <template #footer>
      <el-button @click="visible = false">关闭</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, watch } from 'vue';
import { getAdoptionInfoDetail } from '@/modules/rescue/api';
import { ElMessage } from 'element-plus';

const props = defineProps({
  animalId: {
    type: Number,
    default: null
  },
  modelValue: {
    type: Boolean,
    required: true
  }
});

const emit = defineEmits(['update:modelValue']);

const visible = ref(props.modelValue);
const loading = ref(false);
const error = ref('');
const animalDetail = ref(null);

watch(() => props.modelValue, (newVal) => {
  visible.value = newVal;
});

watch(visible, (newVal) => {
  emit('update:modelValue', newVal);
  if (newVal && props.animalId) {
    fetchDetail();
  }
});

const fetchDetail = async () => {
  loading.value = true;
  error.value = '';
  animalDetail.value = null;
  try {
    const res = await getAdoptionInfoDetail(props.animalId);
    if (res.code === 200) {
      animalDetail.value = res.data;
    } else {
      error.value = res.message || '获取详情失败';
      ElMessage.error(error.value);
    }
  } catch (err) {
    error.value = '网络请求失败';
    ElMessage.error(error.value);
  } finally {
    loading.value = false;
  }
};
</script> 