<script setup lang="ts">
import {reactive} from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { Delete, Edit, Plus } from '@element-plus/icons-vue'
import { ElImageViewer } from 'element-plus'
import { 
  getAdoptionInfos, 
  createAdoptionInfo, 
  updateAdoptionInfo, 
  deleteAdoptionInfo, 
  deleteAdoptionInfoBatch 
} from '../api';

const data = reactive({
  name: null,
  reporter: null,
  species: null,
  info: [] as any[],
  pageNum: 1,
  pageSize: 5,
  total: 0,
  dialogFormVisible: false,
  form: {} as any,
  fileList: [] as any[],
  ids: [] as number[],
  previewVisible: false,
  previewImageList: [] as string[],
})

const load = () => {
  const params = {
    reporter: data.reporter,
    name: data.name,
    species: data.species,
    pageNum: data.pageNum,
    pageSize: data.pageSize,
  };
  getAdoptionInfos(params).then(res => {
    const responseData = res.data;
    if (responseData.code === '200' && responseData.data) {
      responseData.data.records.forEach((record: any) => {
        if (record.imageUrl && typeof record.imageUrl === 'string') {
          record.imageUrl = record.imageUrl.split(',');
        }
      });
      data.info = responseData.data.records;
      data.total = responseData.data.total;
    }
  });
}

load()

const reset = () => {
  data.name = null;
  load();
}

const handleAdd = () => {
  data.dialogFormVisible=true
  data.form = {}
  data.fileList = []
}

const save = () => {
  if (data.form.adoptionInfoId) {
    update();
  } else {
    add();
  }
}

const add = () => {
  const formData = { ...data.form };
  if (Array.isArray(formData.imageUrl)) {
    formData.imageUrl = formData.imageUrl.join(',');
  }
  createAdoptionInfo(formData).then(res => {
    const responseData = res.data;
    if (responseData.code === '200') {
      data.dialogFormVisible = false;
      ElMessage.success("操作成功");
      load();
    } else {
      ElMessage.error(responseData.msg || '操作失败');
    }
  });
}

const update = () => {
  const formData = { ...data.form };
  if (Array.isArray(formData.imageUrl)) {
    formData.imageUrl = formData.imageUrl.join(',');
  }
  updateAdoptionInfo(formData).then(res => {
    const responseData = res.data;
    if (responseData.code === '200') {
      data.dialogFormVisible = false;
      ElMessage.success("操作成功");
      load();
    } else {
      ElMessage.error(responseData.msg || '操作失败');
    }
  });
}

const handleImageClick = (row) => {
  console.log('进入 handleImageClick 方法');
  data.previewImageList = Array.isArray(row.imageUrl) ? row.imageUrl : [row.imageUrl]
  console.log('预览图片列表:', data.previewImageList);
  data.previewVisible = true
  console.log('预览可见状态:', data.previewVisible);
}

const handleUpdate = (row: any) => {
  data.form = JSON.parse(JSON.stringify(row));
  data.dialogFormVisible = true;
  const imageUrls = Array.isArray(row.imageUrl) ? row.imageUrl : (row.imageUrl ? [row.imageUrl] : []);
  data.fileList = imageUrls.map((url: string) => ({ name: '', url }));
}

const handleDelete = (row: any) => {
  ElMessageBox.confirm('删除数据后无法恢复，确认删除吗？','删除确认',{type:'warning'}).then(()=>{
    deleteAdoptionInfo(row.adoptionInfoId).then(res=>{
      const responseData = res.data;
      if (responseData.code === '200'){
        console.log(responseData)
        data.dialogFormVisible=false
        ElMessage.success("操作成功")
        load()
      }else {
        ElMessage.error(responseData.msg || '操作失败')
      }
    })
  }).catch()
}

const handleSelectionChange = (rows: any[]) => {
  data.ids = rows.map(row => row.adoptionInfoId)
  console.log(data.ids)
}

