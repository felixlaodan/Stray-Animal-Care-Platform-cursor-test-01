<template>
  <div class="animal-card border rounded-lg overflow-hidden shadow-lg transition-transform transform hover:-translate-y-1 hover:shadow-xl bg-white">
    <div class="relative">
      <img :src="'http://localhost:8080' + animal.coverImageUrl" alt="Animal Image" class="w-full h-48 object-cover">
      <div v-if="animal.adoptionStatus === 'ADOPTED'" class="absolute inset-0 bg-black bg-opacity-50 flex items-center justify-center">
        <span class="text-lemon text-2xl font-bold px-4 py-2 rounded-lg">已领养</span>
      </div>
    </div>
    <div class="p-4">
      <h3 class="text-xl font-bold mb-2">{{ animal.name }}</h3>
      <div class="flex items-center text-sm text-gray-600 mb-4">
        <span class="mr-4"><i class="fas fa-venus-mars mr-1"></i> {{ animal.gender }}</span>
        <span><i class="fas fa-paw mr-1"></i> {{ animal.species }}</span>
      </div>
      <p class="text-gray-700 text-sm h-10 overflow-hidden text-ellipsis">
        {{ animal.description || '暂无更多描述...' }}
      </p>
    </div>
    <div class="p-4 bg-gray-50 border-t flex justify-end space-x-2">
      <el-button type="white" @click="$emit('view-detail', animal.id)" plain>详细信息</el-button>
      <el-button
        type="lemon"
        @click="$emit('adopt', animal)"
        :disabled="animal.adoptionStatus === 'ADOPTED'"
      >
        我要领养
      </el-button>
    </div>
  </div>
</template>

<script setup>
defineProps({
  animal: {
    type: Object,
    required: true
  }
});

defineEmits(['view-detail', 'adopt']);
</script>

<style scoped>
.animal-card {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>