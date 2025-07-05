<template>
  <div class="admin-layout">
    <el-container style="height: 100vh;">
      <el-aside width="200px" class="admin-aside">
        <div class="logo">
          欢迎您，管理员！
        </div>
        <el-menu
          :default-active="activeMenu"
          class="el-menu-vertical-demo"
          router
        >
          <el-menu-item index="/admin/upload-management">
            <el-icon><document /></el-icon>
            <span>流浪动物管理</span>
          </el-menu-item>
          <el-menu-item index="/admin/user-management">
            <el-icon><user /></el-icon>
            <span>用户管理</span>
          </el-menu-item>
          <el-menu-item index="/posts">
            <el-icon><chat-dot-square /></el-icon>
            <span>论坛管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main class="admin-main">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user';
import { Document, User, Finished, ChatDotSquare } from '@element-plus/icons-vue';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

const activeMenu = computed(() => route.path);

const logout = () => {
  userStore.logout();
  router.push('/login');
};
</script>

<style scoped>
.admin-layout {
  background-color: #f5f7fa;
}
.admin-aside {
  background-color: #ffffff;
  border-right: 1px solid #e6e6e6;
}
.logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  font-weight: bold;
  color: #303133;
  border-bottom: 1px solid #e6e6e6;
}
.el-menu {
  border-right: none;
}
.admin-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  border-bottom: 1px solid #e6e6e6;
}
.admin-main {
  padding: 20px;
}

</style>
