<template>
  <el-container class="post-detail-container">
    <el-header class="page-header-container">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-large font-600 mr-3"> {{ post.title || '加载中...' }} </span>
        </template>
        <template #extra>
          <div class="page-header-extra">
            <el-button 
              v-if="post.userId === currentUserId"
              type="primary"
              link
              @click="openEditDialog(post, 'post')"
            >
              编辑
            </el-button>
            <el-button 
              v-if="post.userId === currentUserId" 
              type="danger" 
              link 
              @click="handleDeletePost(post.id)"
            >
              删除帖子
            </el-button>
          </div>
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
              <div>
                <strong>{{ comment.authorName }}</strong>
                <span class="time">{{ formatDateTime(comment.createdAt) }}</span>
              </div>
              <div>
                <el-button
                  v-if="comment.userId === currentUserId"
                  type="primary"
                  link
                  @click="openEditDialog(comment, 'comment')"
                >
                  编辑
                </el-button>
                <el-button 
                  v-if="comment.userId === currentUserId"
                  type="danger" 
                  link 
                  @click="handleDeleteComment(comment.id)"
                >
                  删除
                </el-button>
              </div>
            </div>
            <div class="comment-content">{{ comment.content }}</div>
            <el-divider v-if="index < post.comments.length - 1"></el-divider>
          </div>
        </div>
        <div v-else class="no-comments">
          <p>暂无评论</p>
        </div>
      </el-card>

      <!-- 评论提交表单卡片 -->
      <el-card class="box-card comment-form-card" v-if="post.id">
        <template #header>
          <div class="card-header">
            <span>发表你的看法</span>
          </div>
        </template>
        <el-form @submit.prevent="handleCommentSubmit">
          <el-form-item>
            <el-input
              v-model="newComment"
              type="textarea"
              :rows="4"
              placeholder="请输入评论内容..."
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleCommentSubmit">发表评论</el-button>
          </el-form-item>
        </el-form>
      </el-card>

    </el-main>

    <!-- 编辑对话框 -->
    <el-dialog v-model="isDialogVisible" title="编辑内容" width="50%">
      <el-input
        v-model="editContent"
        type="textarea"
        :rows="6"
        placeholder="请输入内容"
      />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="isDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleUpdateSubmit">
            确定
          </el-button>
        </span>
      </template>
    </el-dialog>
  </el-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { getPostDetail, addComment, deletePost, deleteComment, updatePost, updateComment } from '../api.js';
import { ElMessage, ElMessageBox } from 'element-plus';

const route = useRoute();
const router = useRouter();
const currentUserId = 1; // 模拟当前登录的用户ID

const post = ref({});
const newComment = ref('');

// 编辑对话框相关状态
const isDialogVisible = ref(false);
const editingItem = ref(null);
const editContent = ref('');

const fetchPostDetail = async () => {
  const postId = route.params.id;
  if (!postId) return;

  try {
    const response = await getPostDetail(postId);
    if (response.data && response.data.code === 200) {
      post.value = response.data.data;
    } else {
      ElMessage.error(response.data.message || '获取帖子详情失败');
    }
  } catch (error) {
    ElMessage.error('请求帖子详情异常');
    console.error("请求帖子详情异常:", error);
  }
};

const openEditDialog = (item, type) => {
  editingItem.value = { ...item, type }; // 存储当前编辑项及其类型
  // 如果是编辑帖子，我们可能需要编辑标题和内容，这里简化为只编辑内容
  editContent.value = item.content;
  isDialogVisible.value = true;
};

const handleUpdateSubmit = async () => {
  if (!editingItem.value) return;

  const { id, type } = editingItem.value;
  let response;

  try {
    if (type === 'post') {
      // 实际应用中可能需要更新标题等，这里简化为只更新内容
      const postData = { ...editingItem.value, content: editContent.value };
      response = await updatePost(id, currentUserId, postData);
    } else if (type === 'comment') {
      const commentData = { content: editContent.value };
      response = await updateComment(id, currentUserId, commentData);
    }

    if (response && response.data.code === 200) {
      ElMessage.success('更新成功！');
      isDialogVisible.value = false;
      await fetchPostDetail();
    } else {
      ElMessage.error(response.data.message || '更新失败');
    }
  } catch (error) {
    ElMessage.error('更新操作异常');
  }
};

const handleDeletePost = async (postId) => {
  try {
    await ElMessageBox.confirm(
      '确定要删除这篇帖子吗？此操作不可逆。',
      '警告',
      {
        confirmButtonText: '确定删除',
        cancelButtonText: '取消',
        type: 'warning',
      }
    );
    
    const response = await deletePost(postId, currentUserId);
    if (response.data && response.data.code === 200) {
      ElMessage.success('帖子删除成功！');
      await router.push('/');
    } else {
      ElMessage.error(response.data.message || '删除失败，你可能没有权限');
    }
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除操作异常');
    }
  }
};

const handleDeleteComment = async (commentId) => {
  try {
    await ElMessageBox.confirm(
      '确定要删除这条评论吗？',
      '确认',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info',
      }
    );

    const response = await deleteComment(commentId, currentUserId);
    if (response.data && response.data.code === 200) {
      ElMessage.success('评论删除成功！');
      await fetchPostDetail(); // 刷新评论列表
    } else {
      ElMessage.error(response.data.message || '删除失败，你可能没有权限');
    }
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除操作异常');
    }
  }
};

const handleCommentSubmit = async () => {
  if (!newComment.value.trim()) {
    ElMessage.warning('评论内容不能为空！');
    return;
  }

  const postId = route.params.id;
  const commentData = {
    content: newComment.value,
    authorName: '热心网友', // 暂时硬编码作者名
    userId: currentUserId
  };

  try {
    const response = await addComment(postId, commentData);
    if (response.data && response.data.code === 200) {
      ElMessage.success('评论发表成功！');
      newComment.value = ''; // 清空输入框
      await fetchPostDetail(); // 重新加载帖子数据，刷新评论列表
    } else {
      ElMessage.error(response.data.message || '评论发表失败');
    }
  } catch (error) {
    ElMessage.error('评论发表异常');
    console.error("评论发表异常:", error);
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

.page-header-extra {
  display: flex;
  align-items: center;
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
    align-items: center;
    font-size: 14px;
    margin-bottom: 8px;
}

.comment-author > div {
  display: flex;
  align-items: center;
  gap: 8px;
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

.comment-form-card {
  margin-top: 20px;
}
</style> 