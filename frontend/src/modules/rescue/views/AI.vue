<template>
  <div class="min-h-screen bg-gray-50 text-gray-800 font-sans">
    <!-- 英雄区 -->
    <header class="relative overflow-hidden bg-lemon-500 py-16 md:py-24">
      <div class="container mx-auto px-4 relative z-10">
        <h1 class="text-[clamp(2rem,5vw,3.5rem)] font-bold text-center text-gray-800 mb-4 animate-fadeIn">
          流浪动物救助与领养科普
        </h1>
        <p class="text-[clamp(1rem,2vw,1.25rem)] text-center text-gray-700 max-w-3xl mx-auto mb-8">
          了解如何帮助流浪动物，以及领养宠物的温暖与责任
        </p>
        <div class="flex justify-center mt-12">
          <a href="#status" class="bg-lemon/70 hover:bg-lemon text-white font-bold py-3 px-8 rounded-full shadow-lg hover:shadow-xl transform hover:-translate-y-1 transition-all duration-300">
            参与讨论 <i class="fas fa-paw ml-2"></i>
          </a>
        </div>
      </div>
      <div class="absolute inset-0 opacity-80">
        <img src="@/assets/images/AI/1.jpg" alt="流浪动物" class="w-full h-full object-cover">
      </div>
    </header>

    <!-- 流浪动物现状 -->
    <section id="status" class="py-16 bg-white">
      <div class="container mx-auto px-4">
        <div class="text-center mb-12">
          <h2 class="text-[clamp(1.8rem,4vw,2.5rem)] font-bold text-gray-800 mb-4">流浪动物的现状</h2>
          <div class="w-20 h-1 bg-lemon-500 mx-auto mb-6"></div>
          <p class="text-lg text-gray-600 max-w-2xl mx-auto">了解我们身边流浪动物的生存状况，以及它们面临的挑战</p>
        </div>

        <div class="grid md:grid-cols-2 gap-8 items-center">
          <div class="bg-gray-50 p-8 rounded-xl shadow-md hover:shadow-lg transition-shadow duration-300 transform hover:-translate-y-1">
            <h3 class="text-xl font-bold text-gray-800 mb-4 flex items-center">
              <i class="fas fa-exclamation-triangle text-lemon-600 mr-3"></i> 严峻的生存挑战
            </h3>
            <ul class="space-y-3 text-gray-600">
              <li class="flex items-start">
                <i class="fas fa-paw text-lemon-500 mt-1 mr-2"></i>
                <span>全球有数亿只流浪动物，仅中国就有约4000万只流浪猫狗</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-paw text-lemon-500 mt-1 mr-2"></i>
                <span>流浪动物平均寿命仅2-3年，远低于家养宠物的10-15年</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-paw text-lemon-500 mt-1 mr-2"></i>
                <span>食物短缺、恶劣天气、疾病和交通事故是主要威胁</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-paw text-lemon-500 mt-1 mr-2"></i>
                <span>每年有大量流浪动物因无人救助而死亡或被安乐死</span>
              </li>
            </ul>
          </div>

          <div class="relative">
            <img src="@/assets/images/AI/1.jpg" alt="流浪动物" class="w-full h-auto rounded-xl shadow-lg">
            <div class="absolute -bottom-6 -right-6 bg-white rounded-lg shadow-lg max-w-xs transform transition-transform hover:scale-105">
              <img src="@/assets/images/AI/1.jpg" alt="流浪动物" class="w-full h-auto rounded-xl shadow-lg">
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 如何救助流浪动物 -->
    <section id="help" class="py-16 bg-gray-50">
      <div class="container mx-auto px-4">
        <div class="text-center mb-12">
          <h2 class="text-[clamp(1.8rem,4vw,2.5rem)] font-bold text-gray-800 mb-4">如何救助流浪动物</h2>
          <div class="w-20 h-1 bg-lemon-500 mx-auto mb-6"></div>
          <p class="text-lg text-gray-600 max-w-2xl mx-auto">每个人都可以为流浪动物伸出援手，这些方法简单而有效</p>
        </div>

        <div class="grid md:grid-cols-3 gap-8">
          <!-- 救助方法卡片1 -->
          <div class="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition-all duration-300 group">
            <div class="w-16 h-16 bg-lemon-100 rounded-full flex items-center justify-center mb-4 group-hover:bg-lemon-200 transition-colors duration-300">
              <i class="fas fa-feeding-bottle text-2xl text-lemon-600 group-hover:scale-110 transition-transform duration-300"></i>
            </div>
            <h3 class="text-xl font-bold text-gray-800 mb-3">提供食物和水</h3>
            <p class="text-gray-600 mb-4">在固定地点为流浪动物提供干净的食物和水，选择适合它们的猫粮或狗粮，保持喂食点清洁。</p>
            <a href="#" class="text-lemon-600 font-medium flex items-center group-hover:text-lemon-800 transition-colors duration-300">
              了解更多 <i class="fas fa-arrow-right ml-2 group-hover:ml-3 transition-all duration-300"></i>
            </a>
          </div>

          <!-- 救助方法卡片2 -->
          <div class="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition-all duration-300 group">
            <div class="w-16 h-16 bg-lemon-100 rounded-full flex items-center justify-center mb-4 group-hover:bg-lemon-200 transition-colors duration-300">
              <i class="fas fa-home text-2xl text-lemon-600 group-hover:scale-110 transition-transform duration-300"></i>
            </div>
            <h3 class="text-xl font-bold text-gray-800 mb-3">搭建临时庇护所</h3>
            <p class="text-gray-600 mb-4">用纸箱或塑料箱制作简易庇护所，放入旧衣物保暖，放置在避风处，帮助它们抵御恶劣天气。</p>
            <a href="#" class="text-lemon-600 font-medium flex items-center group-hover:text-lemon-800 transition-colors duration-300">
              了解更多 <i class="fas fa-arrow-right ml-2 group-hover:ml-3 transition-all duration-300"></i>
            </a>
          </div>

          <!-- 救助方法卡片3 -->
          <div class="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition-all duration-300 group">
            <div class="w-16 h-16 bg-lemon-100 rounded-full flex items-center justify-center mb-4 group-hover:bg-lemon-200 transition-colors duration-300">
              <i class="fas fa-heartbeat text-2xl text-lemon-600 group-hover:scale-110 transition-transform duration-300"></i>
            </div>
            <h3 class="text-xl font-bold text-gray-800 mb-3">联系救助机构</h3>
            <p class="text-gray-600 mb-4">发现受伤或生病的动物，及时联系当地动物保护协会或流浪动物救助站，寻求专业帮助。</p>
            <a href="#" class="text-lemon-600 font-medium flex items-center group-hover:text-lemon-800 transition-colors duration-300">
              了解更多 <i class="fas fa-arrow-right ml-2 group-hover:ml-3 transition-all duration-300"></i>
            </a>
          </div>
        </div>
      </div>
    </section>

    <!-- 领养代替购买 -->
    <section id="adopt" class="py-16 bg-white">
      <div class="container mx-auto px-4">
        <div class="text-center mb-12">
          <h2 class="text-[clamp(1.8rem,4vw,2.5rem)] font-bold text-gray-800 mb-4">领养代替购买</h2>
          <div class="w-20 h-1 bg-lemon-500 mx-auto mb-6"></div>
          <p class="text-lg text-gray-600 max-w-2xl mx-auto">领养一只流浪动物，给它们一个家，也给自己一个温暖的伙伴</p>
        </div>

        <div class="grid md:grid-cols-2 gap-12 items-center">
          <div>
            <h3 class="text-2xl font-bold text-gray-800 mb-6">为什么选择领养？</h3>
            <div class="space-y-6">
              <div class="flex">
                <div class="flex-shrink-0 mt-1">
                  <div class="w-10 h-10 rounded-full bg-lemon-100 flex items-center justify-center text-lemon-600 font-bold">1</div>
                </div>
                <div class="ml-4">
                  <h4 class="text-xl font-semibold text-gray-800 mb-2">拯救生命</h4>
                  <p class="text-gray-600">领养一只流浪动物，就是拯救一个生命，给它们一个温暖的家。</p>
                </div>
              </div>

              <div class="flex">
                <div class="flex-shrink-0 mt-1">
                  <div class="w-10 h-10 rounded-full bg-lemon-100 flex items-center justify-center text-lemon-600 font-bold">2</div>
                </div>
                <div class="ml-4">
                  <h4 class="text-xl font-semibold text-gray-800 mb-2">更温顺的伙伴</h4>
                  <p class="text-gray-600">许多流浪动物经过救助后，会更加珍惜家庭，成为忠诚温顺的宠物。</p>
                </div>
              </div>

              <div class="flex">
                <div class="flex-shrink-0 mt-1">
                  <div class="w-10 h-10 rounded-full bg-lemon-100 flex items-center justify-center text-lemon-600 font-bold">3</div>
                </div>
                <div class="ml-4">
                  <h4 class="text-xl font-semibold text-gray-800 mb-2">经济实惠</h4>
                  <p class="text-gray-600">领养费用远低于购买宠物，而且许多救助机构会提供疫苗和绝育服务。</p>
                </div>
              </div>

              <div class="flex">
                <div class="flex-shrink-0 mt-1">
                  <div class="w-10 h-10 rounded-full bg-lemon-100 flex items-center justify-center text-lemon-600 font-bold">4</div>
                </div>
                <div class="ml-4">
                  <h4 class="text-xl font-semibold text-gray-800 mb-2">减少流浪动物数量</h4>
                  <p class="text-gray-600">领养并绝育，有助于控制流浪动物数量，从根本上解决问题。</p>
                </div>
              </div>
            </div>
          </div>

          <div class="grid grid-cols-2 gap-4">
            <div class="group">
              <img src="@/assets/images/AI/1.jpg" alt="领养宠物" class="w-full h-64 object-cover rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
              <div class="bg-white p-4">
                <h4 class="font-bold text-gray-800">小黑 - 2岁</h4>
                <p class="text-gray-600 text-sm">已绝育，疫苗齐全，温顺亲人</p>
              </div>
            </div>

            <div class="group">
              <img src="@/assets/images/AI/1.jpg" alt="领养宠物" class="w-full h-64 object-cover rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
              <div class="bg-white p-4">
                <h4 class="font-bold text-gray-800">花花 - 1岁</h4>
                <p class="text-gray-600 text-sm">已绝育，活泼好动，喜欢互动</p>
              </div>
            </div>

            <div class="group">
              <img src="@/assets/images/AI/1.jpg" alt="领养宠物" class="w-full h-64 object-cover rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
              <div class="bg-white p-4">
                <h4 class="font-bold text-gray-800">小白 - 3岁</h4>
                <p class="text-gray-600 text-sm">已绝育，安静乖巧，适合家庭</p>
              </div>
            </div>

            <div class="group">
              <img src="@/assets/images/AI/1.jpg" alt="领养宠物" class="w-full h-64 object-cover rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 transform hover:-translate-y-1">
              <div class="bg-white p-4">
                <h4 class="font-bold text-gray-800">豆豆 - 2岁</h4>
                <p class="text-gray-600 text-sm">已绝育，聪明伶俐，会基本指令</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

