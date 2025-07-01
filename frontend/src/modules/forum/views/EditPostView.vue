<template>
  <div class="edit-post-container">
    <h2>编辑帖子</h2>
    <el-form v-if="form" :model="form" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="form.title" placeholder="请输入帖子标题"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input type="textarea" v-model="form.content" :rows="10" placeholder="分享你的想法..."></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitUpdate" :loading="loading">更新帖子</el-button>
        <el-button @click="cancelEdit">取消</el-button>
      </el-form-item>
    </el-form>
    <div v-else>
      正在加载帖子内容...
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { getPostById, updatePost } from '../api.js';
import { ElMessage } from 'element-plus';

const router = useRouter();
const route = useRoute();
const loading = ref(false);
const postId = route.params.id;

const form = reactive({
  title: '',
  content: '',
});

onMounted(async () => {
  try {
    const res = await getPostById(postId);
    if (res.code === 200) {
      form.title = res.data.title;
      form.content = res.data.content;
    } else {
      ElMessage.error('加载帖子失败: ' + (res.message || '未知错误'));
      router.back();
    }
  } catch (error) {
    console.error('加载帖子失败:', error);
    ElMessage.error('加载帖子失败，请稍后重试');
    router.back();
  }
});

const submitUpdate = async () => {
  if (!form.title || !form.content) {
    ElMessage.warning('标题和内容不能为空');
    return;
  }
  
  loading.value = true;
  try {
    const postData = {
      title: form.title,
      content: form.content,
    };
    
    await updatePost(postId, postData);
    ElMessage.success('帖子更新成功！');
    router.push(`/posts/${postId}`); // 更新成功后跳转回帖子详情页
  } catch (error) {
    console.error('更新帖子失败:', error);
    ElMessage.error('更新帖子失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};

const cancelEdit = () => {
  router.back();
};
</script>

<style scoped>
.edit-post-container {
  max-width: 800px;
  margin: 20px auto;
  padding: 30px;
  border: 1px solid #ebeef5;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.edit-post-container h2 {
  color: #303133;
  text-align: center;
  margin-bottom: 30px;
  font-weight: 600;
}

.edit-post-container :deep(.el-form-item__label) {
  color: #333 !important;
  font-weight: 500;
}

.edit-post-container :deep(.el-input__inner) {
  color: #333 !important;
}

.edit-post-container :deep(.el-textarea__inner) {
  color: #333 !important;
}

.edit-post-container :deep(.el-input__inner::placeholder),
.edit-post-container :deep(.el-textarea__inner::placeholder) {
  color: #999 !important;
}
</style> 