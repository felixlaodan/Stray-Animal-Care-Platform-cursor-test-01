<template>
  <div class="post-detail-container">
    <div v-if="loading" class="loading">正在加载...</div>
    <div v-if="post && !loading" class="post-content">
      <h1>{{ post.title }}</h1>
      <div class="post-meta">
        <span>作者: {{ post.authorName }}</span>
        <span>发布于: {{ new Date(post.createTime).toLocaleString() }}</span>
        <LikeButton 
          :liked="post.likedByCurrentUser" 
          :likes-count="post.likesCount" 
          @toggle-like="handleLike" 
        />
      </div>
      
      <!-- 如果有图片，则显示图片 -->
      <div v-if="post.imageUrl" class="post-image-container">
        <img :src="fullImageUrl" alt="帖子图片" class="post-image">
      </div>
      
      <div class="content-body" v-html="post.content"></div>
    </div>

    <hr />

    <!-- 评论区 -->
    <div class="comment-section">
      <h3>评论</h3>
      <div v-if="comments.length > 0">
        <div v-for="comment in comments" :key="comment.id" class="comment-item">
          <div class="comment-header">
            <strong>{{ comment.authorName }}:</strong>
            <div class="comment-actions" v-if="userStore.isAuthenticated && userStore.user?.id === comment.userId">
              <el-button type="primary" link @click="openEditDialog(comment)">编辑</el-button>
              <el-button type="danger" link @click="deleteComment(comment.id)">删除</el-button>
            </div>
          </div>
          <p>{{ comment.content }}</p>
          <small>{{ new Date(comment.createTime).toLocaleString() }}</small>
        </div>
      </div>
      <div v-else>
        <p>暂无评论，快来抢沙发吧！</p>
        </div>
          </div>

    <!-- 发表评论 -->
    <div class="add-comment">
      <h3>发表你的看法</h3>
            <el-input
        v-model="newComment.content"
              type="textarea"
        :rows="3"
        placeholder="写下你的评论..."
      ></el-input>
      <el-button type="primary" @click="submitComment" style="margin-top: 10px;">提交评论</el-button>
    </div>

    <!-- 编辑评论对话框 -->
    <el-dialog v-model="editDialogVisible" title="编辑评论" width="500px">
      <el-input
        v-model="editingCommentContent"
        type="textarea"
        :rows="4"
        placeholder="请输入评论内容"
      />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="editDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleUpdateComment">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { getPostById, getCommentsByPostId, addComment, deleteComment as apiDeleteComment, updateComment as apiUpdateComment, toggleLikePost } from '../api.js';
import { useUserStore } from '@/stores/user.js';
import { ElMessage, ElMessageBox } from 'element-plus';
import LikeButton from '../components/LikeButton.vue';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

const post = ref(null);
const comments = ref([]);
const loading = ref(true);
const postId = route.params.id;

const fullImageUrl = computed(() => {
  if (!post.value || !post.value.imageUrl) return '';
  if (post.value.imageUrl.startsWith('http')) {
    return post.value.imageUrl;
  }
  return 'http://localhost:8080' + post.value.imageUrl;
});

const newComment = reactive({
  content: '',
});

// 编辑评论相关状态
const editDialogVisible = ref(false);
const editingComment = ref(null);
const editingCommentContent = ref('');

onMounted(async () => {
  try {
    const postRes = await getPostById(postId);
    if (postRes.code === 200) {
      post.value = postRes.data;
    }

    const commentsRes = await getCommentsByPostId(postId);
    if (commentsRes.code === 200) {
      comments.value = commentsRes.data;
    }
  } catch (error) {
    console.error('加载帖子详情失败:', error);
    ElMessage.error('加载帖子详情失败');
  } finally {
    loading.value = false;
  }
});

