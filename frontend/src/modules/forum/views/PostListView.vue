<template>
  <div class="post-list-container">
    <el-card class="page-header-card">
      <div class="page-header-content">
        <h1 class="page-title">流浪动物交流论坛</h1>
        <el-button v-if="userStore.isAuthenticated" type="primary" @click="goToCreatePost">发表新帖</el-button>
      </div>
      <div class="search-bar">
        <el-input
          v-model="searchKeyword"
          placeholder="搜索帖子标题或内容..."
          clearable
          @clear="handleSearch"
          @keyup.enter="handleSearch"
        >
          <template #append>
            <el-button @click="handleSearch">搜索</el-button>
          </template>
        </el-input>
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
            <div v-if="post.imageUrl" class="thumbnail-container">
              <img :src="getFullImageUrl(post.imageUrl)" :alt="post.title" class="thumbnail-image">
            </div>
            <p class="post-preview">{{ truncateContent(post.content) }}</p>
          </div>
        </router-link>

        <div class="post-footer">
          <div class="like-action" @click.stop="handleLike(post)">
            <el-icon>
              <StarFilled v-if="post.likedByCurrentUser" class="is-liked" />
              <Star v-else />
            </el-icon>
            <span>{{ post.likesCount }}</span>
          </div>
        </div>
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
import { getPosts, deletePost, toggleLikePost } from '../api.js';
import { ElMessage, ElMessageBox } from 'element-plus';
import { useUserStore } from '@/stores/user.js';
import { Star, StarFilled } from '@element-plus/icons-vue';

const router = useRouter();
const userStore = useUserStore();
const posts = ref([]);
const searchKeyword = ref('');

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

const getFullImageUrl = (imageUrl) => {
  if (!imageUrl) return '';
  if (imageUrl.startsWith('http')) {
    return imageUrl;
  }
  return 'http://localhost:8080' + imageUrl;
};

const fetchPosts = async () => {
  try {
    const response = await getPosts({
      page: pagination.currentPage,
      size: pagination.pageSize,
      keyword: searchKeyword.value,
    });
    posts.value = response.data.records;
    pagination.total = response.data.total;
  } catch (error) {
    console.error("获取帖子列表失败:", error);
    ElMessage.error("获取帖子列表失败，请稍后重试");
  }
};

const handleSearch = () => {
  pagination.currentPage = 1;
  fetchPosts();
};

const handlePageChange = (newPage) => {
  pagination.currentPage = newPage;
  fetchPosts();
};

const handleLike = async (post) => {
  if (!userStore.isAuthenticated) {
    ElMessage.warning('请先登录再点赞');
    return;
  }
  
  // 乐观更新
  const originalLikedState = post.likedByCurrentUser;
  const originalLikesCount = post.likesCount;

  post.likedByCurrentUser = !post.likedByCurrentUser;
  post.likesCount += post.likedByCurrentUser ? 1 : -1;

  try {
    await toggleLikePost(post.id);
  } catch (error) {
    // 失败时恢复
    post.likedByCurrentUser = originalLikedState;
    post.likesCount = originalLikesCount;
    ElMessage.error('操作失败，请稍后重试');
  }
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
  overflow: visible;
}
.page-header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
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
  display: block;
}
.post-title {
  font-size: 1.4em;
  margin: 0 0 10px 0;
  color: #333;
}
.thumbnail-container {
  margin-bottom: 10px;
}
.thumbnail-image {
  max-width: 100%;
  max-height: 200px;
  object-fit: cover;
  border-radius: 4px;
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
.search-bar {
  width: 100%;
}
.post-footer {
  margin-top: 15px;
  padding-top: 10px;
  border-top: 1px solid #f0f0f0;
  display: flex;
  align-items: center;
  gap: 20px;
}
.like-action {
  display: flex;
  align-items: center;
  gap: 5px;
  cursor: pointer;
  color: #666;
  transition: color 0.3s;
}
.like-action:hover {
  color: #409eff;
}
.like-action .is-liked {
  color: #ff6f61;
}
</style> 