const handleDeleteBatch = () => {
  if (data.ids.length === 0){
    ElMessage.warning('请选择数据')
    return
  }
  ElMessageBox.confirm('删除数据后无法恢复，确认删除吗？','删除确认',{type:'warning'}).then(()=>{
    deleteAdoptionInfoBatch(data.ids).then(res=>{
      if (res.code === '200'){
        console.log(res)
        data.dialogFormVisible=false
        ElMessage.success("操作成功")
        load()
      }else {
        ElMessage.error(res.msg || '操作失败')
      }
    })
  }).catch()
}

const handleUploadSuccess = (response: any, file: any, fileList: any[]) => {
    if (response.code === '200') {
        if (!Array.isArray(data.form.imageUrl)) {
            data.form.imageUrl = [];
        }
        data.form.imageUrl.push(response.data);
        data.fileList = fileList;
    } else {
        ElMessage.error(response.msg || '上传失败');
        const index = fileList.findIndex(f => f.uid === file.uid);
        if (index > -1) {
            fileList.splice(index, 1);
        }
    }
};

const beforeUpload = (file: any) => {
  const isImage = /^image/.test(file.type)
  if (!isImage) {
    ElMessage.error('只能上传图片文件！')
  }
  return isImage
}

const handleCloseImageViewer = () => {
  console.log('进入 handleCloseImageViewer 方法');
  console.log('关闭前预览可见状态:', data.previewVisible);
  console.log('当前预览图片列表长度:', data.previewImageList.length);
  data.previewVisible = false
  data.previewImageList = []
  console.log('关闭后预览可见状态:', data.previewVisible);
  console.log('更新后预览图片列表长度:', data.previewImageList.length);
}

</script>

