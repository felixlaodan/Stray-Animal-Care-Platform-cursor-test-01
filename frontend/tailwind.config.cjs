/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        lemon: 'rgb(252, 211, 55)', // 直接定义为颜色值
        dragon: 'rgb(255, 200, 0)',
      },
    },
  },
  corePlugins: {
    // 确保没有禁用需要的插件
    preflight: true,
  },
}