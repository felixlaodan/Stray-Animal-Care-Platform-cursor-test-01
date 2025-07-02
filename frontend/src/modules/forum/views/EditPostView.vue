<template>
  <div class="edit-post-container">
    <h2>编辑帖子</h2>
    <el-form v-if="form" :model="form" label-position="top">
      <el-form-item label="标题">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="form.content" type="textarea" :rows="10"></el-input>
      </el-form-item>

      <el-form-item label="上传图片（可选）">
        <el-upload
          class="image-uploader"
          :action="uploadUrl"
          :show-file-list="false"
          :on-success="handleImageSuccess"
          :before-upload="beforeImageUpload"
          :headers="uploadHeaders"
        >
          <img v-if="form.imageUrl" :src="fullImageUrl" class="image" />
          <el-icon v-else class="image-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">更新帖子</el-button>
        <el-button @click="goBack">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { getPostById, updatePost } from '../api.js';
import { ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';
import { useUserStore } from '@/stores/user.js';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const postId = route.params.id;

const form = reactive({
  title: '',
  content: '',
  imageUrl: '',
});

const uploadUrl = 'http://localhost:8080/upload/image';

const uploadHeaders = computed(() => ({
  Authorization: `Bearer ${userStore.token}`,
}));

const fullImageUrl = computed(() => {
  if (!form.imageUrl) return '';
  if (form.imageUrl.startsWith('http')) return form.imageUrl;
  return 'http://localhost:8080' + form.imageUrl;
});

onMounted(async () => {
  try {
    const res = await getPostById(postId);
    if (res.code === 200) {
      form.title = res.data.title;
      form.content = res.data.content;
      form.imageUrl = res.data.imageUrl || '';
    }
  } catch (error) {
    ElMessage.error('加载帖子信息失败');
  }
});

const handleImageSuccess = (response) => {
  if (response.code === 200) {
    form.imageUrl = response.data;
    ElMessage.success('图片上传成功！');
  } else {
    ElMessage.error(response.message || '图片上传失败');
  }
};

const beforeImageUpload = (rawFile) => {
  const isJPGorPNG = rawFile.type === 'image/jpeg' || rawFile.type === 'image/png';
  const isLt2M = rawFile.size / 1024 / 1024 < 2;
  if (!isJPGorPNG) ElMessage.error('上传图片只能是 JPG/PNG 格式!');
  if (!isLt2M) ElMessage.error('上传图片大小不能超过 2MB!');
  return isJPGorPNG && isLt2M;
};

const submitForm = async () => {
  try {
    await updatePost(postId, {
      title: form.title,
      content: form.content,
      imageUrl: form.imageUrl,
    });
    ElMessage.success('帖子更新成功！');
    router.push(`/posts/${postId}`);
  } catch (error) {
    ElMessage.error('更新失败');
  }
};

const goBack = () => {
  router.go(-1);
};
</script>

<style scoped>
.edit-post-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
}

.image-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: .2s;
}
.image-uploader .el-upload:hover {
  border-color: #409eff;
}
.image-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.image {
  width: 178px;
  height: 178px;
  display: block;
}
</style> 