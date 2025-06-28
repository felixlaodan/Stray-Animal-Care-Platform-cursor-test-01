<template>
  <el-container class="common-layout">
    <el-header class="header">
      <h1>流浪动物交流论坛</h1>
    </el-header>
    <el-main class="main-content">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>最新帖子</span>
          </div>
        </template>
        <div v-if="posts.length">
          <ul>
            <li v-for="post in posts" :key="post.id" class="post-item">
              <router-link :to="`/post/${post.id}`">{{ post.title }}</router-link>
            </li>
          </ul>
        </div>
        <div v-else>
          <p>正在加载帖子...</p>
        </div>
      </el-card>
    </el-main>
  </el-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { RouterLink } from 'vue-router';
import { getPosts } from '../api.js';

const posts = ref([]);

const fetchPosts = async () => {
  try {
    const response = await getPosts({ page: 1, size: 10 });
    if (response.data && response.data.code === 200) {
      posts.value = response.data.data.records;
    }
  } catch (error) {
    console.error("获取帖子列表失败:", error);
  }
};

onMounted(() => {
  fetchPosts();
});
</script>

<style scoped>
.common-layout {
  min-height: 100vh;
}

.header {
  background-color: #409eff;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
}

.main-content {
  padding: 20px;
  background-color: #f4f4f5;
}

.box-card {
  max-width: 800px;
  margin: 0 auto;
}

.card-header {
  font-weight: bold;
}

.post-item {
  padding: 10px 0;
  border-bottom: 1px solid #ebeef5;
}

.post-item:last-child {
  border-bottom: none;
}

ul {
  list-style-type: none;
  padding: 0;
}

a {
  text-decoration: none;
  color: #303133;
  transition: color 0.3s;
}

a:hover {
  color: #409eff;
}
</style> 