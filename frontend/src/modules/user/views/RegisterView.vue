<template>
  <div class="register-container">
    <h2>注册</h2>
    <el-form :model="form" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" :loading="loading" class="lemon-btn">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { register } from '../api.js';

const router = useRouter();
const loading = ref(false);

const form = reactive({
  username: '',
  nickname: '',
  password: '',
});

const onSubmit = async () => {
  if (!form.username || !form.nickname || !form.password) {
    ElMessage.warning('请填写所有必填项');
    return;
  }
  loading.value = true;
  try {
    const res = await register({
      username: form.username,
      nickname: form.nickname,
      password: form.password,
    });

    if (res.code === 200) {
      ElMessage.success('注册成功！正在跳转到登录页...');
      // 注册成功后，跳转到登录页
      setTimeout(() => router.push('/login'), 1500);
    } else {
      ElMessage.error(res.message || '注册失败');
    }
  } catch (error) {
    console.error('注册请求失败', error);
    ElMessage.error('注册请求失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  background-color: #fff;
}

.register-container h2 {
  color: #333;
  text-align: center;
  margin-bottom: 30px;
}

.lemon-btn {
  text-align: center;
  background-color: rgb(252,211, 55) !important;
  border-color: rgb(252,211, 55) !important;
}

/* 覆盖 Element Plus 的默认样式，确保文字清晰可见 */
.register-container :deep(.el-form-item__label) {
  color: #333 !important;
  font-weight: 500;
}

.register-container :deep(.el-input__inner) {
  color: #333 !important;
}

.register-container :deep(.el-input__inner::placeholder) {
  color: #999 !important;
}
</style>