const submitComment = async () => {
  if (!newComment.content) {
    ElMessage.warning('评论内容不能为空');
    return;
  }
  
  if (!userStore.isAuthenticated) {
    ElMessage.error('请先登录再发表评论');
    return;
  }

  try {
    const commentData = {
      postId: postId,
      content: newComment.content,
      // userId 和 authorName 将由后端根据 token 自动填充
    };
    
    const res = await addComment(commentData);

    if (res.code === 200) {
      ElMessage.success('评论成功！');
      // 将新评论实时添加到列表顶部，并包含后端返回的完整信息
      comments.value.unshift(res.data);
      newComment.content = ''; // 清空输入框
    } else {
      ElMessage.error(res.message || '评论失败');
    }
  } catch (error) {
    console.error('提交评论失败:', error);
    ElMessage.error('提交评论失败');
  }
};

const deleteComment = async (commentId) => {
  try {
    await ElMessageBox.confirm('确定要删除这条评论吗？', '提示', { type: 'warning' });
    await apiDeleteComment(commentId);
      ElMessage.success('评论删除成功！');
    comments.value = comments.value.filter(c => c.id !== commentId);
  } catch (error) {
    if (error !== 'cancel') ElMessage.error('删除评论失败');
    }
};

const openEditDialog = (comment) => {
  editingComment.value = comment;
  editingCommentContent.value = comment.content;
  editDialogVisible.value = true;
};

const handleUpdateComment = async () => {
  if (!editingCommentContent.value.trim()) {
    ElMessage.warning('评论内容不能为空');
    return;
  }
  try {
    const res = await apiUpdateComment(editingComment.value.id, {
      content: editingCommentContent.value,
    });
    const updatedComment = res.data;
    const index = comments.value.findIndex(c => c.id === updatedComment.id);
    if (index !== -1) {
      comments.value[index] = updatedComment;
    }
    ElMessage.success('评论更新成功！');
    editDialogVisible.value = false;
  } catch (error) {
    ElMessage.error('更新评论失败');
  }
};

const handleLike = async () => {
  if (!userStore.isAuthenticated) {
    ElMessage.warning('请先登录再点赞');
    router.push('/login');
    return;
  }
  if (!post.value) return;

  // 乐观更新UI
  const originalLikedState = post.value.likedByCurrentUser;
  const originalLikesCount = post.value.likesCount;

  post.value.likedByCurrentUser = !post.value.likedByCurrentUser;
  post.value.likesCount += post.value.likedByCurrentUser ? 1 : -1;

  try {
    await toggleLikePost(post.value.id);
  } catch (error) {
    // 如果API请求失败，则恢复UI
    post.value.likedByCurrentUser = originalLikedState;
    post.value.likesCount = originalLikesCount;
    ElMessage.error('操作失败，请稍后重试');
    console.error("点赞失败:", error);
  }
};
</script>

<style scoped>
.post-detail-container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff; /* 确保有白色背景 */
  color: #333; /* 设定基础文字颜色为深灰色 */
}
.loading {
  text-align: center;
  color: #666;
  padding: 40px;
}
.post-content h1 {
  font-size: 2em;
  margin-bottom: 0.5em;
  color: #303133;
}
.post-meta {
  color: #888;
  font-size: 0.9em;
  margin-bottom: 1.5em;
  display: flex;
  align-items: center;
  gap: 15px;
}
.content-body {
  line-height: 1.8;
  color: #333;
}
hr {
  margin: 40px 0;
  border: 0;
  border-top: 1px solid #eee;
}
.comment-section h3, .add-comment h3 {
  font-size: 1.5em;
  margin-bottom: 1em;
    color: #303133;
  border-left: 4px solid #409eff;
  padding-left: 10px;
}
.comment-item {
  border-bottom: 1px solid #eee;
  padding: 15px 0;
}
.comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
}
.comment-header strong {
  color: #409eff; /* 作者名用蓝色突出 */
}
.comment-item p {
  color: #555;
  line-height: 1.7;
  margin: 8px 0;
}
.comment-item small {
  color: #aaa;
  font-size: 0.8em;
}
.add-comment {
  margin-top: 30px;
}
.comment-actions {
  display: inline-block;
}
.post-image-container {
  width: 100%;
  margin-bottom: 1.5em;
}
.post-image {
  max-width: 100%;
  border-radius: 8px;
}
</style> 
