<template>
  <div class="relative w-full h-screen bg-gradient-to-br from-gray-900 to-black overflow-hidden">
    <!-- 标题 -->
    <div class="absolute top-8 left-1/2 transform -translate-x-1/2 text-white text-center z-20">
      <h1 class="text-[clamp(1.5rem,4vw,3rem)] font-bold tracking-wider mb-2">团队成员</h1>
      <p class="text-gray-400">感谢每一位成员为本平台搭建做出的努力！</p>
    </div>

    <!-- 太阳系模型容器 -->
    <div class="relative w-full h-full flex items-center justify-center pt-1">
      <!-- 太阳(中心) -->
      <div class="absolute w-24 h-24 rounded-full bg-yellow-400 shadow-[0_0_60px_30px_rgba(250,204,21,0.6)] animate-pulse"></div>

      <!-- 团队成员小行星 -->
      <div
        v-for="(member, index) in teamMembers"
        :key="member.id"
        :style="{
          '--orbit-radius': `${120 + index * 60}px`,
          '--orbit-duration': `${30 + index * 10}s`,
          '--planet-color': getPlanetColor(index),
          '--orbit-index': index
        }"
        class="planet-orbit"
      >
        <div
          class="planet cursor-pointer"
          :style="{ backgroundImage: `url(${member.avatar})` }"
          @mouseenter="handleMouseEnter(member, $event)"
          @mouseleave="handleMouseLeave($event)"
        >
          <div class="planet-tooltip">
            <div class="font-bold">{{ member.name }}</div>
            <div class="text-xs text-gray-300">{{ member.position }}</div>
          </div>
        </div>
      </div>
    </div>

    <!-- 成员详情浮层 -->
    <div
      v-if="activeMember"
      class="fixed inset-0 pointer-events-none z-30"
      @mousemove="trackMouse"
    >
      <div
        ref="detailCard"
        :style="{
          left: `${mouseX}px`,
          top: `${mouseY - detailCardHeight}px`
        }"
        class="absolute bg-white/70 rounded-xl shadow-2xl p-5 w-72 transition-all duration-300 opacity-0 pointer-events-auto transform -translate-x-1/2"
        :class="{ 'opacity-100': showDetail }"
        @mouseenter="keepDetailVisible"
        @mouseleave="hideDetail"
      >
        <div class="flex flex-col items-center">
          <img
            :src="activeMember.avatar"
            class="w-24 h-24 rounded-full object-cover border-4 border-white shadow-lg -mt-12 mb-3"
          >
          <h3 class="font-bold text-xl text-gray-800 mb-1">{{ activeMember.name }}</h3>
          <div class="flex flex-wrap justify-center gap-2 mb-4">
            <span v-for="(skill, index) in activeMember.skills" :key="index" class="px-2.5 py-0.5 bg-gray-100 rounded-full text-xs text-gray-700">
              {{ skill }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import dyxImg from '@/assets/images/Team/dyx.jpg'
import hsyImg from '@/assets/images/Team/hsy.jpg'
import hykImg from '@/assets/images/Team/hyk.jpg'
import jfwImg from '@/assets/images/Team/jfw.jpg'
import ljhImg from '@/assets/images/Team/ljh.jpg'

export default {
  name: 'TeamPage',
  data() {
    return {
      teamMembers: [
        {
          id: 1,
          name: '董宜旭',
          avatar: dyxImg,
          skills: ['后端开发', '项目整合', '数据库设计'],
          position: 'dong_yi_xu@163.com',
        },
        {
          id: 2,
          name: '黄思雲',
          avatar: hsyImg,
          skills: ['功能测试', '文档撰写', '绘制图像'],
          position: '601871858@qq.com',
        },
        {
          id: 3,
          name: '何奕可',
          avatar: hykImg,
          skills: ['前端开发', '数据库设计', '素材收集'],
          position: 'hoico@qq.com',
        },
        {
          id: 4,
          name: '贾馥玮',
          avatar: jfwImg,
          skills: ['安全测试', '文档撰写', '素材收集'],
          position: 'jfw040703@qq.com',
        },
        {
          id: 5,
          name: '李俊宏',
          avatar: ljhImg,
          skills: ['AI接口', 'EChart展示', '文档撰写'],
          position: '234634150@qq.com',
        }
      ],
      activeMember: null,
      mouseX: 0,
      mouseY: 0,
      showDetail: false,
      detailCardHeight: 280, // 详情卡片高度
      isDetailHovered: false,
      hoverTimer: null,
      detailCard: null
    }
  },
  mounted() {
    window.addEventListener('mousemove', this.trackMouse);
    this.detailCard = this.$refs.detailCard;
  },
  beforeUnmount() {
    window.removeEventListener('mousemove', this.trackMouse);
  },
  methods: {
    handleMouseEnter(member, event) {
      event.stopPropagation();
      clearTimeout(this.hoverTimer);
      this.activeMember = member;
      this.showDetail = true; // 立即显示详情
    },
    handleMouseLeave(event) {
      event.stopPropagation();
      if (!this.isDetailHovered) {
        clearTimeout(this.hoverTimer);
        this.showDetail = false;
        this.activeMember = null; // 立即隐藏详情
      }
    },
    keepDetailVisible() {
      this.isDetailHovered = true;
      this.showDetail = true;
    },
    hideDetail() {
      this.isDetailHovered = false;
      this.showDetail = false;
      this.activeMember = null; // 立即隐藏详情
    },
    getPlanetColor(index) {
      const colors = [
        'rgba(255, 99, 71, 0.8)',    // tomato
        'rgba(135, 206, 250, 0.8)',  // lightskyblue
        'rgba(152, 251, 152, 0.8)',  // palegreen
        'rgba(255, 215, 0, 0.8)',    // gold
        'rgba(218, 112, 214, 0.8)'   // orchid
      ];
      return colors[index % colors.length];
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString('en-US', { month: 'long', year: 'numeric' });
    },
    trackMouse(e) {
      // 防止详情框超出视口
      const popupWidth = 288;
      const popupHeight = this.detailCardHeight;
      const maxX = window.innerWidth - popupWidth;
      const minX = 0;
      const maxY = window.innerHeight;
      const minY = popupHeight;

      this.mouseX = Math.min(Math.max(e.clientX - popupWidth / 2, minX), maxX);
      this.mouseY = Math.min(Math.max(e.clientY, minY), maxY);
    }
  }
}
</script>

<style scoped>
/* 星星背景效果 */
.bg-gradient-to-br::before {
  content: '';
  position: absolute;
  inset: 0;
  background-image: radial-gradient(circle, white 1px, transparent 1px);
  background-size: 60px 60px;
  animation: twinkle 8s infinite ease-in-out;
}

@keyframes twinkle {
  0%, 100% { opacity: 0.2; }
  50% { opacity: 0.6; }
}

/* 小行星轨道 */
.planet-orbit {
  position: absolute;
  width: calc(var(--orbit-radius) * 2);
  height: calc(var(--orbit-radius) * 2);
  border: 1px solid rgba(255, 255, 255, 0.08);
  border-radius: 50%;
  animation: orbit var(--orbit-duration) linear infinite;
  pointer-events: none;
}

/* 行星 */
.planet {
  position: absolute;
  top: 50%;
  left: 100%;
  transform: translate(-50%, -50%);
  width: 48px;
  height: 48px;
  border-radius: 50%;
  cursor: pointer;
  transition: all 0.3s ease;
  background-size: cover;
  background-position: center;
  box-shadow: 0 0 12px var(--planet-color);
  z-index: calc(10 + var(--orbit-index));
  pointer-events: auto;
}

.planet:hover {
  transform: translateX(-50%) scale(1.4);
  box-shadow: 0 0 25px var(--planet-color);
  z-index: 30;
}

/* 行星悬停提示 */
.planet-tooltip {
  position: absolute;
  bottom: 120%;
  left: 50%;
  transform: translateX(-50%);
  background-color: rgba(0, 0, 0, 0.85);
  color: white;
  padding: 6px 12px;
  border-radius: 6px;
  font-size: 12px;
  white-space: nowrap;
  opacity: 0;
  visibility: hidden;
  transition: opacity 0.3s, visibility 0.3s;
  z-index: 40;
}

.planet:hover .planet-tooltip {
  opacity: 1;
  visibility: visible;
}

/* 轨道动画 */
@keyframes orbit {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

/* 详情卡片 */
.absolute {
  transform-origin: bottom center;
  z-index: 50;
}
</style>
