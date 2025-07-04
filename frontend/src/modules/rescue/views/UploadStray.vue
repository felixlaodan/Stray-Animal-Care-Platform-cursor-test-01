<template>
  <div class="flex justify-center items-start min-h-screen bg-gray-50 p-4">
    <div class="w-full max-w-4xl bg-white p-8 rounded-lg shadow-lg">
      <h1 class="text-3xl font-bold mb-8 text-center text-gray-800">上报流浪动物信息</h1>
      
      <el-form :model="form" :rules="rules" ref="formRef" label-position="top">
        
        <!-- 表单项 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="您的姓名" prop="reporter">
              <el-input v-model="form.reporter" placeholder="请输入您的姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="您的联系电话" prop="reporterPhone">
              <el-input v-model="form.reporterPhone" placeholder="请输入您的联系电话"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="动物名字" prop="name">
              <el-input v-model="form.name" placeholder="给它起个可爱的名字吧"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物种" prop="species">
              <el-select v-model="form.species" placeholder="请选择物种" class="w-full">
                <el-option label="猫" value="猫"></el-option>
                <el-option label="狗" value="狗"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="form.gender">
                <el-radio label="公">公</el-radio>
                <el-radio label="母">母</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="绝育状态" prop="sterilizationStatus">
              <el-select v-model="form.sterilizationStatus" placeholder="请选择绝育状态" class="w-full">
                <el-option label="未绝育" value="未绝育"></el-option>
                <el-option label="已绝育" value="已绝育"></el-option>
                <el-option label="不详" value="不详"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-form-item label="健康状态" prop="healthStatus">
          <el-input v-model="form.healthStatus" placeholder="例如：活泼、有皮肤病、腿部受伤等"></el-input>
        </el-form-item>

        <el-form-item label="发现时间" prop="discoveryTime">
          <el-date-picker v-model="form.discoveryTime" type="date" placeholder="请选择发现日期" class="w-full" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
        </el-form-item>
        
        <el-form-item label="发现地点" prop="discoveryPlace">
          <el-input v-model="form.discoveryPlace" placeholder="请输入详细的发现地点"></el-input>
        </el-form-item>
        
        <el-form-item label="补充描述" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="4" placeholder="可以描述它的性格、外貌特征等信息"></el-input>
        </el-form-item>

        <!-- 图片上传 -->
        <el-form-item label="上传动物照片 (最多5张)" required>
          <el-upload
            v-model:file-list="fileList"
            action="http://localhost:8080/api/files/upload"
            :headers="uploadHeaders"
            list-type="picture-card"
            multiple
            :limit="5"
            :on-exceed="handleExceed"
            :before-upload="beforeUpload"
            name="file"
            with-credentials
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
        </el-form-item>
        
        <div class="text-center mt-6">
          <el-button type="primary" @click="submitForm" :loading="loading" size="large" class="w-1/2">提交上报</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';
import { createUploadRecord } from '@/modules/rescue/api.js';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();
const router = useRouter();
const formRef = ref(null);
const loading = ref(false);

const form = reactive({
  reporter: '',
  reporterPhone: '',
  name: '',
  species: '猫',
  gender: '公',
  healthStatus: '',
  sterilizationStatus: '不详',
  discoveryTime: '',
  discoveryPlace: '',
  description: '',
});

const fileList = ref([]); // 只用于驱动 <el-upload> 的显示和上传

const uploadHeaders = computed(() => ({
  'Authorization': `Bearer ${userStore.token}`
}));

const rules = {
  reporter: [{ required: true, message: '请输入您的姓名', trigger: 'blur' }],
  reporterPhone: [{ required: true, message: '请输入您的联系电话', trigger: 'blur' }],
  name: [{ required: true, message: '请输入动物名字', trigger: 'blur' }],
  species: [{ required: true, message: '请选择物种', trigger: 'change' }],
  gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
  sterilizationStatus: [{ required: true, message: '请选择绝育状态', trigger: 'change' }],
  discoveryTime: [{ required: true, message: '请选择发现时间', trigger: 'change' }],
  discoveryPlace: [{ required: true, message: '请输入发现地点', trigger: 'blur' }],
};

const handleExceed = () => {
  ElMessage.warning('最多只能上传5张图片');
};

const beforeUpload = (rawFile) => {
  const isJpgOrPng = rawFile.type === 'image/jpeg' || rawFile.type === 'image/png';
  const isLt2M = rawFile.size / 1024 / 1024 < 2;

  if (!isJpgOrPng) {
    ElMessage.error('上传图片只能是 JPG/PNG 格式!');
    return false;
  }
  if (!isLt2M) {
    ElMessage.error('上传图片大小不能超过 2MB!');
    return false;
  }
  return true;
};

const submitForm = async () => {
  if (!formRef.value) return;

  // 1. 先校验表单的基础字段
  const valid = await formRef.value.validate().catch(() => {});
  if (!valid) {
    ElMessage.error('请检查表单，填写所有必填项');
    return;
  }

  // 2. 单独校验图片是否已上传
  const uploadedImageUrls = fileList.value
    .filter(file => file.status === 'success' && file.response?.data)
    .map(file => file.response.data);

  if (uploadedImageUrls.length === 0) {
    ElMessage.error('请至少上传一张有效的照片');
    return;
  }

  // 3. 组合最终数据并提交
  const finalData = {
    ...form,
    imageUrls: uploadedImageUrls
  };

  console.log('最终提交的数据:', JSON.stringify(finalData, null, 2));
  loading.value = true;
  try {
    const res = await createUploadRecord(finalData);
    if (res.code === '200') {
      console.log('后端返回成功, code: 200. ElMessage即将弹出。');
      ElMessage.success('上报成功！感谢您的爱心！');
      
      console.log('准备在1秒后执行跳转。Router实例:', router);
      setTimeout(() => {
        console.log('setTimeout回调触发！准备执行 router.push("/")');
        router.push('/');
        console.log('router.push("/") 已调用。');
      }, 1000);

    } else {
      ElMessage.error(res.message || '上报失败，请稍后重试');
    }
  } catch (error) {
    console.error("Error submitting form:", error);
    ElMessage.error('请求失败：' + (error.message || '请检查网络或联系管理员'));
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.el-form {
  margin-top: 20px;
}
</style> 