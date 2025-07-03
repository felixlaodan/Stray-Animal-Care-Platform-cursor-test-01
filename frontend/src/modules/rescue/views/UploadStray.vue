<template>
  <div class="container mx-auto p-4 max-w-3xl">
    <div class="bg-white rounded-xl shadow-lg p-6 mb-6 transform transition-all duration-300 hover:shadow-xl">
      <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">流浪动物上报</h2>
      
      <form @submit.prevent="submitForm" class="space-y-6">
        <!-- 上报人信息部分 -->
        <div class="border-b border-gray-200 pb-6">
          <h3 class="text-xl font-semibold text-gray-700 mb-4">上报人信息</h3>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <label for="reporter" class="block text-sm font-medium text-gray-700">上报人</label>
              <input 
                type="text" 
                id="reporter" 
                v-model="formData.reporter" 
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200"
                required
              >
            </div>
            <div class="space-y-2">
              <label for="reporterPhone" class="block text-sm font-medium text-gray-700">联系电话</label>
              <div class="relative">
                <input 
                  type="tel" 
                  id="reporterPhone" 
                  v-model="formData.reporterPhone" 
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200"
                  :class="phoneError ? 'border-red-500 focus:ring-red-500 focus:border-red-500' : ''"
                  required
                >
                <div 
                  v-if="phoneError" 
                  class="absolute -bottom-6 left-0 text-xs text-red-500 transition-opacity duration-300 opacity-100"
                >
                  电话号码不符合格式
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <!-- 流浪动物信息部分 -->
        <div class="space-y-6">
          <h3 class="text-xl font-semibold text-gray-700 mb-4">流浪动物信息</h3>
          
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <label for="name" class="block text-sm font-medium text-gray-700">动物名字</label>
              <input 
                type="text" 
                id="name" 
                v-model="formData.name" 
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200"
                required
              >
            </div>
            <div class="space-y-2">
              <label for="species" class="block text-sm font-medium text-gray-700">物种</label>
              <select 
                id="species" 
                v-model="formData.species" 
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200 appearance-none bg-white"
                required>请选择动物物种
                <option value="狗">狗</option>
                <option value="猫">猫</option>
                <option value="其他">其他</option>
              </select>
            </div>
          </div>
          
          <div class="space-y-2">
            <label class="block text-sm font-medium text-gray-700">性别</label>
            <div class="flex space-x-4 mt-2">
              <label class="inline-flex items-center cursor-pointer">
                <input 
                  type="radio" 
                  name="gender" 
                  value="母" 
                  v-model="formData.gender" 
                  class="form-radio h-5 w-5 text-lemon focus:ring-lemon transition-all duration-200"
                  required
                >
                <span class="ml-2 text-gray-700">母</span>
              </label>
              <label class="inline-flex items-center cursor-pointer">
                <input 
                  type="radio" 
                  name="gender" 
                  value="公" 
                  v-model="formData.gender" 
                  class="form-radio h-5 w-5 text-lemon focus:ring-lemon transition-all duration-200"
                  required
                >
                <span class="ml-2 text-gray-700">公</span>
              </label>
            </div>
          </div>
          
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <label for="healthStatus" class="block text-sm font-medium text-gray-700">健康状态</label>
              <input 
                type="text" 
                id="healthStatus" 
                v-model="formData.healthStatus" 
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200"
                required
              >
            </div>
            <div class="space-y-2">
              <label for="sterilizationStatus" class="block text-sm font-medium text-gray-700">绝育状态</label>
              <select 
                id="sterilizationStatus" 
                v-model="formData.sterilizationStatus" 
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200 appearance-none bg-white"
                required
              >
                <option value="不详">不详</option>
                <option value="已绝育">已绝育</option>
                <option value="未绝育">未绝育</option>
              </select>
            </div>
          </div>
          
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <label for="discoveryTime" class="block text-sm font-medium text-gray-700">发现时间</label>
              <input 
                type="date" 
                id="discoveryTime" 
                v-model="formData.discoveryTime" 
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200"
                required
              >
            </div>
            <div class="space-y-2">
              <label for="discoveryPlace" class="block text-sm font-medium text-gray-700">发现地点</label>
              <input 
                type="text" 
                id="discoveryPlace" 
                v-model="formData.discoveryPlace" 
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200"
                required
              >
            </div>
          </div>
          
          <div class="space-y-2">
            <label for="description" class="block text-sm font-medium text-gray-700">补充描述</label>
            <textarea 
              id="description" 
              v-model="formData.description" 
              rows="3" 
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-lemon focus:border-lemon transition-all duration-200"
            ></textarea>
          </div>
          
          <div class="space-y-2">
            <label class="block text-sm font-medium text-gray-700">图片</label>
            <div class="flex flex-wrap gap-4">
              <div 
                class="relative w-24 h-24 border-2 border-dashed border-gray-300 rounded-lg flex items-center justify-center cursor-pointer hover:border-lemon transition-all duration-200"
                @click="handleFileUpload"
              >
                <div class="text-center">
                  <i class="fa fa-plus text-gray-400 text-xl"></i>
                  <p class="text-xs text-gray-500 mt-1">上传图片</p>
                </div>
                <input 
                  type="file" 
                  accept="image/*" 
                  multiple 
                  class="absolute inset-0 opacity-0 cursor-pointer" 
                  @change="handleFileChange"
                  id="fileInput"
                >
              </div>
              
              <div v-for="(image, index) in formData.images" :key="index" class="relative w-24 h-24">
                <img 
                  :src="image.previewUrl" 
                  alt="上传的流浪动物照片"
                  class="w-full h-full object-cover rounded-lg border-2 border-transparent hover:border-lemon transition-all duration-200"
                >
                <button 
                  type="button" 
                  class="absolute -top-2 -right-2 bg-red-500 text-white rounded-full w-5 h-5 flex items-center justify-center text-xs opacity-0 hover:opacity-100 transition-opacity duration-200"
                  @click="removeImage(index)"
                >
                  <i class="fa fa-times"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
        
        <!-- 按钮区域 -->
        <div class="flex justify-end space-x-4 pt-4 border-t border-gray-200">
          <button 
            type="button" 
            @click="resetForm" 
            class="px-6 py-2 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition-all duration-200 transform hover:scale-105"
          >
            重置
          </button>
          <button 
            type="submit" 
            class="px-6 py-2 bg-lemon text-white rounded-lg hover:bg-lemon transition-all duration-200 transform hover:scale-105 flex items-center"
            :disabled="isSubmitting"
          >
            <span v-if="!isSubmitting">提交</span>
            <span v-else>
              <i class="fa fa-circle-o-notch fa-spin mr-2"></i>
              提交中...
            </span>
          </button>
        </div>
      </form>
    </div>
    
    <!-- 提交成功提示 -->
    <div 
      v-if="showSuccessMessage" 
      class="fixed inset-0 flex items-center justify-center z-50"
    >
      <div class="absolute inset-0 bg-black bg-opacity-50"></div>
      <div class="bg-white rounded-lg p-6 max-w-md w-full mx-4 z-10 transform transition-all duration-300 scale-100 opacity-100">
        <div class="text-center">
          <div class="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <i class="fa fa-check text-green-500 text-2xl"></i>
          </div>
          <h3 class="text-xl font-bold text-gray-800 mb-2">提交成功</h3>
          <p class="text-gray-600 mb-6">感谢您的爱心上报，我们会尽快处理</p>
          <button 
            @click="hideSuccessMessage" 
            class="px-6 py-2 bg-lemon text-white rounded-lg hover:bg-lemon-600 transition-all duration-200"
          >
            确定
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { createUploadRecord } from '../api'
import request from '@/core/api/index.js'
import { ElMessage } from 'element-plus'

