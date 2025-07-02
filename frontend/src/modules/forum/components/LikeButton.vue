<template>
  <button class="like-button" @click="onClick" :class="{ 'liked': liked }">
    <span class="like-icon">
      <svg class="heart-icon" viewBox="0 0 24 24">
        <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"></path>
      </svg>
      <span v-for="i in 7" :key="i" :class="`particle particle-${i}`"></span>
    </span>
    <span class="likes-count">{{ likesCount }}</span>
  </button>
</template>

<script setup>
defineProps({
  liked: {
    type: Boolean,
    required: true,
  },
  likesCount: {
    type: Number,
    required: true,
  }
});

const emit = defineEmits(['toggle-like']);

const onClick = () => {
  emit('toggle-like');
};
</script>

<style scoped>
.like-button {
  --particle-color: #ff6f61;
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  background-color: transparent;
  border: none;
  padding: 5px;
  border-radius: 20px;
  transition: background-color 0.3s ease;
  position: relative;
  font-family: inherit;
  font-size: 1em;
  color: #666;
}

.like-button:hover {
  background-color: #f0f2f5;
}

.like-icon {
  position: relative;
  width: 24px;
  height: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.heart-icon {
  width: 24px;
  height: 24px;
  fill: #aaa;
  stroke: #888;
  stroke-width: 1;
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.likes-count {
  font-weight: 500;
  min-width: 10px;
}

/* Liked State */
.like-button.liked .heart-icon {
  fill: var(--particle-color);
  stroke: var(--particle-color);
  transform: scale(1.1);
  animation: heart-pop 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.like-button.liked .likes-count {
  color: var(--particle-color);
}

@keyframes heart-pop {
  0% { transform: scale(1); }
  50% { transform: scale(1.3); }
  100% { transform: scale(1.1); }
}

/* Particles */
.particle {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 4px;
  height: 4px;
  border-radius: 50%;
  background-color: var(--particle-color);
  opacity: 0;
  transform: translate(-50%, -50%);
}

.like-button.liked .particle {
  animation: particle-burst 0.8s ease-out forwards;
}

.particle-1 { animation-delay: 0.05s; }
.particle-2 { transform: translate(-50%, -50%) rotate(51deg); }
.particle-3 { transform: translate(-50%, -50%) rotate(102deg); }
.particle-4 { transform: translate(-50%, -50%) rotate(153deg); }
.particle-5 { transform: translate(-50%, -50%) rotate(204deg); }
.particle-6 { transform: translate(-50%, -50%) rotate(255deg); }
.particle-7 { transform: translate(-50%, -50%) rotate(306deg); }


@keyframes particle-burst {
  0% {
    transform: translate(-50%, -50%) translateY(0) scale(0.5);
    opacity: 0.8;
  }
  100% {
    transform: translate(-50%, -50%) translateY(-70px) scale(0);
    opacity: 0;
  }
}
</style> 