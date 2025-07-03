<template>
    <div class="min-h-screen bg-gray-50">
      <!-- 页面标题 -->
<div class="relative bg-lemon py-24 md:py-32 overflow-hidden">
  <div class="absolute inset-0 bg-gradient-to-r from-lemon/80 to-lemon/40"></div>
  <div class="container mx-auto px-4 sm:px-6 lg:px-8 relative z-10">
    <div class="flex flex-col md:flex-row items-center gap-8 md:gap-12">
      <div class="md:w-2/3">
        <h1 class="text-[clamp(2.5rem,5vw,4rem)] font-bold text-white leading-tight">
          终止流浪 <span class="text-amber-800">保护生命</span>
        </h1>
        <p class="mt-6 text-[clamp(1rem,2vw,1.25rem)] text-gray-800 max-w-2xl">
          每一个生命都值得被温柔以待，让我们一起行动，为流浪动物创造一个温暖的世界
        </p>
      </div>
      <div class="md:w-1/3 rounded-2xl overflow-hidden shadow-2xl transform transition-all duration-700 hover:scale-105">
        <img 
          src="@/assets/images/EndStray/1.png" 
          alt="志愿者救助流浪动物" 
          class="w-full h-auto object-cover transform transition-transform duration-1000 hover:scale-105"
        >
      </div>
    </div>
  </div>
