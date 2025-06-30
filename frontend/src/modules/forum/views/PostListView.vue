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
            <router-link to="/create-post">
              <el-button type="primary">发表新帖</el-button>
            </router-link>
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

        <div class="pagination-container" v-if="pagination.total > 0">
          <el-pagination
            layout="prev, pager, next, jumper, ->, total"
            :current-page="pagination.currentPage"
            :page-size="pagination.pageSize"
            :total="pagination.total"
            @current-change="handlePageChange"
          />
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
const pagination = ref({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

const fetchPosts = async () => {
  try {
    const response = await getPosts({
      page: pagination.value.currentPage,
      size: pagination.value.pageSize,
    });
    if (response.data && response.data.code === 200) {
      posts.value = response.data.data.records;
      pagination.value.total = response.data.data.total;
    }
  } catch (error) {
    console.error("获取帖子列表失败:", error);
  }
};

const handlePageChange = (newPage) => {
  pagination.value.currentPage = newPage;
  fetchPosts();
};

onMounted(() => {
  fetchPosts();
});
</script>

<style scoped>
.common-layout {
  width: 100%;
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
}

.box-card {
  max-width: 800px;
  margin: 0 auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
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