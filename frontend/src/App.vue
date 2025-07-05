<template>
  <div id="app">
    <Manage />
    <main class="pt-16">
      <router-view />
    </main>
  </div>
</template>

<script setup>
import { watch } from 'vue'
import { useRoute } from 'vue-router'
import Manage from './components/Manage.vue';

const route = useRoute()
watch(() => route.path, () => {
  // 强制组件更新
  document.documentElement.scrollTop = 0
})
</script>

<style>
#app {
  min-height: 100vh;
  /* Global style overrides for ElMessage */
.el-message.el-message--success {
  background-color: #f0f9eb !important;
  border-color: #e1f3d8 !important;
}

.el-message.el-message--success .el-message__content {
  color: #67c23a !important;
}

.el-message.el-message--success .el-message__icon {
  color: #67c23a !important;
}
  background-image: url('@/assets/images/background.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

</style>

<style scoped>
/* You can add any additional App-level specific styles here */
</style>
