<template>
  <div class="img-bg">
    <br>
    <h2 class="text-2xl font-bold mb-4 text-center">用户管理</h2>

    <!-- 搜索区域 -->
    <div class="p-4 bg-white rounded-lg shadow mb-6 max-w-[90%] mx-auto">
      <div class="flex items-center space-x-4">
        <el-input
          v-model="searchKeyword"
          placeholder="搜索用户名或昵称..."
          clearable
          @clear="handleSearch"
          @keyup.enter="handleSearch"
          class="flex-grow"
        />
        <el-button type="warning" @click="handleSearch">搜索</el-button>
        <el-button @click="handleReset">重置</el-button>
        <el-button type="success" @click="handleCreate">新增用户</el-button>
      </div>
    </div>

    <!-- 表格区域 -->
    <div class="bg-white/90 rounded-lg shadow overflow-hidden max-w-[90%] mx-auto">
      <div class="flex justify-between items-center">
        <el-table :data="tableData" v-loading="loading" stripe style="width: 100%">
          <el-table-column prop="id" label="ID" width="80" sortable />
          <el-table-column prop="username" label="用户名" width="150" />
          <el-table-column prop="nickname" label="昵称" width="150" />
          <el-table-column prop="role" label="角色" width="100" />
          <el-table-column prop="status" label="状态" width="120">
            <template #default="scope">
              <el-tag :type="scope.row.status === 'ACTIVE' ? 'success' : 'danger'">
                {{ scope.row.status === 'ACTIVE' ? '活跃' : '禁用' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="注册时间" sortable />
          <el-table-column label="操作" width="200" fixed="right">
            <template #default="scope">
              <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button
                size="small"
                :type="scope.row.status === 'ACTIVE' ? 'warning' : 'success'"
                @click="handleToggleStatus(scope.row)"
              >
                {{ scope.row.status === 'ACTIVE' ? '禁用' : '启用' }}
              </el-button>
            </template>
          </el-table-column>
        </el-table>

      </div>

      <!-- 分页区域 -->
      <div class="flex justify-center p-4">
        <el-pagination
          background
          layout="prev, pager, next, jumper, ->, total"
          :total="pagination.total"
          v-model:current-page="pagination.currentPage"
          :page-size="pagination.pageSize"
          @current-change="fetchData"
        />
      </div>
    </div>

    <!-- 用户表单弹窗 -->
    <UserFormModal
      v-model="formModal.visible"
      :user-to-edit="formModal.user"
      @submit-success="onFormSubmitSuccess"
    />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { adminGetAllUsers, adminToggleUserStatus } from '@/modules/user/api';
import { ElMessage, ElMessageBox } from 'element-plus';
import UserFormModal from '@/admin/components/UserFormModal.vue';

const loading = ref(true);
const tableData = ref([]);
const searchKeyword = ref('');
const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

const formModal = reactive({
  visible: false,
  user: null, // null表示新增，对象表示编辑
});

const fetchData = async () => {
  loading.value = true;
  try {
    const params = {
      pageNum: pagination.currentPage,
      pageSize: pagination.pageSize,
      keyword: searchKeyword.value,
    };
    const res = await adminGetAllUsers(params);
    if (res.code === 200 && res.data) {
      tableData.value = res.data.records;
      pagination.total = parseInt(res.data.total, 10) || 0;
    } else {
      ElMessage.error(res.message || '获取用户列表失败');
    }
  } catch (error) {
    ElMessage.error('网络请求失败');
  } finally {
    loading.value = false;
  }
};

const handleSearch = () => {
  pagination.currentPage = 1;
  fetchData();
};

const handleReset = () => {
  searchKeyword.value = '';
  fetchData();
};

const handleCreate = () => {
  formModal.user = null;
  formModal.visible = true;
};

const handleEdit = (user) => {
  formModal.user = JSON.parse(JSON.stringify(user)); // 深拷贝
  formModal.visible = true;
};

const onFormSubmitSuccess = () => {
  fetchData(); // 新增或编辑成功后刷新列表
};

const handleToggleStatus = async (user) => {
  const actionText = user.status === 'ACTIVE' ? '禁用' : '启用';
  try {
    await ElMessageBox.confirm(`确定要${actionText}用户 "${user.username}" 吗?`, '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    });

    const res = await adminToggleUserStatus(user.id);
    if (res.code === 200 && res.data) {
        ElMessage.success(`${actionText}成功!`);
        // 直接在前端更新状态，避免重新请求列表
        user.status = res.data.status;
    } else {
        ElMessage.error(res.message || `${actionText}失败`);
    }
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error(error.message || '操作失败');
    }
  }
};

onMounted(() => {
  fetchData();
});
</script>
<style>
.img-bg {
  position: fixed;
  top: 66px;
  left: 200px;
  right: 0px;
  bottom: 0px;
  background-image: url('@/assets/images/background.jpg');
  background-size: cover;
  background-position: auto；
}
</style>