<template>
  <div>
    <el-card>
        <el-input style="inline-size: 200px; margin-inline-end: 10px;" v-model="data.name" placeholder="请输入上报人"></el-input>
      <el-input style="inline-size: 200px; margin-inline-end: 10px;" v-model="data.name" placeholder="请输入动物名字"></el-input>
      <el-select style="inline-size: 100px; margin-inline-end: 10px;" v-model="data.species" placeholder="请选择物种">
        <el-option label="狗" value="狗"></el-option>
        <el-option label="猫" value="猫"></el-option>
        <el-option label="其他" value="其他"></el-option>
      </el-select>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </el-card>

    <el-card>
      <el-button type="success" @click="handleAdd">新增</el-button>
      <el-button type="warning" @click="handleDeleteBatch">批量删除</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="danger">导出</el-button>
    </el-card>

    <el-card text-align="center">
      <el-table :data="data.info" stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection"></el-table-column>
        <el-table-column label="上报人" prop="reporter" width="80px"></el-table-column>
        <el-table-column label="联系电话" prop="reporterPhone" width="120px"></el-table-column>
        <el-table-column label="动物名字" prop="name" width="80px"></el-table-column>
        <el-table-column label="物种" prop="species" width="60px"></el-table-column>
        <el-table-column label="性别" prop="gender" width="60px"></el-table-column>
        <el-table-column label="健康状态" prop="healthStatus" width="140px"></el-table-column>
        <el-table-column label="绝育状态" prop="sterilizationStatus" width="80px"></el-table-column>
        <el-table-column label="发现时间" prop="discoveryTime" width="100px"></el-table-column>
        <el-table-column label="发现地点" prop="discoveryPlace" width="250px"></el-table-column>
        <el-table-column label="补充描述" prop="description" width="250px"></el-table-column>
        <el-table-column label="图片集" prop="imageUrl" width="100px">
          <template #default="scope">
            <el-button type="success" @click="() => handleImageClick(scope.row)">点击查看</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope" style="inline-size: 120px">
            <el-button type="primary" :icon="Edit" circle @click="handleUpdate(scope.row)" />
            <el-button type="danger" :icon="Delete" circle @click="handleDelete(scope.row)"/>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-card>
      <el-pagination
          @size-change="load"
          @current-change="load"
          v-model:current-page="data.pageNum"
          v-model:page-size="data.pageSize"
          :page-sizes="[5,10,15,20]"
          :pager-count="11"
          background
          layout="total, sizes, prev, pager, next,jumper"
          :total="data.total"
      />
    </el-card>

  </div>

  <el-dialog v-model="data.dialogFormVisible" title="上报流浪动物记录" width="700px" append-to-body=false>
    <el-form-item :model="data.form">

      <el-row>
        <el-col :span="8"> 
          <el-form-item label="上报人">
            <el-input v-model="data.form.reporter" autocomplete="off" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系电话">
            <el-input v-model="data.form.reporterPhone" autocomplete="off" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="动物名字">
            <el-input v-model="data.form.name" autocomplete="off" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="动物物种">
            <el-select v-model="data.form.species" placeholder="请选择动物物种">
              <el-option label="狗" value="狗" /> 
              <el-option label="猫" value="猫" /> 
              <el-option label="其他" value="其他" /> 
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="动物性别">
            <el-button :type="data.form.gender === '母' ? 'primary' : ''" @click="data.form.gender = '母'">母</el-button>
            <el-button :type="data.form.gender === '公' ? 'primary' : ''" @click="data.form.gender = '公'">公</el-button>        
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="16"> 
          <el-form-item label="健康状态">
            <el-input v-model="data.form.healthStatus" placeholder="请选择健康状态">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="绝育状态">
            <el-select v-model="data.form.sterilizationStatus" placeholder="请选择动物绝育状态">
              <el-option label="不详" value="不详" /> 
              <el-option label="已绝育" value="已绝育" /> 
              <el-option label="未绝育" value="未绝育" /> 
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8"> 
          <el-form-item label="发现时间">
            <el-date-picker v-model="data.form.discoveryTime" type="date" placeholder="请选择一个日期" style="inline-size: 100%" />
          </el-form-item>
        </el-col>
        <el-col :span="16"> 
          <el-form-item label="发现地点">
            <el-input v-model="data.form.discoveryPlace" autocomplete="off" placeholder="请输入动物发现地点" />
          </el-form-item>
        </el-col>
      </el-row>
  
    <el-row>
        <el-col :span="24"> 
          <el-form-item label="补充描述">
            <el-input v-model="data.form.description" autocomplete="off" placeholder="请输入补充描述" />
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row>
        <el-col :span="24"> 
          <el-form-item label="图片">
            <el-upload
              v-model:file-list="data.fileList"
              action="/api/files/upload"
              list-type="picture-card"
              :on-success="handleUploadSuccess"
              :before-upload="beforeUpload"
              multiple
            >
              <el-icon><Plus /></el-icon>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>

      <div class="dialog-footer">
        <el-button @click="data.dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="save()">
          保存
        </el-button>
      </div>
    </el-form-item>
  </el-dialog>

  <el-image-viewer
    v-if="data.previewImageList.length"
    v-model="data.previewVisible"
    :url-list="data.previewImageList"
    @close="handleCloseImageViewer"
  />


</template>

<style scoped>
.el-card :deep(.el-table) {
  text-align: center !important;
}
.el-card :deep(.el-table .cell) {
  text-align: center !important;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 148px;
  height: 148px;
  display: inline-flex;
  justify-content: center;
  align-items: center;
}
.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 148px;
  height: 148px;
  line-height: 148px;
  text-align: center;
}
.avatar {
  width: 148px;
  height: 148px;
  display: block;
}
  .el-form-item {
    margin-bottom: 22px;
  }

  .el-form-item__content {
    width: 100%;
  }

  .el-input, .el-select, .el-date-picker {
    width: 100%;
  }

  .el-dialog .el-form-item {
    width: calc(100% - 20px);
    margin-left: 10px;
  }

.el-row {
  display: flex;
  width: 100%;
}

.el-col {
  box-sizing: border-box;
  padding: 0 10px;
}

/* :deep(.el-dialog__header) {
    display: flex !important;
    justify-content: center !important;
    align-items: center !important;
  }
董宜旭：让修改表单的头、脚居中
:deep(.el-dialog__footer) {
    display: flex !important;
    justify-content: center !important;
    gap: 10px !important;
    padding: 10px 20px !important;
  } */
</style> 