</div>
  
      <!-- 统计数据 -->
      <div class="container mx-auto px-4 sm:px-6 lg:px-8 py-16">
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
      </div>
  
      <!-- 呼吁内容 -->
      <div class="bg-white py-16">
        <div class="container mx-auto px-4 sm:px-6 lg:px-8">
          <div class="max-w-4xl mx-auto">
            <h2 class="text-3xl font-bold text-gray-800 mb-8 text-center">我们为什么要救助流浪动物</h2>
            
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
  
      <!-- 我们能做什么 -->
      <div class="bg-gray-50 py-16">
        <div class="container mx-auto px-4 sm:px-6 lg:px-8">
          <h2 class="text-3xl font-bold text-gray-800 mb-12 text-center">我们能为它们做些什么</h2>
          
          <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            <div class="bg-white rounded-xl shadow-md overflow-hidden transform transition-all hover:shadow-xl">
              <div class="h-48 overflow-hidden">
                <img src="@/assets/images/EndStray/1.png" alt="领养流浪动物" class="w-full h-full object-cover">
              </div>
              <div class="p-6">
                <h3 class="text-xl font-semibold text-gray-800 mb-3">领养代替购买</h3>
                <p class="text-gray-600">
                  给流浪动物一个温暖的家，你将收获一份无条件的爱与陪伴。每一个领养行为都是对生命的尊重。
                </p>
                <router-link to="/rescue/adoption-info" class="mt-4 inline-block bg-lemon hover:bg-lemon/90 text-white font-medium py-2 px-6 rounded-full transition-all transform hover:scale-105">
                  我要领养
                </router-link>
              </div>
            </div>
            
            <div class="bg-white rounded-xl shadow-md overflow-hidden transform transition-all hover:shadow-xl">
              <div class="h-48 overflow-hidden">
                <img src="@/assets/images/EndStray/1.png" alt="捐赠物资" class="w-full h-full object-cover">
              </div>
              <div class="p-6">
                <h3 class="text-xl font-semibold text-gray-800 mb-3">捐赠物资或资金</h3>
                <p class="text-gray-600">
                  你的每一份捐赠都能帮助流浪动物获得食物、医疗和庇护所，让它们的生活更加美好。
                </p>
                <button class="mt-4 bg-lemon hover:bg-lemon/90 text-white font-medium py-2 px-6 rounded-full transition-all transform hover:scale-105">
                  我要捐赠
                </button>
              </div>
            </div>
            
            <div class="bg-white rounded-xl shadow-md overflow-hidden transform transition-all hover:shadow-xl">
              <div class="h-48 overflow-hidden">
                <img src="@/assets/images/EndStray/1.png" alt="志愿者活动" class="w-full h-full object-cover">
              </div>
              <div class="p-6">
                <h3 class="text-xl font-semibold text-gray-800 mb-3">成为志愿者</h3>
                <p class="text-gray-600">
                  贡献你的时间和爱心，参与救助活动、宣传教育或帮助照顾流浪动物，成为它们的守护者。
                </p>
                <button class="mt-4 bg-lemon hover:bg-lemon/90 text-white font-medium py-2 px-6 rounded-full transition-all transform hover:scale-105">
                  我要加入
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- 领养须知 -->
      <div class="bg-white py-16">
        <div class="container mx-auto px-4 sm:px-6 lg:px-8">
          <div class="max-w-4xl mx-auto">
            <h2 class="text-3xl font-bold text-gray-800 mb-8 text-center">领养须知</h2>
            
            <div class="bg-gray-50 rounded-xl p-8 shadow-md">
              <ul class="space-y-4">
                <li class="flex items-start">
                  <div class="flex-shrink-0 mt-1 bg-lemon/20 text-lemon rounded-full p-1">
                    <i class="fa fa-check text-sm"></i>
                  </div>
                  <div class="ml-3">
                    <p class="text-gray-800">领养前请确保你有足够的时间、精力和经济能力照顾宠物的一生</p>
                  </div>
                </li>
                <li class="flex items-start">
                  <div class="flex-shrink-0 mt-1 bg-lemon/20 text-lemon rounded-full p-1">
                    <i class="fa fa-check text-sm"></i>
                  </div>
                  <div class="ml-3">
                    <p class="text-gray-800">了解宠物的生活习性和健康状况，做好充分的准备</p>
                  </div>
                </li>
                <li class="flex items-start">
                  <div class="flex-shrink-0 mt-1 bg-lemon/20 text-lemon rounded-full p-1">
                    <i class="fa fa-check text-sm"></i>
                  </div>
                  <div class="ml-3">
                    <p class="text-gray-800">领养后请给予宠物足够的关爱和陪伴，不要随意遗弃</p>
                  </div>
                </li>
                <li class="flex items-start">
                  <div class="flex-shrink-0 mt-1 bg-lemon/20 text-lemon rounded-full p-1">
                    <i class="fa fa-check text-sm"></i>
                  </div>
                  <div class="ml-3">
                    <p class="text-gray-800">定期带宠物去做体检和疫苗接种，确保它们的健康</p>
                  </div>
                </li>
                <li class="flex items-start">
                  <div class="flex-shrink-0 mt-1 bg-lemon/20 text-lemon rounded-full p-1">
                    <i class="fa fa-check text-sm"></i>
                  </div>
                  <div class="ml-3">
                    <p class="text-gray-800">尊重宠物的个性和需求，耐心训练和引导</p>
                  </div>
                </li>
              </ul>
              
              <div class="mt-8 text-center">
                <router-link to="/rescue/adoption-info" class="bg-lemon hover:bg-lemon/90 text-white font-medium py-3 px-8 rounded-full transition-all transform hover:scale-105">
                  我已了解，准备领养
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- 号召行动 -->
      <div class="bg-lemon/20 py-16">
        <div class="container mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h2 class="text-3xl font-bold text-gray-800 mb-6">让我们一起，终止流浪</h2>
          <p class="text-gray-600 max-w-2xl mx-auto mb-10">
            每一个生命都值得被温柔以待，无论大小，无论强弱。让我们用爱和行动，为流浪动物创造一个不再有恐惧和饥饿的世界。
          </p>
          <div class="flex flex-col sm:flex-row justify-center gap-4">
            <router-link to="/rescue/adoption-info" class="bg-lemon hover:bg-lemon/90 text-white font-medium py-3 px-8 rounded-full transition-all transform hover:scale-105">
              我要领养
            </router-link>
            <button class="bg-gray-800 hover:bg-gray-700 text-white font-medium py-3 px-8 rounded-full transition-all transform hover:scale-105">
              我要捐赠
            </button>
            <button class="bg-white hover:bg-gray-100 text-gray-800 font-medium py-3 px-8 rounded-full transition-all transform hover:scale-105">
              成为志愿者
            </button>
          </div>
        </div>
      </div>
  
      <!-- 页脚 -->
      <footer class="bg-gray-800 text-white py-12">
        <div class="container mx-auto px-4 sm:px-6 lg:px-8">
          <div class="flex flex-col md:flex-row justify-between items-center">
            <div class="mb-6 md:mb-0">
              <h3 class="text-xl font-bold">终止流浪 保护生命</h3>
              <p class="mt-2 text-gray-400">让每一个生命都有温暖的家</p>
            </div>
            <div class="flex space-x-6">
              <a href="#" class="text-gray-400 hover:text-white transition-colors">
                <i class="fa fa-weibo text-xl"></i>
              </a>
              <a href="#" class="text-gray-400 hover:text-white transition-colors">
                <i class="fa fa-wechat text-xl"></i>
              </a>
              <a href="#" class="text-gray-400 hover:text-white transition-colors">
                <i class="fa fa-instagram text-xl"></i>
              </a>
            </div>
          </div>
          <div class="mt-8 pt-8 border-t border-gray-700 text-center text-gray-400">
            <p>© 2025 流浪动物救助信息交流平台 版权所有</p>
          </div>
        </div>
      </footer>
    </div>
  </template>
  
  <script>
  export default {
    name: 'EndStray',
    data() {
      return {
        // 页面数据
      }
    },
    mounted() {
      // 页面加载后执行的操作
      window.scrollTo(0, 0);
    },
    methods: {
      // 页面方法
    }
  }
  </script>  