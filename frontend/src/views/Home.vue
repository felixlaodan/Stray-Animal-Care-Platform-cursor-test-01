<template>
  <div class="font-sans text-gray-800 bg-gray-50">
    <!-- 轮播图区域 -->
    <div class="relative h-[80vh] overflow-hidden">
      <div class="carousel-container flex transition-transform duration-500 ease-in-out" :style="{ transform: `translateX(-${currentSlide * 100}%)` }">
        <!-- 轮播图1 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide1.jpg" alt="流浪狗狗等待救助" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">给予它们一个温暖的家</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">每只流浪动物都有自己的故事，您的爱心可以改变它们的命运</p>
            <button class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              立即救助
            </button>
          </div>
        </div>

        <!-- 轮播图2 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide2.png" alt="流浪猫咪等待领养" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">领养代替购买</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">每一个生命都值得被尊重和爱护，领养一只流浪动物，给它们一个新的开始</p>
            <button class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              我要领养
            </button>
          </div>
        </div>

        <!-- 轮播图3 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide3.jpg" alt="志愿者照顾流浪动物" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">加入我们的志愿者团队</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">即使是最小的善举，也能带来巨大的改变。成为志愿者，用爱和行动帮助这些无助的生命</p>
            <button class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              成为志愿者
            </button>
          </div>
        </div>

        <!-- 轮播图4 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide4.jpg" alt="捐赠帮助流浪动物" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">您的捐赠，它们的希望</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">每一笔捐款都能帮助我们为流浪动物提供食物、医疗和庇护所，让它们感受到温暖和关怀</p>
            <button class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              立即捐赠
            </button>
          </div>
        </div>
      </div>

      <!-- 轮播指示器 -->
      <div class="absolute bottom-6 left-0 right-0 flex justify-center space-x-2 z-30">
        <button v-for="index in 4" :key="index" :class="{ 'w-3 h-3 rounded-full bg-[rgb(252,211,55)]': index - 1 === currentSlide, 'w-3 h-3 rounded-full bg-white/50 hover:bg-white/80 transition-colors': index - 1 !== currentSlide }" @click="currentSlide = index - 1"></button>
      </div>

      <!-- 轮播控制按钮 -->
      <button @click="prevSlide" class="absolute left-4 top-1/2 -translate-y-1/2 w-10 h-10 rounded-full bg-white/20 hover:bg-white/40 flex items-center justify-center z-30 transition-all duration-300">
        <i class="fa fa-chevron-left text-white"></i>
      </button>
      <button @click="nextSlide" class="absolute right-4 top-1/2 -translate-y-1/2 w-10 h-10 rounded-full bg-white/20 hover:bg-white/40 flex items-center justify-center z-30 transition-all duration-300">
        <i class="fa fa-chevron-right text-white"></i>
      </button>
    </div>

    <!-- 平台简介 -->
    <div class="py-16 px-6 max-w-7xl mx-auto">
      <div class="text-center mb-12">
        <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-gray-900 mb-4">关于我们的平台</h2>
        <div class="w-20 h-1 bg-[rgb(252,211,55)] mx-auto mb-6"></div>
        <p class="text-gray-600 max-w-3xl mx-auto text-lg">我们致力于为流浪动物提供帮助，连接救助者、领养者和爱心人士，共同创造一个更美好的世界</p>
      </div>

      <div class="grid md:grid-cols-3 gap-8">
        <!-- 救助服务 -->
        <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-xl transition-all duration-300 transform hover:-translate-y-1">
          <div class="h-48 overflow-hidden">
            <img src="@/assets/images/Home/aboutus1.jpg" alt="流浪动物救助" class="w-full h-full object-cover hover:scale-105 transition-transform duration-500">
          </div>
          <div class="p-6">
            <h3 class="text-xl font-bold text-gray-900 mb-3">流浪动物救助</h3>
            <p class="text-gray-600 mb-4">我们组织志愿者团队，救助街头流浪的小动物，为它们提供临时庇护和基本医疗</p>
            <a href="#" class="inline-flex items-center text-[rgb(252,211,55)] font-medium hover:text-[rgb(252,200,0)] transition-colors">
              了解更多 <i class="fa fa-arrow-right ml-2"></i>
            </a>
          </div>
        </div>

        <!-- 领养服务 -->
        <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-xl transition-all duration-300 transform hover:-translate-y-1">
          <div class="h-48 overflow-hidden">
            <img src="@/assets/images/Home/aboutus2.jpg" alt="宠物领养服务" class="w-full h-full object-cover hover:scale-105 transition-transform duration-500">
          </div>
          <div class="p-6">
            <h3 class="text-xl font-bold text-gray-900 mb-3">宠物领养服务</h3>
            <p class="text-gray-600 mb-4">我们为流浪动物寻找合适的领养家庭，同时提供领养咨询和后续跟进服务</p>
            <a href="#" class="inline-flex items-center text-[rgb(252,211,55)] font-medium hover:text-[rgb(252,200,0)] transition-colors">
              了解更多 <i class="fa fa-arrow-right ml-2"></i>
            </a>
          </div>
        </div>

        <!-- 交流互助 -->
        <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-xl transition-all duration-300 transform hover:-translate-y-1">
          <div class="h-48 overflow-hidden">
            <img src="@/assets/images/Home/aboutus3.jpg" alt="志愿者活动" class="w-full h-full object-cover hover:scale-105 transition-transform duration-500">
          </div>
          <div class="p-6">
            <h3 class="text-xl font-bold text-gray-900 mb-3">志愿者活动</h3>
            <p class="text-gray-600 mb-4">我们欢迎每一位有爱心的人士加入我们的志愿者团队，一起为流浪动物的福利努力</p>
            <a href="#" class="inline-flex items-center text-[rgb(252,211,55)] font-medium hover:text-[rgb(252,200,0)] transition-colors">
              了解更多 <i class="fa fa-arrow-right ml-2"></i>
            </a>
          </div>
        </div>
      </div>
    </div>

      <!-- 呼吁内容 -->
      <div class="bg-white py-16">
        <div class="container mx-auto px-4 sm:px-6 lg:px-8">
          <div class="max-w-4xl mx-auto">
            <h2 class="text-3xl font-bold text-gray-800 mb-8 text-center">我们为什么要救助流浪动物</h2>
            <!-- 统计数据 -->
            <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div class="bg-white rounded-xl shadow-lg p-6 transform transition-all hover:scale-105 hover:shadow-xl">
              <div class="text-4xl font-bold text-lemon mb-2">10万+</div>
              <div class="text-gray-600">流浪动物在城市中艰难生存</div>
            </div>
            <div class="bg-white rounded-xl shadow-lg p-6 transform transition-all hover:scale-105 hover:shadow-xl">
              <div class="text-4xl font-bold text-lemon mb-2">30%</div>
              <div class="text-gray-600">流浪动物患有不同程度伤病</div>
            </div>
            <div class="bg-white rounded-xl shadow-lg p-6 transform transition-all hover:scale-105 hover:shadow-xl">
              <div class="text-4xl font-bold text-lemon mb-2">5000+</div>
              <div class="text-gray-600">通过领养重获新生的生命</div>
            </div>
          </div>
          <br><br><br>
            <div class="space-y-8">
              <div class="flex flex-col md:flex-row items-center gap-8">
                <div class="md:w-1/2">
                  <h3 class="text-2xl font-semibold text-gray-800 mb-4">它们也有生存的权利</h3>
                  <p class="text-gray-600 leading-relaxed">
                    流浪动物并非天生就该流浪，很多是因为人类的遗弃或疏忽而失去了家园。每一个生命都有感受痛苦和快乐的能力，我们有责任给予它们尊重和保护。
                  </p>
                </div>
                <div class="md:w-1/2 rounded-xl overflow-hidden shadow-lg">
                  <img src="@/assets/images/EndStray/1.png" alt="流浪猫在街头寻找食物" class="w-full h-auto object-cover">
                </div>
              </div>

              <div class="flex flex-col md:flex-row-reverse items-center gap-8">
                <div class="md:w-1/2">
                  <h3 class="text-2xl font-semibold text-gray-800 mb-4">它们是城市的一部分</h3>
                  <p class="text-gray-600 leading-relaxed">
                    流浪动物已经成为城市生态的一部分，它们的存在反映了我们社会的文明程度。善待流浪动物，不仅是对生命的尊重，也有助于维护城市生态平衡。
                  </p>
                </div>
                <div class="md:w-1/2 rounded-xl overflow-hidden shadow-lg">
                  <img src="@/assets/images/EndStray/1.png" alt="流浪狗在公园休息" class="w-full h-auto object-cover">
                </div>
              </div>

              <div class="flex flex-col md:flex-row items-center gap-8">
                <div class="md:w-1/2">
                  <h3 class="text-2xl font-semibold text-gray-800 mb-4">领养代替购买</h3>
                  <p class="text-gray-600 leading-relaxed">
                    领养一只流浪动物，不仅能给它们一个温暖的家，还能减少对商业繁殖的需求，从而遏制非法宠物交易。每一个领养行为都是对生命的救赎。
                  </p>
                </div>
                <div class="md:w-1/2 rounded-xl overflow-hidden shadow-lg">
                  <img src="@/assets/images/EndStray/1.png" alt="幸福的领养家庭" class="w-full h-auto object-cover">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>


    <!-- 成功案例 -->
    <div class="py-16 px-6 bg-gray-100">
      <div class="max-w-7xl mx-auto">
        <div class="text-center mb-12">
          <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-gray-900 mb-4">救助成功案例</h2>
          <div class="w-20 h-1 bg-[rgb(252,211,55)] mx-auto mb-6"></div>
          <p class="text-gray-600 max-w-3xl mx-auto text-lg">每一个成功的救助故事都是爱与希望的见证，它们重新找到了幸福的家</p>
        </div>

        <div class="grid md:grid-cols-2 gap-8">
          <!-- 案例1 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example1.png" alt="小黑的故事" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">小黑的新生</h3>
              <p class="text-gray-600 mb-4">小黑是一只被遗弃的流浪狗，浑身是伤地被我们发现。经过两个月的治疗和照顾，现在它已经找到了一个充满爱的新家。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年3月</span>
              </div>
            </div>
          </div>

          <!-- 案例2 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example2.jpg" alt="咪咪的故事" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">咪咪的幸福生活</h3>
              <p class="text-gray-600 mb-4">咪咪是一只被救助的流浪猫，曾经非常胆小怕人。在我们的照顾下，它逐渐恢复了信任，现在已经成为一个活泼可爱的家庭成员。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年5月</span>
              </div>
            </div>
          </div>

          <!-- 案例3 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example1.png" alt="小黑的故事" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">小黑的新生</h3>
              <p class="text-gray-600 mb-4">小黑是一只被遗弃的流浪狗，浑身是伤地被我们发现。经过两个月的治疗和照顾，现在它已经找到了一个充满爱的新家。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年3月</span>
              </div>
            </div>
          </div>

          <!-- 案例4 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example1.png" alt="小黑的故事" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">小黑的新生</h3>
              <p class="text-gray-600 mb-4">小黑是一只被遗弃的流浪狗，浑身是伤地被我们发现。经过两个月的治疗和照顾，现在它已经找到了一个充满爱的新家。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年3月</span>
              </div>
            </div>
          </div>

          <!-- 案例5 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example1.png" alt="小黑的故事" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">小黑的新生</h3>
              <p class="text-gray-600 mb-4">小黑是一只被遗弃的流浪狗，浑身是伤地被我们发现。经过两个月的治疗和照顾，现在它已经找到了一个充满爱的新家。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年3月</span>
              </div>
            </div>
          </div>

          <!-- 案例6 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example1.png" alt="小黑的故事" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">小黑的新生</h3>
              <p class="text-gray-600 mb-4">小黑是一只被遗弃的流浪狗，浑身是伤地被我们发现。经过两个月的治疗和照顾，现在它已经找到了一个充满爱的新家。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年3月</span>
              </div>
            </div>
          </div>

        </div>

        <div class="text-center mt-10">
          <a href="#" class="inline-block bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
            分享我的故事
          </a>
        </div>
      </div>
    </div>



    <!-- 页脚 -->
    <footer class="bg-gray-900 text-white py-12 px-6">
      <div class="max-w-7xl mx-auto">
        <div class="grid md:grid-cols-4 gap-8">
          <div>
            <h3 class="text-lg font-bold mb-4">关于平台</h3>
            <p class="text-gray-400 mb-4">平台致力于流浪动物救助信息交流，希望通过我们的努力，让每一只流浪动物都能得到关爱和保护。</p>
            <div class="flex space-x-4">
              <a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fa fa-weixin"></i>
              </a>
              <a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fa fa-weibo"></i>
              </a>
              <a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fa fa-instagram"></i>
              </a>
              <a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fa fa-youtube-play"></i>
              </a>
            </div>
          </div>

          <div>
            <h3 class="text-lg font-bold mb-4">快速链接</h3>
            <ul class="space-y-2">
              <li><a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">平台建设者</a></li>
              <li><a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">领养信息</a></li>
              <li><a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">交流互动</a></li>
              <li><a href="#" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">智能科普</a></li>
            </ul>
          </div>

          <div>
            <h3 class="text-lg font-bold mb-4">联系方式</h3>
            <ul class="space-y-2">
              <li class="flex items-center">
                <i class="fa fa-map-marker mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">北京市朝阳区建国路88号爱心大厦5层</span>
              </li>
              <li class="flex items-center">
                <i class="fa fa-phone mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">010-12345678</span>
              </li>
              <li class="flex items-center">
                <i class="fa fa-envelope mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">contact@animal-rescue.org</span>
              </li>
              <li class="flex items-center">
                <i class="fa fa-clock-o mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">周一至周日 9:00 - 18:00</span>
              </li>
            </ul>
          </div>

          <div>
            <h3 class="text-lg font-bold mb-4">订阅我们</h3>
            <p class="text-gray-400 mb-4">订阅我们的电子通讯，获取最新的救助信息和活动通知</p>
            <form class="flex">
              <input type="email" placeholder="您的电子邮箱" class="px-4 py-2 rounded-l-lg w-full focus:outline-none text-gray-900">
              <button type="submit" class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 px-4 py-2 rounded-r-lg transition-colors">
                <i class="fa fa-paper-plane"></i>
              </button>
            </form>
          </div>
        </div>

        <div class="border-t border-gray-800 mt-10 pt-6 text-center text-gray-500">
          <p>&copy; 2025 流浪动物救助信息交流平台. 保留所有权利.</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      currentSlide: 0,
      slideInterval: null
    }
  },
  mounted() {
    // 自动轮播
    this.slideInterval = setInterval(() => {
      this.nextSlide()
    }, 5000)
  },
  beforeDestroy() {
    // 清除定时器
    clearInterval(this.slideInterval)
  },
  methods: {
    nextSlide() {
      this.currentSlide = (this.currentSlide + 1) % 4
    },
    prevSlide() {
      this.currentSlide = (this.currentSlide - 1 + 4) % 4
    }
  }
};
// // @ts-ignore
// createAiChat({
//   appId: "1940005408542314497",
//   iconPosition: "bottom-right"
// });
//}
// @ts-ignore
/* createAiChat({
  appId: "1940005408542314497",
  iconPosition: "bottom-right"
}); */
</script>

<style type="text/tailwindcss">
  .carousel-container {
    width: 100%;
    height: 90vh;
  }
</style>