// 表单数据
const formData = ref({
  reporter: '',
  reporterPhone: '',
  name: '',
  species: '',
  gender: '',
  healthStatus: '',
  sterilizationStatus: '',
  discoveryTime: '',
  discoveryPlace: '',
  description: '',
  images: []
})

// 表单状态
const phoneError = ref(false)
const isSubmitting = ref(false)
const showSuccessMessage = ref(false)

// 验证电话号码格式
const validatePhone = () => {
  const phoneRegex = /^1\d{10}$/
  phoneError.value = !phoneRegex.test(formData.value.reporterPhone)
}

// 处理文件上传
const handleFileUpload = () => {
  const fileInput = document.getElementById('fileInput')
  if (fileInput) {
    fileInput.click()
  } else {
    console.error('未找到 id 为 fileInput 的元素')
  }
}

// 处理文件变化
const handleFileChange = (event) => {
  const files = event.target.files
  if (!files.length) return
  
  for (let i = 0; i < files.length; i++) {
    const file = files[i]
    const reader = new FileReader()
    
    reader.onload = (e) => {
      formData.value.images.push({
        file,
        previewUrl: e.target.result
      })
    }
    
    reader.readAsDataURL(file)
  }
  
  // 清空文件输入，以便可以重复选择同一文件
  event.target.value = ''
}

// 移除图片
const removeImage = (index) => {
  formData.value.images.splice(index, 1)
}

// 重置表单
const resetForm = () => {
  formData.value = {
    reporter: '',
    reporterPhone: '',
    name: '',
    species: '',
    gender: '',
    healthStatus: '',
    sterilizationStatus: '',
    discoveryTime: '',
    discoveryPlace: '',
    description: '',
    images: []
  }
  phoneError.value = false
}

// 提交表单
const submitForm = async () => {
  validatePhone()
  if (phoneError.value) {
    ElMessage.warning('请输入正确的手机号')
    return
  }
  
  // 简单的前端验证
  const requiredFields = ['reporter', 'reporterPhone', 'name', 'species', 'gender', 'healthStatus', 'sterilizationStatus', 'discoveryTime', 'discoveryPlace']
  if (requiredFields.some(field => !formData.value[field])) {
    ElMessage.warning('请填写所有必填项')
    return
  }

  isSubmitting.value = true

  try {
    // 步骤1: 上传所有图片
    const uploadPromises = formData.value.images.map(image => {
      const fileFormData = new FormData()
      fileFormData.append('file', image.file)
      // 使用 request 实例上传文件
      return request.post('/api/files/upload', fileFormData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
    })

    const uploadResults = await Promise.all(uploadPromises)
    const imageUrls = uploadResults.map(res => res.data.data) // 假设成功时，URL在 res.data.data 中

    // 步骤2: 准备并提交表单数据
    const recordData = {
      ...formData.value,
      imageUrl: imageUrls.join(','), // 将 URL 数组转为逗号分隔的字符串
      images: undefined, // 清理掉不再需要的原始图片数据
    }
    
    const res = await createUploadRecord(recordData)
    if (res.data.code === '200') {
      showSuccessMessage.value = true
      resetForm()
    } else {
      throw new Error(res.data.msg || '提交失败')
    }

  } catch (error) {
    console.error('提交失败:', error)
    ElMessage.error(error.message || '提交过程中发生错误，请重试')
  } finally {
    isSubmitting.value = false
  }
}

const hideSuccessMessage = () => {
  showSuccessMessage.value = false
}

// 监听电话号码变化，实时验证
watch(() => formData.value.reporterPhone, () => {
  validatePhone()
})
</script>

<style scoped>
/* 自定义滚动条 */
::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}
</style> 