<!-- 领养流程 -->
<section id="process" class="py-16 relative overflow-hidden">
  <div class="container mx-auto px-4">
    <div class="text-center mb-12">
      <h2 class="text-[clamp(1.8rem,4vw,2.5rem)] font-bold text-gray-800 mb-4">领养流程指南</h2>
      <div class="w-20 h-1 bg-lemon mx-auto mb-6 transition-all duration-300 hover:scale-x-150"></div>
      <p class="text-lg text-gray-600 max-w-2xl mx-auto">了解领养宠物的基本流程，做好准备迎接新伙伴</p>
    </div>

    <div class="relative">
      <!-- 时间线 -->
      <div id="timeline" class="absolute left-1/2 transform -translate-x-1/2 h-full w-1 bg-lemon-200 md:w-2 transition-all duration-500"></div>

      <div class="space-y-12 relative">
        <!-- 步骤1 -->
        <div class="flex flex-col md:flex-row items-center">
          <div class="md:w-1/2 md:pr-12 md:text-right mb-6 md:mb-0">
            <div class="process-text p-4 rounded-lg transition-all duration-500 bg-lemon/50 hover:shadow-xl hover:bg-lemon hover:scale-[1.02]">
              <h3 class="text-xl font-bold text-dark mb-2">选择合适的领养机构</h3>
              <p class="text-gray-600">寻找正规的动物救助站、领养中心或动物保护协会，确保动物健康和权益得到保障。</p>
            </div>
          </div>
          <div class="z-10 w-12 h-12 rounded-full bg-lemon flex items-center justify-center text-black font-bold text-xl shadow-lg transform transition-transform duration-500 hover:rotate-12 hover:scale-110 hover:bg-lemon-700">1</div>
          <div class="md:w-1/2 md:pl-12"></div>
        </div>

        <!-- 步骤2 -->
        <div class="flex flex-col md:flex-row items-center">
          <div class="md:w-1/2 md:pr-12"></div>
          <div class="z-10 w-12 h-12 rounded-full bg-lemon flex items-center justify-center text-black font-bold text-xl shadow-lg transform transition-transform duration-500 hover:rotate-12 hover:scale-110 hover:bg-lemon-700">2</div>
          <div class="md:w-1/2 md:pl-12 md:text-left mb-6 md:mb-0">
            <div class="process-text p-4 rounded-lg transition-all duration-500 bg-lemon/50 hover:shadow-xl hover:bg-lemon hover:scale-[1.02]">
              <h3 class="text-xl font-bold text-gray-800 mb-2">挑选合适的宠物</h3>
              <p class="text-gray-600">根据自己的生活方式和居住环境，选择适合的宠物，考虑年龄、性格和健康状况。</p>
            </div>
          </div>
        </div>

        <!-- 步骤3 -->
        <div class="flex flex-col md:flex-row items-center">
          <div class="md:w-1/2 md:pr-12 md:text-right mb-6 md:mb-0">
            <div class="process-text p-4 rounded-lg transition-all duration-500 bg-lemon/50 hover:shadow-xl hover:bg-lemon hover:scale-[1.02]">
              <h3 class="text-xl font-bold text-gray-800 mb-2">提交领养申请</h3>
              <p class="text-gray-600">填写领养申请表，提供个人信息和居住环境等资料，以便机构评估是否适合领养。</p>
            </div>
          </div>
          <div class="z-10 w-12 h-12 rounded-full bg-lemon flex items-center justify-center text-black font-bold text-xl shadow-lg transform transition-transform duration-500 hover:rotate-12 hover:scale-110 hover:bg-lemon-700">3</div>
          <div class="md:w-1/2 md:pl-12"></div>
        </div>

        <!-- 步骤4 -->
        <div class="flex flex-col md:flex-row items-center">
          <div class="md:w-1/2 md:pr-12"></div>
          <div class="z-10 w-12 h-12 rounded-full bg-lemon flex items-center justify-center text-black font-bold text-xl shadow-lg transform transition-transform duration-500 hover:rotate-12 hover:scale-110 hover:bg-lemon-700">4</div>
          <div class="md:w-1/2 md:pl-12 md:text-left mb-6 md:mb-0">
            <div class="process-text p-4 rounded-lg transition-all duration-500 bg-lemon/50 hover:shadow-xl hover:bg-lemon hover:scale-[1.02]">
              <h3 class="text-xl font-bold text-gray-800 mb-2">家访和面谈</h3>
              <p class="text-gray-600">领养机构可能会进行家访或面谈，确保宠物将进入一个安全和合适的家庭环境。</p>
            </div>
          </div>
        </div>

        <!-- 步骤5 -->
        <div class="flex flex-col md:flex-row items-center">
          <div class="md:w-1/2 md:pr-12 md:text-right mb-6 md:mb-0">
            <div class="process-text p-4 rounded-lg transition-all duration-500 bg-lemon/50 hover:shadow-xl hover:bg-lemon hover:scale-[1.02]">
              <h3 class="text-xl font-bold text-gray-800 mb-2">完成领养手续</h3>
              <p class="text-gray-600">签署领养协议，支付领养费用（如有），获取宠物的健康记录和疫苗信息。</p>
            </div>
          </div>
          <div class="z-10 w-12 h-12 rounded-full bg-lemon flex items-center justify-center text-black font-bold text-xl shadow-lg transform transition-transform duration-500 hover:rotate-12 hover:scale-110 hover:bg-lemon-700">5</div>
          <div class="md:w-1/2 md:pl-12"></div>
        </div>

        <!-- 步骤6 -->
        <div class="flex flex-col md:flex-row items-center">
          <div class="md:w-1/2 md:pr-12"></div>
          <div class="z-10 w-12 h-12 rounded-full bg-lemon flex items-center justify-center text-black font-bold text-xl shadow-lg transform transition-transform duration-500 hover:rotate-12 hover:scale-110 hover:bg-lemon-700">6</div>
          <div class="md:w-1/2 md:pl-12 md:text-left">
            <div class="process-text p-4 rounded-lg transition-all duration-500 bg-lemon/50 hover:shadow-xl hover:bg-lemon hover:scale-[1.02]">
              <h3 class="text-xl font-bold text-gray-800 mb-2">迎接新伙伴回家</h3>
              <p class="text-gray-600">准备好宠物用品，迎接新成员回家，给予足够的时间和耐心让宠物适应新环境。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </section>

    <!-- 领养后的照顾 -->
    <section id="care" class="py-16 bg-img">
      <div class="container mx-auto px-4">
        <div class="text-center mb-12">
          <h2 class="text-[clamp(1.8rem,4vw,2.5rem)] font-bold text-gray-800 mb-4">领养后的照顾指南</h2>
          <div class="w-20 h-1 bg-lemon-500 mx-auto mb-6"></div>
          <p class="text-lg text-gray-600 max-w-2xl mx-auto">领养只是开始，正确的照顾才能让宠物健康快乐成长</p>
        </div>

        <div class="grid md:grid-cols-2 gap-8 mb-12">
          <div class="bg-gray-50 p-6 rounded-xl shadow-md">
            <h3 class="text-xl font-bold text-gray-800 mb-4 flex items-center">
              <i class="fas fa-food text-lemon-600 mr-3"></i> 饮食管理
            </h3>
            <ul class="space-y-3 text-gray-600">
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>选择适合宠物年龄和品种的优质宠物食品</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>定时定量喂食，避免过度喂养导致肥胖</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>提供干净新鲜的饮用水，每天更换</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>避免喂食人类食物，尤其是巧克力、洋葱等对宠物有害的食品</span>
              </li>
            </ul>
          </div>

          <div class="bg-gray-50 p-6 rounded-xl shadow-md">
            <h3 class="text-xl font-bold text-gray-800 mb-4 flex items-center">
              <i class="fas fa-bed text-lemon-600 mr-3"></i> 居住环境
            </h3>
            <ul class="space-y-3 text-gray-600">
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>为宠物准备舒适的窝，放置在安静温暖的地方</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>定期清洁宠物窝和用品，保持卫生</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>确保家中环境安全，避免宠物接触到危险物品</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>为猫咪准备猫砂盆，为狗狗提供足够的活动空间</span>
              </li>
            </ul>
          </div>

          <div class="bg-gray-50 p-6 rounded-xl shadow-md">
            <h3 class="text-xl font-bold text-gray-800 mb-4 flex items-center">
              <i class="fas fa-heartbeat text-lemon-600 mr-3"></i> 健康护理
            </h3>
            <ul class="space-y-3 text-gray-600">
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>定期带宠物进行体检和疫苗接种</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>及时进行驱虫处理，包括体内和体外</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>注意宠物的口腔卫生，定期刷牙或提供洁牙食品</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>观察宠物的行为和身体状况，如有异常及时就医</span>
              </li>
            </ul>
          </div>

          <div class="bg-gray-50 p-6 rounded-xl shadow-md">
            <h3 class="text-xl font-bold text-gray-800 mb-4 flex items-center">
              <i class="fas fa-paw text-lemon-600 mr-3"></i> 情感陪伴
            </h3>
            <ul class="space-y-3 text-gray-600">
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>每天花时间陪伴宠物，建立信任和亲密关系</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>为狗狗提供足够的运动和玩耍时间</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>为猫咪提供玩具和攀爬设施，满足它们的天性</span>
              </li>
              <li class="flex items-start">
                <i class="fas fa-check-circle text-lemon-500 mt-1 mr-2"></i>
                <span>耐心进行基础训练，培养良好的行为习惯</span>
              </li>
            </ul>
          </div>
        </div>

        <div class="text-center">
          <div class="relative inline-block">
            <img src="@/assets/images/AI/1.jpg" alt="领养" class="w-full h-auto rounded-xl shadow-lg">
            <div class="absolute inset-0 bg-black bg-opacity-30 rounded-xl flex items-center justify-center">
              <button id="careTipsBtn" class=" hover:bg-lemon text-white font-bold py-3 px-8 rounded-full shadow-lg hover:shadow-xl transform hover:-translate-y-1 transition-all duration-300">
                做好准备后，试着去领养一只动物吧
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 页脚 -->


    <!-- 照顾小贴士模态框（时间不够先不做了） -->
  </div>
