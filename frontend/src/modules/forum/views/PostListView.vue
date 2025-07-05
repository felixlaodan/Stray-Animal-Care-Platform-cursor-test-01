<template>
  <div class="post-list-container img-bg1">
    <el-card class="page-header-card max-w-[90%] mx-auto">
      <div class="page-header-content">
        <h1 class="page-title">流浪动物交流论坛</h1>
        <el-button v-if="userStore.isAuthenticated" type="success" @click="goToCreatePost" data-type="custom">发表新帖</el-button>
      </div>
      <div class="action-bar">
        <div class="sort-options">
          <el-radio-group v-model="sortBy" size="large">
            <el-radio-button  value="latest">最新发布</el-radio-button>
            <el-radio-button  value="popular">热度最高</el-radio-button>
          </el-radio-group>
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
              <el-button type="white" @click="handleSearch" data-type="custom">搜索</el-button>
            </template>
          </el-input>
        </div>
      </div>
    </el-card>

    <div class="post-list max-w-[90%] mx-auto grid md:grid-cols-2 gap-4">
      <el-card v-for="post in posts" :key="post.id" class="post-card" shadow="hover">
        <template #header>
          <div class="card-header">
            <span>{{ post.authorName }}</span>
            <div class="post-actions" v-if="userStore.isAuthenticated && (userStore.user?.id === post.userId || userStore.isAdmin)">
              <el-button type="white" link @click="handleEdit(post.id)" data-type="custom">编辑</el-button>
              <el-button type="warning" link @click="handleDelete(post.id)" data-type="custom">删除</el-button>
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
          <LikeButton
            :liked="post.likedByCurrentUser"
            :likes-count="post.likesCount"
            @toggle-like="handleLike(post)"
          />
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
import { ref, onMounted, reactive, watch } from 'vue';
import { useRouter } from 'vue-router';
import { getPosts, deletePost, toggleLikePost } from '../api.js';
import { ElMessage, ElMessageBox } from 'element-plus';
import { useUserStore } from '@/stores/user.js';
import LikeButton from '../components/LikeButton.vue';

const router = useRouter();
const userStore = useUserStore();
const posts = ref([]);
const searchKeyword = ref('');
const sortBy = ref('latest'); // 默认按最新排序

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
      sortBy: sortBy.value, // 传递排序参数
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
    console.error('点赞操作失败:', error);
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

// 监听排序变化
watch(sortBy, (newValue, oldValue) => {
  if (newValue !== oldValue) {
    pagination.currentPage = 1; // 切换排序时回到第一页
    fetchPosts();
  }
});

onMounted(() => {
  fetchPosts();
});
</script>

<style scoped>
.post-list-container {
  margin: 0 auto;
}
.page-header-card {
  margin-bottom: 20px;
  background-color: rgba(252, 213, 55, 0.9);
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
.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  flex-wrap: wrap; /* Allow wrapping on smaller screens */
}
.search-bar {
  width: 300px; /* Adjust width as needed */
}
.sort-options {
  flex-grow: 1;
}
.post-footer {
  margin-top: 15px;
  padding-top: 10px;
  border-top: 1px solid #f0f0f0;
  display: flex;
  align-items: center;
  gap: 20px;
}
.post-title {
  font-size: 1.4em;
  margin: 0 0 10px 0;
  color: #333;
}
.img-bg1 {
  background-image: url('@/assets/images/background.jpg');
  background-size: cover;
  background-position: auto；
}
/* 覆盖Element Plus，设置lemon样式 */
.post-list-container .el-button--lemon[data-type="custom"] {
  background-color: rgb(252, 211, 55) !important;
  border-color: rgb(252, 211, 55) !important;
  color: #ffffff !important;
}
.post-list-container .el-button--lemon[data-type="custom"]:hover {
  background-color: rgb(245, 198, 21) !important; /* 悬停时稍深的黄色 */
  border-color: rgb(245, 198, 21) !important;
  color: #ffffff !important;
}

.post-list-container .el-message--lemon[data-type="custom"] {
  background-color: rgba(255, 255, 255, 0.1)!important; /* 半透明黄色背景 */
  border-left-color: rgb(252, 211, 55)!important;
  color: #ffffff!important;
}
.post-list-container .el-message--lemon[data-type="custom"] .el-message__content {
  color: #ffffff!important;
}
/* 覆盖Element Plus，设置white样式 */
.post-list-container .el-button--white[data-type="custom"] {
  background-color: rgb(255, 255, 255)!important;
  border-color: rgb(255, 255, 255)!important;
  color:rgb(252, 211, 55)!important;
}
.post-list-container .el-button--white[data-type="custom"]:hover {
  background-color: rgb(252, 211, 55)!important; /* 悬停时稍深的黄色 */
  border-color: rgb(245, 198, 21)!important;
  color: rgb(255, 255, 255)!important;
}

.post-list-container .el-message--white[data-type="custom"] {
  background-color: rgba(255, 255, 255, 0.1)!important; /* 半透明黄色背景 */
  border-left-color: rgb(252, 211, 55)!important;
  color: #ffffff;
}
.post-list-container .el-message--white[data-type="custom"] .el-message__content {
  color: #ffffff!important;
}

</style>
