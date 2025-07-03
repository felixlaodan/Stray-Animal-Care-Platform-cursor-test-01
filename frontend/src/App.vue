<template>
  <div id="app">
    <Manage />
    <main class="pt-16"> <!-- pt-16 (padding-top: 4rem) to offset the fixed navbar height -->
      <router-view />
    </main>
    </div>
</template>

<script setup>
import Manage from './components/Manage.vue'
</script>

<style scoped>
/* You can add any additional App-level specific styles here */
</style>
