<template>
  <div class="login-container">
    <div class="text-xl">用户登录</div>
    <el-form :model="form" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="form.password" show-password @keyup.enter="onSubmit"></el-input>
      </el-form-item>
      <br><br>
      <el-form-item class="center">
        <el-button type="lemon" @click="onSubmit" :loading="loading">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { useUserStore } from '@/stores/user.js';
import { login } from '../api.js';

const router = useRouter();
const userStore = useUserStore();
const loading = ref(false);

const form = reactive({
  username: '',
  password: '',
});

const onSubmit = async () => {
  if (!form.username || !form.password) {
    ElMessage.warning('请输入用户名和密码');
    return;
  }
  loading.value = true;
  try {
    const res = await login({
      username: form.username,
      password: form.password,
    });

    if (res.code === 200) {
      // 调用 user store 中的 action 来保存状态
      userStore.loginSuccess({
        newToken: res.data.token,
        newUser: res.data.user
      });
      ElMessage.success('登录成功！');

      // 关键修改：根据用户角色进行跳转
      if (userStore.isAdmin) {
        router.push('/admin'); // 管理员跳转到管理页
      } else {
        router.push('/'); // 普通用户跳转到主页
      }

    } else {
      ElMessage.error(res.message || '登录失败');
    }
  } catch (error) {
    console.error('登录请求失败', error);
    ElMessage.error('登录请求失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.login-container {
  width: 500px;
  height: 300px;
  margin: 50px auto;
  padding: 30px;
  border: 1px solid #ebeef5;
  border-radius: 30px;
  background-color: #fff;
  text-align: center;
}

.login-container h2 {
  color: #333;
  text:bold;
  text-align: center;
  margin-bottom: 30px;
}

</style>
