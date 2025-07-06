<template>
  <div class="flex justify-center items-start min-h-screen p-4 bg-img1">
    <div class="w-full max-w-4xl bg-white p-8 rounded-lg shadow-lg">
      <h1 class="text-3xl font-bold mb-8 text-center text-gray-800">
        {{ isEditMode ? '编辑流浪动物信息' : '上报流浪动物信息' }}
      </h1>

      <el-form :model="form" :rules="rules" ref="formRef" label-position="top">

        <!-- 表单项 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="您的姓名" prop="reporter">
              <el-input class="opacity-80" v-model="form.reporter" placeholder="请输入您的姓名"></el-input>
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
                <el-radio label="公" class="lemon-radio"><span>公</span></el-radio>
                <el-radio label="母" class="lemon-radio"><span>母</span></el-radio>
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
            class="opacity-80"
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
          <el-button type="lemon" @click="submitForm" :loading="loading" size="large" class="w-1/2">
            {{ isEditMode ? '确认修改' : '提交上报' }}
          </el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, reactive, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';
import {
  createUploadRecord,
  updateUploadRecord,
  getUploadRecordById,
  adminUpdateUploadRecord,
  adminGetUploadById // 引入Admin API
} from '@/modules/rescue/api.js';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();
const router = useRouter();
const route = useRoute();
const formRef = ref(null);
const loading = ref(false);

const isEditMode = computed(() => !!route.params.id);
const recordId = computed(() => route.params.id ? Number(route.params.id) : null);
const isAdminMode = computed(() => route.path.startsWith('/admin'));

const form = reactive({
  reporter: '',
  reporterPhone: '',
  name: '',
  species: '',
  gender: '',
  healthStatus: '',
  sterilizationStatus: '',
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

const fetchRecordData = async () => {
  if (!isEditMode.value) return;
  loading.value = true;
  try {
    // 关键修改：根据是否为管理员模式，调用不同的API
    const apiToCall = isAdminMode.value ? adminGetUploadById : getUploadRecordById;
    const res = await apiToCall(recordId.value);

    if (res.code === 200 && res.data) {
      // 填充表单
      Object.assign(form, res.data);
      // 关键修复：填充图片列表
      if (res.data.imageUrls && Array.isArray(res.data.imageUrls)) {
        fileList.value = res.data.imageUrls.map(url => ({
          name: url.split('/').pop(),
          url: `http://localhost:8080${url}`,
          status: 'success', // 标记为已成功状态
          response: { data: url } // 模拟上传成功后的响应体，方便删除时获取URL
        }));
      }
    } else {
      ElMessage.error('获取记录详情失败: ' + (res.message || '未知错误'));
      router.push('/rescue/upload-records');
    }
  } catch (error) {
    ElMessage.error('网络错误，无法获取记录详情');
    router.push('/rescue/upload-records');
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  fetchRecordData();
});

const submitForm = async () => {
  if (!formRef.value) return;
  await formRef.value.validate(async (valid) => {
    if (valid) {
      // 从 fileList 提取 URLs
      const uploadedImageUrls = fileList.value
        .map(file => {
          // 对于新上传的文件，URL在response.data中
          // 对于已存在的文件，URL在url中
          if (file.response && file.response.data) {
            return file.response.data;
          }
          // 从完整的url中提取路径
          try {
            const urlObject = new URL(file.url);
            return urlObject.pathname;
          } catch (e) {
            return null; // 无效的URL
          }
        })
        .filter(Boolean);

      if (uploadedImageUrls.length === 0) {
        ElMessage.error('请至少上传一张有效的照片');
        return;
      }

      const finalData = { ...form, imageUrls: uploadedImageUrls };
      loading.value = true;
      try {
        let successMessage = '';

        if (isAdminMode.value) {
          // --- 管理员模式 ---
          if (isEditMode.value) {
            await adminUpdateUploadRecord(recordId.value, finalData);
            successMessage = '管理员修改成功！';
          } else {
            await createUploadRecord(finalData); // 复用创建接口
            successMessage = '管理员新增上报成功！';
          }
        } else {
          // --- 用户模式 ---
          if (isEditMode.value) {
            await updateUploadRecord(recordId.value, finalData);
            successMessage = '修改成功！';
          } else {
            await createUploadRecord(finalData);
            successMessage = '上报成功！感谢您的爱心！';
          }
        }

        ElMessage.success(successMessage);
        // 根据模式跳转到不同的列表页
        router.push(isAdminMode.value ? '/admin/upload-management' : '/rescue/upload-records');

      } catch (error) {
        ElMessage.error('操作失败: ' + error.message);
      } finally {
        loading.value = false;
      }
    }
  });
};
</script>

<style>
.bg-img1 {
  background-image: url('@/assets/images/UploadStray.jpg');
  background-size: cover;
  background-position: center;
}
.el-form {
  margin-top: 20px;
}

.lemon-radio .el-radio__inner {
  border-color: rgb(252, 211, 55);
  background-color: white;
}
.lemon-radio.is-checked .el-radio__inner {
  border-color: rgb(252, 211, 55);
  background-color: rgb(252, 211, 55);
}
.bg-lemon {
  background-color: rgb(252, 211, 55);
}
.bg-lemon:hover {
  background-color: rgb(252, 211, 55, 0.9);
}
.max-w-4xl {
  background-color: rgba(255, 255, 255, 0.7);
}
.el-input__inner, .el-select-dropdown__item, .el-date-editor .el-input__inner {
  background-color: rgba(255, 255, 255, 0.8);
}
.el-form-item, .el-input__inner, .el-select-dropdown__item, .el-date-editor .el-input__inner, .el-radio__label, .el-button {
  font-size: 16px;
  font-weight: bold;
}
</style> 
