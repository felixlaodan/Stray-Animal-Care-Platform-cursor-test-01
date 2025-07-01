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
        <div class="post-list">
          <div v-if="posts.length">
            <ul>
              <li v-for="post in posts" :key="post.id" class="post-item">
                <div class="post-title-wrapper">
                  <router-link :to="`/posts/${post.id}`">{{ post.title }}</router-link>
                  <span class="post-author">by {{ post.authorName }}</span>
                </div>
                <div class="post-actions" v-if="userStore.isAuthenticated && userStore.user.id === post.userId">
                  <el-button type="primary" link @click="handleEdit(post.id)">编辑</el-button>
                  <el-button type="danger" link @click="handleDelete(post.id)">删除</el-button>
                </div>
              </li>
            </ul>
          </div>
          <el-empty v-else description="暂无帖子"></el-empty>
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
import { ref, onMounted, reactive } from 'vue';
import { RouterLink, useRouter } from 'vue-router';
import { getPosts, deletePost } from '../api.js';
import { ElMessage, ElMessageBox } from 'element-plus';
import { useUserStore } from '@/stores/user.js';

const posts = ref([]);
const userStore = useUserStore();
const router = useRouter();
const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

const fetchPosts = async () => {
  try {
    const response = await getPosts({
      page: pagination.currentPage,
      size: pagination.pageSize,
    });
    
    posts.value = response.data.records;
    pagination.total = response.data.total;

  } catch (error) {
    console.error("获取帖子列表失败:", error);
    ElMessage.error("获取帖子列表失败，请稍后重试");
  }
};

const handlePageChange = (newPage) => {
  pagination.currentPage = newPage;
  fetchPosts();
};

const handleEdit = (postId) => {
  router.push(`/posts/edit/${postId}`);
};

const handleDelete = async (postId) => {
  try {
    await ElMessageBox.confirm(
      '确定要删除这篇帖子吗？此操作不可撤销。',
      '警告',
      {
        confirmButtonText: '确定删除',
        cancelButtonText: '取消',
        type: 'warning',
      }
    );
    
    await deletePost(postId);
    ElMessage.success('帖子删除成功！');
    // 从列表中移除被删除的帖子，实现页面的实时更新
    posts.value = posts.value.filter(p => p.id !== postId);

  } catch (error) {
    // 如果用户点击了"取消"，或者API调用失败
    if (error !== 'cancel') {
      console.error('删除帖子失败:', error);
      ElMessage.error('删除帖子失败');
    } else {
      ElMessage.info('已取消删除');
    }
  }
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

.post-list {
  list-style-type: none;
  padding: 0;
}

.post-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 10px;
  border-bottom: 1px solid #ebeef5;
}

.post-item:last-child {
  border-bottom: none;
}

.post-title-wrapper {
  display: flex;
  flex-direction: column;
}

.post-author {
  font-size: 0.8em;
  color: #909399;
  margin-top: 5px;
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