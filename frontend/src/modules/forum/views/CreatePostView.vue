<template>
  <div class="create-post-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <h1>发布新帖子</h1>
        </div>
      </template>
      <el-form :model="postForm" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="postForm.title" placeholder="请输入帖子标题"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="postForm.authorName" placeholder="请输入你的昵称"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input 
            type="textarea" 
            :rows="10" 
            v-model="postForm.content" 
            placeholder="请输入帖子正文"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handlePostSubmit">立即发布</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { createPost } from '../api.js';
import { ElMessage } from 'element-plus';

const router = useRouter();

const postForm = ref({
  title: '',
  content: '',
  authorName: ''
});

const handlePostSubmit = async () => {
  // 简单的前端校验
  if (!postForm.value.title.trim() || !postForm.value.content.trim() || !postForm.value.authorName.trim()) {
    ElMessage.warning('标题、内容和昵称均不能为空！');
    return;
  }

  try {
    const response = await createPost({ ...postForm.value, userId: 1 });
    if (response.data && response.data.code === 200) {
      ElMessage.success('帖子发布成功！');
      // 跳转回论坛首页
      await router.push('/'); 
    } else {
      ElMessage.error(response.data.message || '帖子发布失败');
    }
  } catch (error) {
    ElMessage.error('帖子发布异常');
    console.error("帖子发布异常:", error);
  }
};
</script>

<style scoped>
.create-post-container {
  width: 100%;
  padding: 20px;
  min-height: 100vh;
}

.box-card {
  max-width: 800px;
  margin: 20px auto;
}

.card-header h1 {
  margin: 0;
  font-size: 20px;
  text-align: center;
}
</style> 