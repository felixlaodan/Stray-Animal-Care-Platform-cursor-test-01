<template>
  <div class="min-h-screen bg-gray-50 text-gray-800 font-sans">
    <!-- 英雄区 -->
    <header class="relative overflow-hidden bg-lemon-500 py-16 md:py-24">
      <div class="container mx-auto px-4 relative z-10">
        <h1 class="text-[clamp(2rem,5vw,3.5rem)] font-bold text-center text-white mb-4 animate-fadeIn">
          关于它的科普
        </h1>
        <p class="text-[clamp(1rem,2vw,1.25rem)] text-center text-white max-w-3xl mx-auto mb-8">
          了解如何救助流浪动物，以及救助后的那些知识
        </p>
        <div class="flex justify-center mt-12">
          <a href="#status" class="bg-lemon/70 hover:bg-lemon text-white font-bold py-3 px-8 rounded-full shadow-lg hover:shadow-xl transform hover:-translate-y-1 transition-all duration-300">
            了解更多 <i class="fas fa-paw ml-2"></i>
          </a>
        </div>
      </div>
      <div class="absolute inset-0 opacity-100">
        <img src="@/assets/images/AI/hero.jpg" alt="流浪动物" class="w-full h-full object-cover">
      </div>
    </header>

 <!-- 主要内容区 -->
 <main class="container mx-auto px-4 py-12 md:py-16" id="status">
      <!-- 流浪动物现状 -->
      <section class="mb-16 md:mb-24">
        <div class="flex flex-col md:flex-row items-center gap-8">
          <div class="md:w-1/2">
            <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-yellow-600 mb-6">
              流浪动物的现状
            </h2>
            <p class="text-lg mb-4 leading-relaxed">
            截止2025年初，中国培森动保公益研究院的数据表明，全球范围内流浪动物的数量已超过2亿只，仅在中国就有超过4000万只流浪猫狗。<br>这些动物大多生活在城市的角落、废弃建筑或街头巷尾，面临着食物短缺、疾病威胁和人为伤害等多重挑战。
            </p>
            <p class="text-lg mb-6 leading-relaxed">
              流浪动物的繁殖速度惊人，一对流浪猫在7年内理论上可以繁殖出37万只后代，流浪狗则可繁殖出6万只后代。<br>这种快速繁殖导致流浪动物数量持续增长，形成恶性循环。
            </p>
            <div class="bg-yellow-50 p-4 rounded-lg">
              <h3 class="font-bold text-yellow-700 mb-2">关键数据</h3>
              <ul class="space-y-2">
                <li class="flex items-start">
                  <span class="text-yellow-600 font-bold mr-2">•</span>
                  <span>全球流浪动物数量：超过2亿只</span>
                </li>
                <li class="flex items-start">
                  <span class="text-yellow-600 font-bold mr-2">•</span>
                  <span>中国流浪猫狗数量：超过4000万只</span>
                </li>
                <li class="flex items-start">
                  <span class="text-yellow-600 font-bold mr-2">•</span>
                  <span>流浪动物平均寿命：3-5年（家养动物为12-15年）</span>
                </li>
              </ul>
            </div>
          </div>
          <div class="md:w-1/2 grid grid-cols-2 gap-4">
            <img src="https://picsum.photos/id/1025/600/400" alt="流浪猫" class="rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
            <img src="https://picsum.photos/id/1027/600/400" alt="流浪狗" class="rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
            <img src="https://picsum.photos/id/1062/600/400" alt="流浪动物生活环境" class="rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
            <img src="https://picsum.photos/id/1074/600/400" alt="流浪动物救助" class="rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
          </div>
        </div>
      </section>

      <!-- 流浪动物产生的原因 -->
      <section class="mb-16 md:mb-24 bg-yellow-50 rounded-2xl p-8 md:p-12">
        <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-yellow-600 mb-8 text-center">
          流浪动物产生的原因
        </h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300">
            <div class="w-16 h-16 bg-yellow-100 rounded-full flex items-center justify-center mb-4">
              <i class="fas fa-paw text-2xl text-yellow-500"></i>
            </div>
            <h3 class="text-xl font-bold mb-3 text-yellow-700">不负责任的遗弃</h3>
            <p class="text-gray-700 leading-relaxed">
              这是流浪动物产生的最主要原因。许多人因宠物生病、怀孕、搬家或单纯失去兴趣而将其遗弃，使其沦为流浪动物。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300">
            <div class="w-16 h-16 bg-yellow-100 rounded-full flex items-center justify-center mb-4">
              <i class="fas fa-users text-2xl text-yellow-500"></i>
            </div>
            <h3 class="text-xl font-bold mb-3 text-yellow-700">缺乏科学饲养意识</h3>
            <p class="text-gray-700 leading-relaxed">
              许多宠物主人不了解绝育的重要性，导致宠物意外繁殖，多余的幼崽被随意丢弃或放任自流。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300">
            <div class="w-16 h-16 bg-yellow-100 rounded-full flex items-center justify-center mb-4">
              <i class="fas fa-building text-2xl text-yellow-500"></i>
            </div>
            <h3 class="text-xl font-bold mb-3 text-yellow-700">城市规划与管理不足</h3>
            <p class="text-gray-700 leading-relaxed">
              部分城市缺乏完善的宠物管理法规和流浪动物收容机制，导致流浪动物问题无法得到有效解决。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300">
            <div class="w-16 h-16 bg-yellow-100 rounded-full flex items-center justify-center mb-4">
              <i class="fas fa-heart-broken text-2xl text-yellow-500"></i>
            </div>
            <h3 class="text-xl font-bold mb-3 text-yellow-700">社会认知与偏见</h3>
            <p class="text-gray-700 leading-relaxed">
              一些人对流浪动物存在误解和恐惧，不仅不提供帮助，反而采取驱赶、伤害等行为，加剧了流浪动物的生存困境。
            </p>
          </div>
        </div>
      </section>

      <!-- 流浪动物面临的挑战 -->
      <section class="mb-16 md:mb-24">
        <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-yellow-600 mb-8 text-center">
          流浪动物面临的挑战
        </h2>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div class="bg-white rounded-lg overflow-hidden shadow-md hover:shadow-xl transition-all duration-300 group">
            <img src="https://picsum.photos/id/1059/600/400" alt="食物短缺" class="w-full h-48 object-cover group-hover:scale-105 transition-transform duration-500">
            <div class="p-6">
              <h3 class="text-xl font-bold mb-3 text-yellow-700">食物与水源短缺</h3>
              <p class="text-gray-700 leading-relaxed">
                流浪动物每天都需要为寻找食物和干净的水源而奔波，垃圾桶是它们主要的食物来源，常常食不果腹。
              </p>
            </div>
          </div>
          <div class="bg-white rounded-lg overflow-hidden shadow-md hover:shadow-xl transition-all duration-300 group">
            <img src="https://picsum.photos/id/1082/600/400" alt="疾病威胁" class="w-full h-48 object-cover group-hover:scale-105 transition-transform duration-500">
            <div class="p-6">
              <h3 class="text-xl font-bold mb-3 text-yellow-700">疾病与伤害威胁</h3>
              <p class="text-gray-700 leading-relaxed">
                流浪动物容易感染各种传染病，如犬瘟、猫瘟、狂犬病等，同时也面临着交通事故、人为伤害等风险。
              </p>
            </div>
          </div>
          <div class="bg-white rounded-lg overflow-hidden shadow-md hover:shadow-xl transition-all duration-300 group">
            <img src="https://picsum.photos/id/1060/600/400" alt="恶劣环境" class="w-full h-48 object-cover group-hover:scale-105 transition-transform duration-500">
            <div class="p-6">
              <h3 class="text-xl font-bold mb-3 text-yellow-700">恶劣的生存环境</h3>
              <p class="text-gray-700 leading-relaxed">
                无论是严寒酷暑还是风雨交加，流浪动物都只能在露天环境中寻找庇护所，生存条件极其恶劣。
              </p>
            </div>
          </div>
        </div>
      </section>

      <!-- 流浪动物救助科普 -->
      <section class="mb-16 md:mb-24 bg-yellow-50 rounded-2xl p-8 md:p-12">
        <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-yellow-600 mb-8 text-center">
          流浪动物救助科普
        </h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6 items-start">
          <div class="grid grid-cols-1 gap-6">
            <div class="tutu">
              <img src="https://picsum.photos/id/1071/600/400" alt="TNR救助方法" class="tutu rounded-lg shadow-lg w-full">
            </div>
            <div class="bg-white p-6 rounded-lg shadow-md">
              <h3 class="text-xl font-bold mb-3 text-yellow-700 flex items-center">
                <i class="fas fa-hand-holding-heart text-yellow-500 mr-2"></i>
                如何安全接近流浪动物
              </h3>
              <ol class="list-decimal pl-5 space-y-2 text-gray-700">
                <li>保持安静，缓慢接近，避免突然动作</li>
                <li>不要直视动物的眼睛，这会被视为挑衅</li>
                <li>伸出手让动物先嗅闻，建立初步信任</li>
                <li>如果动物表现出紧张或攻击性，立即停止接近</li>
              </ol>
            </div>
            <div class="bg-white p-6 rounded-lg shadow-md">
              <h3 class="text-xl font-bold mb-3 text-yellow-700 flex items-center">
                <i class="fas fa-first-aid text-yellow-500 mr-2"></i>
                发现受伤流浪动物怎么办
              </h3>
              <ol class="list-decimal pl-5 space-y-2 text-gray-700">
                <li>首先确保自身安全，避免被动物咬伤</li>
                <li>用毛毯或厚毛巾轻轻包裹动物，减少其应激反应</li>
                <li>尽快联系当地动物保护组织或宠物医院</li>
                <li>在专业人员到来前，提供干净的水和安静的环境</li>
              </ol>
            </div>
          </div>
          <div class="grid grid-cols-1 gap-6">
            <div class="bg-white p-6 rounded-lg shadow-md">
              <h3 class="font-bold text-yellow-700 mb-3">科学救助的核心原则 - TNR</h3>
              <p class="space-y-2 text-gray-700">
                TNR（Trap-Neuter-Return）即捕捉-绝育-放归，是目前国际上公认的控制流浪动物数量的科学方法。通过对流浪动物进行绝育，可以有效减少其繁殖数量，同时保留它们的自然生活状态。
              </p>
            </div>
            <div class="bg-white p-6 rounded-lg shadow-md">
              <h4 class="font-bold text-yellow-700 mb-3">TNR的优势</h4>
              <ul class="space-y-2 text-gray-700">
                <li class="flex items-start">
                  <span class="text-yellow-600 font-bold mr-2">•</span>
                  <span>控制流浪动物数量，从根本上解决问题</span>
                </li>
                <li class="flex items-start">
                  <span class="text-yellow-600 font-bold mr-2">•</span>
                  <span>减少流浪动物因发情引发的争斗和叫声</span>
                </li>
                <li class="flex items-start">
                  <span class="text-yellow-600 font-bold mr-2">•</span>
                  <span>降低流浪动物感染和传播疾病的风险</span>
                </li>
                <li class="flex items-start">
                  <span class="text-yellow-600 font-bold mr-2">•</span>
                  <span>延长流浪动物的寿命，改善其生活质量</span>
                </li>
              </ul>
            </div>
            <div>
              <img src="https://picsum.photos/id/1071/600/400" alt="TNR救助方法" class="rounded-lg shadow-lg w-full">
            </div>
          </div>
        </div>
      </section>

      <!-- 如何参与救助 -->
      <section class="mb-16 md:mb-24">
        <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-yellow-600 mb-8 text-center">
          每个人都可以参与的救助行动
        </h2>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300 border-l-4 border-yellow-500">
            <h3 class="text-xl font-bold mb-3 text-yellow-700">领养代替购买</h3>
            <p class="text-gray-700 leading-relaxed">
              选择从动物收容所或救助站领养宠物，给流浪动物一个温暖的家，同时也减少对宠物繁殖产业的需求。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300 border-l-4 border-yellow-500">
            <h3 class="text-xl font-bold mb-3 text-yellow-700">提供食物和水</h3>
            <p class="text-gray-700 leading-relaxed">
              在力所能及的范围内，为流浪动物提供干净的食物和水，特别是在寒冷的冬天和炎热的夏天。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300 border-l-4 border-yellow-500">
            <h3 class="text-xl font-bold mb-3 text-yellow-700">帮助绝育和免疫</h3>
            <p class="text-gray-700 leading-relaxed">
              支持或参与流浪动物的绝育和免疫计划，帮助控制数量并提高它们的健康水平。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300 border-l-4 border-yellow-500">
            <h3 class="text-xl font-bold mb-3 text-yellow-700">提供临时庇护</h3>
            <p class="text-gray-700 leading-relaxed">
              在恶劣天气条件下，为流浪动物提供临时的避雨或保暖场所，如纸箱、宠物窝等。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300 border-l-4 border-yellow-500">
            <h3 class="text-xl font-bold mb-3 text-yellow-700">传播正确观念</h3>
            <p class="text-gray-700 leading-relaxed">
              通过社交媒体、线下活动等方式，传播正确的流浪动物观念，反对遗弃和虐待行为。
            </p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300 border-l-4 border-yellow-500">
            <h3 class="text-xl font-bold mb-3 text-yellow-700">支持救助机构</h3>
            <p class="text-gray-700 leading-relaxed">
              向合法的动物救助机构捐赠物资或资金，支持他们的救助工作和收容所运营。
            </p>
          </div>
        </div>
      </section>

      <!-- 救助故事 -->
      <section class="mb-16 md:mb-24">
        <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-yellow-600 mb-8 text-center">
          温暖的救助故事
        </h2>
        <div class="bg-white rounded-2xl shadow-lg overflow-hidden">
          <div class="md:flex">
            <div class="md:w-1/2">
              <img src="https://picsum.photos/id/1069/800/600" alt="救助故事" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-8 md:p-12">
              <h3 class="text-2xl font-bold mb-4 text-yellow-700">从街头流浪到家庭温暖：小花的故事</h3>
              <p class="text-gray-700 mb-6 leading-relaxed">
                小花是一只在街头流浪了半年多的橘猫，被发现时骨瘦如柴，身上长满了跳蚤和皮肤病。志愿者小李发现它后，立即带它去了宠物医院。经过检查，小花不仅营养不良，还患有严重的呼吸道感染。
              </p>
              <p class="text-gray-700 mb-6 leading-relaxed">
                在接下来的一个月里，小李每天给小花喂食、擦药、陪伴它。小花从最初的警惕害怕，到后来逐渐信任小李，开始主动亲近她。经过精心照料，小花的身体逐渐康复，变得活泼可爱。
              </p>
              <p class="text-gray-700 mb-6 leading-relaxed">
                康复后，小李为小花找到了一个充满爱的领养家庭。现在的小花每天都生活在温暖的家中，有了自己的猫窝和玩具，再也不用在街头忍受风吹雨打。这是一个普通却温暖的救助故事，这样的故事每天都在世界各地上演。
              </p>
              <div class="mt-6 text-yellow-600 font-bold">
                <i class="fas fa-quote-left mr-2"></i>
                每一个生命都值得被尊重，每一次救助都可能改变一个生命的轨迹
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- 呼吁行动 -->
      <section class="relative text-white py-12 md:py-16 rounded-2xl overflow-hidden">
        <img src="@/assets/images/AI/appeal.jpg" alt="流浪动物" class="absolute inset-0 w-full h-full object-cover z-0">
        <div class="absolute inset-0 bg-lemon/40 z-10"></div>
        <div class="container mx-auto px-4 text-center relative z-20">
          <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold mb-6">
            让我们一起帮助流浪动物“爪印归途”
          </h2>
          <p class="text-lg md:text-xl max-w-2xl mx-auto mb-8">
            一个微小的善举，能给一个生命带来天翻地覆的改变。<br>从今天起，用我们的爱心和行动，帮助更多的流浪动物“爪印归途”。
          </p>
          <div class="flex flex-col sm:flex-row justify-center gap-4">
            <router-link to="/posts" class="bg-white text-yellow-600 font-bold py-3 px-8 rounded-lg hover:bg-gray-100 transition-colors duration-300 transform hover:-translate-y-1">
              呼吁更多人加入
            </router-link>
            <router-link to="/team" class="bg-transparent border-2 border-white text-white font-bold py-3 px-8 rounded-lg hover:bg-white/10 transition-colors duration-300 transform hover:-translate-y-1">
              了解平台搭建者
            </router-link>
          </div>
        </div>
      </section>
    </main>

    <!-- 页脚 -->

  </div>
</template>

<script>
export default {
  name: 'StrayAnimalScience',
  data() {
    return {
      // 页面数据
    }
  },
  mounted() {
    // 页面加载完成后的动画效果
    this.addAnimationClasses();
  },
  methods: {
    addAnimationClasses() {
      // 为元素添加进入动画类
      const elements = document.querySelectorAll('section, .bg-yellow-600');
      elements.forEach((el, index) => {
        setTimeout(() => {
          el.classList.add('animate-fadeIn');
        }, 100 * index);
      });
    }
  }
}
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css');
/* 自定义动画 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-fadeIn {
  animation: fadeIn 0.8s ease-out forwards;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-cols-1 md\\:grid-cols-2,
  .grid-cols-1 md\\:grid-cols-3 {
    grid-template-columns: 1fr;
  }
}

.tutu{
  height: 320px;
}

</style>

