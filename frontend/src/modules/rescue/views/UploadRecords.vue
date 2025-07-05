<template>
  <div class="p-4 sm:p-6 lg:p-8 bg-gray-50 min-h-screen">
    <div class="max-w-7xl mx-auto">
      <h1 class="text-3xl font-bold text-gray-800 mb-6">我的上报记录</h1>
      
      <!-- 搜索区域 -->
      <div class="p-4 bg-white rounded-lg shadow mb-6">
        <div class="flex items-center space-x-4">
          <el-input
            v-model="searchKeyword"
            placeholder="搜索健康状况、描述、发现地点..."
            clearable
            @clear="handleSearch"
            @keyup.enter="handleSearch"
            class="flex-grow"
          />
          <el-button type="primary" @click="handleSearch">搜索</el-button>
          <el-button @click="handleReset">重置</el-button>
        </div>
      </div>

      <div v-if="loading" class="text-center py-10">
        <p>加载中...</p>
      </div>
      
      <div v-else-if="tableData.length === 0" class="text-center py-10 bg-white rounded-lg shadow">
        <p class="text-gray-500">您还没有任何上报记录。</p>
        <el-button type="primary" class="mt-4" @click="$router.push('/rescue/upload-stray')">去上报</el-button>
      </div>

      <div v-else class="bg-white rounded-lg shadow overflow-hidden">
        <el-table :data="tableData" stripe style="width: 100%" row-key="id">
          <el-table-column type="expand">
            <template #default="props">
              <div class="p-4 bg-gray-100">
                <h3 class="font-semibold text-lg mb-2">详细信息</h3>
                <el-descriptions :column="2" border>
                  <el-descriptions-item label="上报人">{{ props.row.reporter }}</el-descriptions-item>
                  <el-descriptions-item label="联系电话">{{ props.row.reporterPhone }}</el-descriptions-item>
                  <el-descriptions-item label="发现时间">{{ props.row.discoveryTime }}</el-descriptions-item>
                  <el-descriptions-item label="发现地点">{{ props.row.discoveryPlace }}</el-descriptions-item>
                  <el-descriptions-item label="健康状况">{{ props.row.healthStatus }}</el-descriptions-item>
                  <el-descriptions-item label="绝育状况">{{ props.row.sterilizationStatus }}</el-descriptions-item>
                  <el-descriptions-item label="补充描述" :span="2">{{ props.row.description || '无' }}</el-descriptions-item>
                </el-descriptions>
              </div>
            </template>
          </el-table-column>
          
          <el-table-column prop="name" label="动物名字" width="150"></el-table-column>
          <el-table-column prop="species" label="物种" width="100"></el-table-column>
          <el-table-column prop="gender" label="性别" width="80"></el-table-column>

          <el-table-column label="照片" width="300">
            <template #default="scope">
              <div v-if="scope.row.imageUrls && scope.row.imageUrls.length" class="flex flex-wrap gap-2">
                <el-image
                  v-for="url in scope.row.imageUrls"
                  :key="url"
                  :src="'http://localhost:8080' + url"
                  :preview-src-list="scope.row.imageUrls.map(u => 'http://localhost:8080' + u)"
                  :initial-index="scope.row.imageUrls.indexOf(url)"
                  fit="cover"
                  class="w-16 h-16 rounded-md cursor-pointer hover:opacity-80 transition-opacity"
                  lazy
                ></el-image>
              </div>
              <span v-else>无照片</span>
            </template>
          </el-table-column>

          <el-table-column prop="createTime" label="上报时间" sortable></el-table-column>

          <el-table-column label="操作" width="150" fixed="right">
            <template #default="scope">
              <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div class="flex justify-center p-4">
          <el-pagination
            background
            layout="prev, pager, next, jumper, ->, total"
            :total="pagination.total"
            :page-size="pagination.pageSize"
            v-model:current-page="pagination.currentPage"
            @current-change="handlePageChange"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { getUploadRecordsPage, deleteUploadRecord } from '@/modules/rescue/api.js';
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
  console.log("开始获取数据...");
  try {
    const params = {
      pageNum: pagination.currentPage,
      pageSize: pagination.pageSize,
      keyword: searchKeyword.value,
    };
    console.log("请求参数:", params);

    const res = await getUploadRecordsPage(params);
    
    console.log("后端已响应, res 内容:", JSON.stringify(res, null, 2));

    if (res && res.code === 200 && res.data) {
      console.log("响应成功, 准备更新表格数据");
      tableData.value = res.data.records;
      pagination.total = parseInt(res.data.total, 10) || 0;
    } else {
      console.error("响应失败或数据格式不正确", res);
      ElMessage.error(res ? res.message : '获取数据失败，响应为空');
    }
  } catch (error) {
    console.error("捕获到异常:", error);
    ElMessage.error('请求失败，请检查网络');
  } finally {
    loading.value = false;
    console.log("获取数据流程结束.");
  }
};

const handleSearch = () => {
  pagination.currentPage = 1; // 搜索时重置到第一页
  fetchData();
};

const handleReset = () => {
  searchKeyword.value = '';
  handleSearch(); // 调用搜索以刷新
};

const handleEdit = (record) => {
  router.push(`/rescue/upload-stray/${record.id}`);
};

const handleDelete = async (id) => {
  try {
    await ElMessageBox.confirm(
      '此操作将永久删除该上报记录及其关联的待领养信息，是否继续?',
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    );
    
    await deleteUploadRecord(id);
    ElMessage.success('删除成功!');
    fetchData(); // 刷新列表
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error(error.message || '删除失败');
    }
  }
};

const handlePageChange = (newPage) => {
  pagination.currentPage = newPage;
  fetchData();
};

onMounted(() => {
  fetchData();
});
</script>

<style scoped>
/* 可以在这里添加一些微调样式 */
</style>