<template>
  <div class="font-sans text-gray-800 bg-gray-50">
    <!-- 轮播图区域 -->
    <div class="relative h-[80vh] overflow-hidden">
      <div class="carousel-container flex transition-transform duration-500 ease-in-out" :style="{ transform: `translateX(-${currentSlide * 100}%)` }">
        <!-- 轮播图1 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide1.jpg" alt="等待领养" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">给予它们一个温暖的家</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">每只流浪动物都有自己的故事，您的爱心可以改变它们的命运</p>
            <button @click="$router.push('/rescue/adoption-info')" class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              我要领养
            </button>
          </div>
        </div>

        <!-- 轮播图2 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide2.png" alt="等待救助" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">上报流浪动物足迹</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">您的一次上传分享，可能是它们生命的一个转折点</p>
            <button @click="$router.push('/rescue/')" class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              我要上报
            </button>
          </div>
        </div>

        <!-- 轮播图3 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide3.jpg" alt="志愿者照顾流浪动物" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">和爱心人士一起交流</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">相互帮助，共享知识，共同保护流浪动物</p>
            <button class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              交流互助
            </button>
          </div>
        </div>

        <!-- 轮播图4 -->
        <div class="carousel-slide w-full h-full flex-shrink-0 relative">
          <div class="absolute inset-0 bg-black/40 z-10"></div>
          <img src="@/assets/images/Home/slide4.jpg" alt="捐赠帮助流浪动物" class="w-full h-full object-cover">
          <div class="absolute inset-0 z-20 flex flex-col justify-center items-center text-white text-center px-6">
            <h2 class="text-[clamp(2rem,5vw,4rem)] font-bold mb-4 tracking-tight">科普先于行动</h2>
            <p class="text-[clamp(1rem,2vw,1.5rem)] max-w-3xl mb-8">学习科普知识，科学救助流浪动物，保护他们的未来</p>
            <button class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
              查看科普
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
                  <img src="@/assets/images/Home/why1.jpg" class="w-full h-auto object-cover">
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
                  <img src="@/assets/images/Home/why2.jpg"  class="w-full h-auto object-cover">
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
                  <img src="@/assets/images/Home/why3.png" class="w-full h-auto object-cover">
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
              <img src="@/assets/images/Home/example1.png" alt="小黑的新生" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">小黑的新生</h3>
              <p class="text-gray-600 mb-4">小黑是街头一只腿部残疾的流浪狗，艰难地挪动着身体寻找食物。<br>爱心人士发现它后，立即送往宠物医院进行手术治疗。经过三个月的康复训练和精心护理，小黄不仅能自由奔跑，还被一位善良的女士领养，开启了幸福新生活。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年3月</span>
              </div>
            </div>
          </div>

          <!-- 案例2 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example2.jpg" alt="咪咪的幸福转折" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">咪咪的幸福转折</h3>
              <p class="text-gray-600 mb-4">零下5度的清晨，咪咪蜷缩在快递柜下，冻得连叫声都发不出来。<br>爱心人士用暖水袋焐热它冻僵的爪子，喂下温羊奶后送进保温箱。治疗猫鼻支的20天里，它从抗拒触摸到主动蹭人手心。<br>现在它在领养家庭的毛毯上打盹，爪子总爱搭在主人手腕上——那是它记住温暖的方式。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年3月</span>
              </div>
            </div>
          </div>

          <!-- 案例3 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example3.jpg" alt="布丁的重生日记" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">布丁的重生日记</h3>
              <p class="text-gray-600 mb-4">布丁是只被遗弃在巷口的流浪猫，右前爪骨折化脓，浑身沾满沥青。<br>爱心人士用橄榄油一点点清理它的毛发，送往医院手术后，又经历45天的笼养康复。<br>如今它在领养家庭里每天趴在飘窗晒太阳，还学会了用完好的爪子扒拉逗猫棒——曾经瑟缩在垃圾桶旁的小可怜，终于成了会踩奶的撒娇精。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年4月</span>
              </div>
            </div>
          </div>

          <!-- 案例4 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example4.jpg" alt="阿旺的晴天" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">阿旺的晴天</h3>
              <p class="text-gray-600 mb-4">暴雨夜发现阿旺时，这只瘸腿的流浪狗正躲在汽车底盘下，耳朵撕裂的伤口还在渗血。<br>爱心人士用毛巾裹着它送医，缝针、打疫苗、驱跳蚤，整整两个月的悉心照料让它从瘦骨嶙峋变得毛发油亮。<br>现在它的新主人每天带它去公园跑步，曾经警惕躲闪的眼神，如今总亮晶晶地追着飞盘跑。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年5月</span>
              </div>
            </div>
          </div>

          <!-- 案例5 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example5.jpg" alt="泡泡的春日" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">泡泡的春日</h3>
              <p class="text-gray-600 mb-4">工地旁发现泡泡时，它瘦得只剩骨头，尾巴被铁丝勒出深痕。<br>解开铁丝、消炎护理后，它慢慢变得亲人。<br>现在新主人每天带它遛弯，曾经躲闪的小狗，如今会主动把爪子搭在人掌心。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年6月</span>
              </div>
            </div>
          </div>

          <!-- 案例6 -->
          <div class="bg-white rounded-xl shadow-lg overflow-hidden flex flex-col md:flex-row">
            <div class="md:w-1/2 h-64 md:h-auto overflow-hidden">
              <img src="@/assets/images/Home/example6.jpg" alt="豆豆的第二次生命" class="w-full h-full object-cover">
            </div>
            <div class="md:w-1/2 p-6 flex flex-col justify-center">
              <h3 class="text-xl font-bold text-gray-900 mb-2">豆豆的第二次生命</h3>
              <p class="text-gray-600 mb-4">被发现时，黑豆拖着半截尾巴在绿化带里啃树皮，尾巴溃烂处爬满蛆虫。清创手术时它疼得发抖，却始终没咬过医护人员一口。<br>三个月后，断尾愈合的它在领养家庭学会了巡回游戏，每当主人回家，这只曾被伤害的小狗总会叼来拖鞋，用摇尾巴的弧度画出最圆的句号。</p>
              <div class="flex items-center mt-auto">
                <span class="text-sm text-gray-500">救助时间: 2025年6月</span>
              </div>
            </div>
          </div>

        </div>

        <p class="text-gray-800 max-w-3xl mx-auto text-xl text-center mt-12">因为被看见，它们拥有了幸福。<br>发现并上报您身边的流浪动物，赐予它们一个幸福的机会，<br>帮助流浪动物们与爱它们的家人找到彼此。</p>

        <div class="text-center mt-10">
          <router-link to="/rescue/upload-stray" class="inline-block bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 font-bold py-3 px-8 rounded-full transition-all duration-300 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-[rgb(252,211,55)] focus:ring-opacity-50">
            点击上报
          </router-link>
        </div>
      </div>
    </div>



    <!-- 页脚 -->
    <footer class="bg-gray-900 text-white py-12 px-6">
      <div class="max-w-7xl mx-auto">
        <div class="grid md:grid-cols-4 gap-8">
          <div>
            <h3 class="text-lg font-bold mb-4">关于平台</h3>
            <p class="text-gray-400 mb-4">平台致力于流浪动物救助信息交流，<br>希望通过所有爱心人士的努力，<br>让每一只流浪动物都能得到关爱和保护。</p>
            <div class="flex space-x-4">
              <a href="https://web2.wechat.com/?lang=zh_HK" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fab fa-weixin"></i>
              </a>
              <a href="https://im.qq.com/" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fab fa-qq"></i>
              </a>
              <a href="https://www.bilibili.com/" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fab fa-bilibili"></i>
              </a>
              <a href="https://weibo.com/" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">
                <i class="fab fa-weibo"></i>
              </a>
            </div>
          </div>

          <div>
            <h3 class="text-lg font-bold mb-4">快速链接</h3>
            <ul class="space-y-2">
              <li><router-link to="/team" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">平台建设者</router-link></li>
              <li><router-link to="/rescue/adoption-info" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">领养信息</router-link></li>
              <li><router-link to="/posts" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">交流互动</router-link></li>
              <li><router-link to="/ai" class="text-gray-400 hover:text-[rgb(252,211,55)] transition-colors">智能科普</router-link></li>
            </ul>
          </div>

          <div>
            <h3 class="text-lg font-bold mb-4">联系方式</h3>
            <ul class="space-y-2">
              <li class="flex items-center">
                <i class="fa fa-map-marker mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">克拉玛依市安定路爱心大厦3层</span>
              </li>
              <li class="flex items-center">
                <i class="fa fa-phone mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">010-0990-888</span>
              </li>
              <li class="flex items-center">
                <i class="fa fa-envelope mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">cupk@helpstray.org</span>
              </li>
              <li class="flex items-center">
                <i class="fa fa-clock mr-2 text-[rgb(252,211,55)]"></i>
                <span class="text-gray-400">周一至周日 9:00 - 18:00</span>
              </li>
            </ul>
          </div>

          <div>
            <h3 class="text-lg font-bold mb-4">联系我们</h3>
            <p class="text-gray-400 mb-4">通过电子邮箱，向我们发送您的意见建议</p>
            <form class="flex">
              <input type="email" placeholder="请输入您的QQ邮箱" class="px-4 py-2 rounded-l-lg w-full focus:outline-none text-gray-900">
              <a href="https://wx.mail.qq.com/" class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 px-4 py-2 rounded-r-lg transition-colors">
                <button type="submit"
                class="bg-[rgb(252,211,55)] hover:bg-[rgb(252,200,0)] text-gray-900 px-4 py-2 rounded-r-lg transition-colors">
                  <i class="fa fa-paper-plane"></i>
                </button>
              </a>
            </form>
          </div>
        </div>

        <div class="border-t border-gray-800 mt-10 pt-6 text-center text-gray-500">
          <p>&copy; 2025 爪印归途——流浪动物救助信息交流平台. 保留所有权利.</p>
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
