<template>
  <div class="post-list-container">
    <el-card class="page-header-card">
      <div class="page-header-content">
        <h1 class="page-title">流浪动物交流论坛</h1>
        <el-button type="primary" @click="goToCreatePost">发表新帖</el-button>
      </div>
    </el-card>

    <div class="post-list">
      <el-card v-for="post in posts" :key="post.id" class="post-card" shadow="hover">
        <template #header>
          <div class="card-header">
            <span>{{ post.authorName }}</span>
            <div class="post-actions" v-if="userStore.isAuthenticated && userStore.user?.id === post.userId">
              <el-button type="primary" link @click="handleEdit(post.id)">编辑</el-button>
              <el-button type="danger" link @click="handleDelete(post.id)">删除</el-button>
            </div>
          </div>
        </template>
        <router-link :to="`/posts/${post.id}`" class="post-link">
          <div class="post-content">
            <h3 class="post-title">{{ post.title }}</h3>
            <p class="post-preview">{{ truncateContent(post.content) }}</p>
          </div>
        </router-link>
      </el-card>
      
      <el-empty v-if="!posts.length" description="暂无帖子"></el-empty>
    </div>

    <div v-if="pagination.total > 0" class="pagination-container">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="pagination.total"
        :page-size="pagination.pageSize"
        v-model:current-page="pagination.currentPage"
        @current-change="handlePageChange"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { getPosts, deletePost } from '../api.js';
import { ElMessage, ElMessageBox } from 'element-plus';
import { useUserStore } from '@/stores/user.js';

const router = useRouter();
const userStore = useUserStore();
const posts = ref([]);

const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

const truncateContent = (content, length = 120) => {
  if (!content) return '';
  const strippedContent = content.replace(/<[^>]*>?/gm, ''); // 移除HTML标签
  if (strippedContent.length <= length) {
    return strippedContent;
  }
  return strippedContent.substring(0, length) + '...';
};

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

const goToCreatePost = () => {
  router.push('/create-post');
};

const handleEdit = (postId) => {
  router.push(`/posts/edit/${postId}`);
};

const handleDelete = async (postId) => {
  try {
    await ElMessageBox.confirm('确定要删除这篇帖子吗？此操作不可撤销。', '警告', {
      confirmButtonText: '确定删除',
      cancelButtonText: '取消',
      type: 'warning',
    });
    await deletePost(postId);
    ElMessage.success('帖子删除成功！');
    posts.value = posts.value.filter(p => p.id !== postId);
    if (posts.value.length === 0 && pagination.currentPage > 1) {
      pagination.currentPage -= 1;
    }
    fetchPosts();
  } catch (error) {
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
.post-list-container {
  max-width: 800px;
  margin: 0 auto;
}
.page-header-card {
  margin-bottom: 20px;
  background-color: #409eff;
  color: white;
}
.page-header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.page-title {
  margin: 0;
  font-size: 1.8em;
}
.post-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.post-card {
  transition: all 0.3s ease;
}
.post-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 20px rgba(0,0,0,0.1);
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #666;
  font-size: 0.9em;
}
.post-link {
  text-decoration: none;
  color: inherit;
}
.post-title {
  font-size: 1.4em;
  margin: 0 0 10px 0;
  color: #333;
}
.post-preview {
  font-size: 1em;
  color: #555;
  line-height: 1.6;
}
.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style> 