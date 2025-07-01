<template>
  <div class="create-post-container">
    <h2>发表新帖子</h2>
    <el-form :model="form" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="form.title" placeholder="请输入帖子标题"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input type="textarea" v-model="form.content" :rows="5" placeholder="分享你的想法..."></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitPost" :loading="loading">立即发布</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user.js';
import { createPost } from '../api.js';
import { ElMessage } from 'element-plus';

const router = useRouter();
const userStore = useUserStore();
const loading = ref(false);

const form = reactive({
  title: '',
  content: '',
});

const submitPost = async () => {
  if (!form.title || !form.content) {
    ElMessage.warning('标题和内容不能为空');
    return;
  }

  // 检查用户是否登录
  if (!userStore.isAuthenticated) {
    ElMessage.error('请先登录再发布帖子');
    router.push('/login');
    return;
  }
  
  loading.value = true;

  try {
    const postData = {
      title: form.title,
      content: form.content,
      // userId 和 authorName 将由后端根据 token 自动填充，前端无需提交
    };
    
    const res = await createPost(postData);

    if (res.code === 200) {
      ElMessage.success('帖子发布成功！');
      // 发布成功后跳转到帖子列表页
      router.push('/posts');
    } else {
      ElMessage.error(res.message || '发布失败');
    }
  } catch (error) {
    console.error('发布帖子失败:', error);
    ElMessage.error('发布帖子失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.create-post-container {
  max-width: 800px;
  margin: 20px auto;
  padding: 30px;
  border: 1px solid #ebeef5;
  border-radius: 8px;
  background-color: #fff; /* 添加白色背景以突出表单 */
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); /* 添加细微阴影以增加立体感 */
}

.create-post-container h2 {
  color: #303133;
  text-align: center;
  margin-bottom: 30px;
  font-weight: 600;
}

/* 覆盖 Element Plus 的默认样式，确保文字清晰可见 */
.create-post-container :deep(.el-form-item__label) {
  color: #333 !important;
  font-weight: 500;
}

.create-post-container :deep(.el-input__inner) {
  color: #333 !important;
}

.create-post-container :deep(.el-textarea__inner) {
  color: #333 !important;
}

.create-post-container :deep(.el-input__inner::placeholder),
.create-post-container :deep(.el-textarea__inner::placeholder) {
  color: #999 !important;
}
</style> 