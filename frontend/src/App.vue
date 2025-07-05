<template>
  <div id="app">
    <Manage />
    <main class="pt-16">
      <router-view />
    </main>
  </div>
</template>

<script setup>
import Manage from './components/Manage.vue';
</script>

<style>
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
</style>

<style scoped>
/* You can add any additional App-level specific styles here */
</style>
