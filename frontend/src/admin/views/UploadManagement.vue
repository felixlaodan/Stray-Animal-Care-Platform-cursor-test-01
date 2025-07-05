<template>
  <div>
    <h2 class="text-2xl font-bold mb-4">流浪动物上报管理</h2>

    <!-- 搜索区域 -->
    <div class="p-4 bg-white rounded-lg shadow mb-6">
      <div class="flex justify-between items-center">
        <div class="flex items-center space-x-4 flex-grow">
          <el-input
            v-model="searchKeyword"
            placeholder="搜索健康状况、描述、发现地点..."
            clearable
            @clear="handleSearch"
            @keyup.enter="handleSearch"
          />
          <el-button type="primary" @click="handleSearch">搜索</el-button>
          <el-button @click="handleReset">重置</el-button>
        </div>
        <!-- 新增按钮 -->
        <div>
          <el-button type="success" @click="handleCreate">新增上报记录</el-button>
        </div>
      </div>
    </div>

    <!-- 表格区域 -->
    <div class="bg-white rounded-lg shadow overflow-hidden">
      <el-table :data="tableData" v-loading="loading" stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" sortable></el-table-column>
        <el-table-column prop="reporter" label="上报人" width="120"></el-table-column>
        <el-table-column prop="name" label="动物名" width="120"></el-table-column>
        <el-table-column prop="species" label="物种" width="100"></el-table-column>
        <el-table-column prop="healthStatus" label="健康状况" show-overflow-tooltip></el-table-column>
        <el-table-column prop="createTime" label="上报时间" sortable width="180"></el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row.id)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

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
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { adminGetAllUploads, adminDeleteUploadRecord } from '@/modules/rescue/api';
import { ElMessage, ElMessageBox } from 'element-plus';
import { useRouter } from 'vue-router';

const router = useRouter();
const loading = ref(true);
const tableData = ref([]);
const searchKeyword = ref('');
const pagination = reactive({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

const fetchData = async () => {
  loading.value = true;
  try {
    const params = {
      pageNum: pagination.currentPage,
      pageSize: pagination.pageSize,
      keyword: searchKeyword.value,
    };
    const res = await adminGetAllUploads(params);
    if (res.code === 200 && res.data) {
      tableData.value = res.data.records;
      pagination.total = parseInt(res.data.total, 10) || 0;
    } else {
      ElMessage.error(res.message || '获取数据失败');
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
  router.push('/admin/upload-stray/new');
};

const handleEdit = (id) => {
  router.push(`/admin/upload-stray/edit/${id}`);
};

const handleDelete = async (id) => {
  try {
    await ElMessageBox.confirm('此操作将永久删除该记录及其所有关联信息，是否继续?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    });
    
    await adminDeleteUploadRecord(id);
    ElMessage.success('删除成功!');
    fetchData(); // 刷新列表

  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error(error.message || '删除失败');
    }
  }
};

onMounted(() => {
  fetchData();
});
</script> 