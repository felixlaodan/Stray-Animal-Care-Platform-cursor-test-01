<template>
  <div>
    <div>
      EChart图
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