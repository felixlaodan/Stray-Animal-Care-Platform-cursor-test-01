<template>
  <el-container class="post-detail-container">
    <el-header class="page-header-container">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-large font-600 mr-3"> {{ post.title || '加载中...' }} </span>
        </template>
      </el-page-header>
    </el-header>

    <el-main class="main-content">
      <!-- 帖子内容卡片 -->
      <el-card class="box-card post-card" v-if="post.id">
        <template #header>
          <div class="card-header">
            <span>作者: {{ post.authorName }}</span>
            <span class="time">发布于: {{ formatDateTime(post.createdAt) }}</span>
          </div>
        </template>
        <div class="post-content" v-html="post.content"></div>
      </el-card>

      <!-- 评论区卡片 -->
      <el-card class="box-card comments-card" v-if="post.id">
        <template #header>
          <div class="card-header">
            <span>评论列表</span>
          </div>
        </template>
        <div v-if="post.comments && post.comments.length > 0">
          <div v-for="(comment, index) in post.comments" :key="comment.id" class="comment-item">
            <div class="comment-author">
              <strong>{{ comment.authorName }}</strong>
              <span class="time">{{ formatDateTime(comment.createdAt) }}</span>
            </div>
            <div class="comment-content">{{ comment.content }}</div>
            <el-divider v-if="index < post.comments.length - 1"></el-divider>
          </div>
        </div>
        <div v-else class="no-comments">
          <p>暂无评论</p>
        </div>
      </el-card>

    </el-main>
  </el-container>
</template>

<script setup>
import { ref, onMounted, getCurrentInstance } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const { appContext } = getCurrentInstance();
const $http = appContext.config.globalProperties.$http;

const post = ref({});

const fetchPostDetail = async () => {
  const postId = route.params.id;
  if (!postId) return;

  try {
    const response = await $http.get(`/api/posts/${postId}`);
    if (response.data && response.data.code === 200) {
      post.value = response.data.data;
    } else {
      console.error("获取帖子详情失败:", response.data.message);
    }
  } catch (error) {
    console.error("请求帖子详情异常:", error);
  }
};

// 格式化日期时间
const formatDateTime = (dateTime) => {
  if (!dateTime) return '';
  return new Date(dateTime).toLocaleString();
};

const goBack = () => {
  router.back();
};

onMounted(() => {
  fetchPostDetail();
});
</script>

<style scoped>
.post-detail-container {
  background-color: #f4f4f5;
  min-height: 100vh;
}

.page-header-container {
    background-color: #fff;
    display: flex;
    align-items: center;
    padding: 0 20px;
    border-bottom: 1px solid #dcdfe6;
}

.main-content {
  padding: 20px;
}

.box-card {
  max-width: 800px;
  margin: 0 auto 20px auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  color: #909399;
}

.post-content {
  font-size: 16px;
  line-height: 1.6;
  color: #303133;
}

.comments-card .card-header span {
    font-weight: bold;
    color: #303133;
}

.comment-item {
  margin-bottom: 15px;
}

.comment-author {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    margin-bottom: 8px;
}

.comment-author strong {
    color: #409EFF;
}

.comment-author .time, .card-header .time {
    color: #909399;
}

.comment-content {
    font-size: 15px;
    color: #606266;
}

.no-comments {
    text-align: center;
    color: #909399;
    padding: 20px;
}
</style> 