</template>

<script>
  // 步骤卡片交互效果
    document.querySelectorAll('.process-step').forEach(step => {
      step.addEventListener('mouseenter', function() {
        // 高亮时间线
        const timeline = document.getElementById('timeline');
        timeline.classList.add('bg-lemon');

        // 添加卡片背景效果
        this.classList.add('bg-lemon-50');
      });

      step.addEventListener('mouseleave', function() {
        // 恢复时间线样式
        const timeline = document.getElementById('timeline');
        timeline.classList.remove('bg-lemon');

        // 恢复卡片背景
        this.classList.remove('bg-lemon-50');
      });
    });

    // 平滑滚动效果
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
      anchor.addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
          behavior: 'smooth'
        });
      });
    });
//导入 Chart.js
import { Chart } from 'chart.js';

export default {
  name: 'AnimalAdoptionGuide',
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.initChart();
    this.addScrollAnimations();
    this.setupModalEvents();
  },
  methods: {
    initChart() {
      const ctx = document.getElementById('strayAnimalChart').getContext('2d');
      // 使用导入的 Chart 类创建图表
      this.chart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['流浪狗', '流浪猫', '其他'],
          datasets: [{
            data: [55, 35, 10],
            backgroundColor: [
              'rgba(252, 211, 55, 0.8)',
              'rgba(59, 130, 246, 0.8)',
              'rgba(16, 185, 129, 0.8)'
            ],
            borderWidth: 0
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              position: 'bottom',
              labels: {
                color: '#333',
                padding: 10,
                font: {
                  size: 12
                }
              }
            }
          }
        }
      });
    },
    addScrollAnimations() {
      const animateElements = document.querySelectorAll('.bg-white, .bg-gray-50, img[class*="rounded-xl"]');

      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            entry.target.classList.add('animate-fadeInUp');
            observer.unobserve(entry.target);
          }
        });
      }, {
        threshold: 0.1
      });

      animateElements.forEach(element => {
        observer.observe(element);
      });
    },
    setupModalEvents() {
      const careTipsBtn = document.getElementById('careTipsBtn');
      const closeModalBtn = document.getElementById('closeModalBtn');
      const closeModalBtn2 = document.getElementById('closeModalBtn2');
      const modal = document.getElementById('careTipsModal');

      careTipsBtn.addEventListener('click', () => {
        modal.classList.remove('hidden');
        document.body.style.overflow = 'hidden';
      });

      closeModalBtn.addEventListener('click', () => {
        modal.classList.add('hidden');
        document.body.style.overflow = '';
      });

      closeModalBtn2.addEventListener('click', () => {
        modal.classList.add('hidden');
        document.body.style.overflow = '';
      });

      // 点击模态框外部关闭
      modal.addEventListener('click', (e) => {
        if (e.target === modal) {
          modal.classList.add('hidden');
          document.body.style.overflow = '';
        }
      });


    }
  }
}
</script>

<style scoped>
/* 自定义动画 */
@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
}

@keyframes fadeInUp {
  from { opacity: 0; transform: translateY(30px); }
  to { opacity: 1; transform: translateY(0); }
}

.animate-fadeIn {
  animation: fadeIn 0.8s ease-out forwards;
}

.animate-fadeInUp {
  animation: fadeInUp 0.8s ease-out forwards;
}

</style>
