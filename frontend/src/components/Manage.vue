<template>
    <nav class="bg-white shadow-md fixed w-full z-50 transition-all duration-300" :class="{'bg-opacity-95': scrollPosition > 50, 'bg-opacity-100': scrollPosition <= 50}">
      <div class="w-full px-4">
        <div class="flex justify-between items-center h-16">

          <!-- Logo&平台主题 -->
          <div class="flex items-center space-x-5">
            <img src="../assets/logo.svg" alt="Logo" class="w-8 h-8">
            <span class="text-xl font-bold text-gray-800 tracking-tight">爪印归途</span>
          </div>

          <!-- 导航栏菜单 -->
          <div class="hidden md:flex items-center space-x-8">
            <router-link to="/" class="nav-link">主页</router-link>

            <!-- 上报流浪 -->
            <div class="relative group">
              <router-link to="/rescue/upload-stray" class="nav-link flex items-center">上报流浪</router-link>
              <div class="absolute left-0 mt-2 w-48 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-300 z-50 transform origin-top scale-95 group-hover:scale-100">
                <div class="py-1" role="menu" aria-orientation="vertical">
                  <router-link to="/rescue/upload-records" class="block px-4 py-2 text-sm text-gray-700 hover:bg-lemon hover:text-white transition-colors duration-200">上报记录</router-link>
                </div>
              </div>
            </div>

            <!-- 终止流浪 -->
            <div class="relative group">
              <router-link to="/rescue/adoption-info" class="nav-link flex items-center">终止流浪</router-link>
              <div class="absolute left-0 mt-2 w-48 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-300 z-50 transform origin-top scale-95 group-hover:scale-100">
                <div class="py-1" role="menu" aria-orientation="vertical">
                  <router-link to="/rescue/adoption-info" class="block px-4 py-2 text-sm text-gray-700 hover:bg-lemon hover:text-white transition-colors duration-200">领养信息</router-link>
                  <!-- TODO: 领养记录页面待创建 -->
                  <!-- <router-link to="/rescue/adoption-records" class="block px-4 py-2 text-sm text-gray-700 hover:bg-lemon hover:text-white transition-colors duration-200">领养记录</router-link> -->
                </div>
              </div>
            </div>

            <!-- TODO: 救助基地页面待创建 -->
            <!-- <router-link to="/rescue-base" class="nav-link flex items-center">救助基地</router-link> -->
            
            <router-link to="/posts" class="nav-link flex items-center">社区论坛</router-link>
            <router-link to="/ai" class="nav-link flex items-center">智能科普</router-link>
          </div>

          <!-- 用户信息和移动端菜单按钮 -->
          <div class="flex items-center space-x-5">
            <!-- 用户信息 (登录/未登录状态) -->
            <div v-if="userStore.isAuthenticated" class="hidden md:flex items-center space-x-4">
              <span class="font-medium text-gray-700">欢迎, {{ userStore.user?.nickname || '用户' }}</span>
              <button @click="handleLogout" class="nav-link !bg-red-500 !text-white">注销</button>
            </div>
            <div v-else class="hidden md:flex items-center space-x-4">
              <router-link to="/login" class="nav-link">登录</router-link>
              <router-link to="/register" class="nav-link !bg-lemon !text-gray-900">注册</router-link>
            </div>
            
            <!-- 移动端菜单按钮 (可选) -->
            <div class="md:hidden">
              <button @click="isMobileMenuOpen = !isMobileMenuOpen" class="text-gray-800 focus:outline-none">
                <i class="fas fa-bars text-xl"></i>
              </button>
            </div>
          </div>
        </div>
        
        <!-- 移动端下拉菜单 -->
        <div v-if="isMobileMenuOpen" class="md:hidden mt-2 space-y-2">
          <router-link to="/" class="block nav-link">主页</router-link>
          <router-link to="/rescue/upload-stray" class="block nav-link">上报流浪</router-link>
          <router-link to="/rescue/adoption-info" class="block nav-link">终止流浪</router-link>
           <router-link to="/posts" class="block nav-link">社区论坛</router-link>
           <div v-if="!userStore.isAuthenticated" class="border-t pt-2 space-y-2">
              <router-link to="/login" class="block nav-link">登录</router-link>
              <router-link to="/register" class="block nav-link">注册</router-link>
           </div>
           <div v-else class="border-t pt-2">
              <button @click="handleLogout" class="w-full text-left block nav-link !bg-red-500 !text-white">注销</button>
           </div>
        </div>

      </div>
    </nav>
</template>
  
<script setup>
import { onMounted, onBeforeUnmount, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user';
import { ElMessage } from 'element-plus';

const scrollPosition = ref(0);
const isMobileMenuOpen = ref(false); // 控制移动端菜单
const userStore = useUserStore();
const router = useRouter();

const handleScroll = () => {
  scrollPosition.value = window.scrollY;
};

const handleLogout = () => {
  userStore.logout();
  ElMessage.success('您已成功注销');
  router.push('/'); // 注销后返回首页
};

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onBeforeUnmount(() => {
  window.removeEventListener('scroll', handleScroll);
});
</script>
  
<style scoped>
/* 用户信息样式 */
.user-info-text {
  max-width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/* 导航链接基础样式 */
.nav-link {
  @apply px-3 py-2 rounded-md text-base font-medium text-gray-700 hover:bg-lemon hover:text-white transition-colors duration-200;
}

/* 当前激活的导航链接 */
.router-link-exact-active {
  @apply bg-lemon text-white font-semibold;
}

/* 滚动时导航栏样式变化 */
@media (min-width: 768px) {
  .nav-scrolled {
    @apply shadow-lg bg-opacity-95;
  }
}

nav {
  top: 0;
  z-index: 999;
}

/* 下拉菜单动画 */
.group-hover\\:scale-100 { transform: scale(1); }
.scale-95 { transform: scale(0.95); }
.opacity-0 { opacity: 0; }
.group-hover\\:opacity-100 { opacity: 1; }
.invisible { visibility: hidden; }
.group-hover\\:visible { visibility: visible; }
.transition-all { transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1); }
</style> 