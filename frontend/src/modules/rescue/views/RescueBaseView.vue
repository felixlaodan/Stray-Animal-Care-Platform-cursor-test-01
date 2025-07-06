<template>
  <div class="p-4 sm:p-6 lg:p-8">
    <div ref="chartDom" style="width: 100%; height: 80vh;"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import * as echarts from 'echarts';

const chartDom = ref(null);

onMounted(() => {
  if (chartDom.value) {
    const myChart = echarts.init(chartDom.value);
    myChart.showLoading();

    const ChinaJsonPath = 'https://geo.datav.aliyun.com/areas_v3/bound/510100_full.json';

    fetch(ChinaJsonPath)
      .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
      })
      .then(chinaJson => {
        echarts.registerMap('Chengdu', chinaJson);

        // 全量救助基地数据
        const rescueBases = [
          { base_name: '锦江区救助基地', rescue_count: 450, director: '张三', contact_phone: "028-85123456", location: '锦江区', address: '成都市锦江区XX路XX号', email: 'help@jinjiang.org', description: '这里是锦江区的爱心汇聚地，致力于为流浪动物提供温暖的家。' },
          { base_name: '青羊区救助基地', rescue_count: 968, director: '李四', contact_phone: "028-86234567", location: '青羊区', address: '成都市青羊区YY路YY号', email: 'help@qingyang.org', description: '在青羊区，我们用心守护每一个生命，让爱传递。' },
          { base_name: '金牛区救助基地', rescue_count: 1398, director: '王五', contact_phone: "028-87345678", location: '金牛区', address: '成都市金牛区ZZ路ZZ号', email: 'help@jinniu.org', description: '金牛区救助基地，用行动诠释责任与爱心，让每一只小动物都能找到归宿。' },
          { base_name: '武侯区救助基地', rescue_count: 890, director: '赵六', contact_phone: "028-85456789", location: '武侯区', address: '成都市武侯区AA路AA号', email: 'help@wuhou.org', description: '武侯区救助基地，以爱心和专业为支撑，为流浪动物提供安全的庇护所。' },
          { base_name: '成华区救助基地', rescue_count: 1254, director: '钱七', contact_phone: "028-84567890", location: '成华区', address: '成都市成华区BB路BB号', email: 'help@chenghua.org', description: '成华区救助基地，用心呵护每一只生命，让它们重获新生。' },
          { base_name: '龙泉驿区救助基地', rescue_count: 865, director: '孙八', contact_phone: "028-84856789", location: '龙泉驿区', address: '成都市龙泉驿区CC路CC号', email: 'help@longquanyi.org', description: '龙泉驿区救助基地，以无私奉献的精神，为流浪动物提供温暖的家园。' },
          { base_name: '青白江区救助基地', rescue_count: 1456, director: '周九', contact_phone: "028-83654321", location: '青白江区', address: '成都市青白江区DD路DD号', email: 'help@qingbaijiang.org', description: '青白江区救助基地，用爱心和责任守护每一只流浪动物，让它们感受到家的温暖。' },
          { base_name: '新都区救助基地', rescue_count: 1098, director: '吴十', contact_phone: "028-83987654", location: '新都区', address: '成都市新都区EE路EE号', email: 'help@xindu.org', description: '新都区救助基地，致力于为流浪动物提供一个温馨、安全的环境。' },
          { base_name: '温江区救助基地', rescue_count: 1124, director: '郑十一', contact_phone: "028-82765432", location: '温江区', address: '成都市温江区FF路FF号', email: 'help@wenjiang.org', description: '温江区救助基地，用爱心和专业为流浪动物提供庇护，让它们重新找到家。' },
          { base_name: '双流区救助基地', rescue_count: 1256, director: '王十二', contact_phone: "028-85765432", location: '双流区', address: '成都市双流区GG路GG号', email: 'help@shuangliu.org', description: '双流区救助基地，用心守护每一只流浪动物，让它们在这里找到新的希望。' },
          { base_name: '郫都区救助基地', rescue_count: 1487, director: '刘十三', contact_phone: "028-87890123", location: '郫都区', address: '成都市郫都区HH路HH号', email: 'help@pidu.org', description: '郫都区救助基地，用爱心和责任为流浪动物提供一个温暖的家。' },
          { base_name: '新津区救助基地', rescue_count: 768, director: '陈十四', contact_phone: "028-82567890", location: '新津区', address: '成都市新津区II路II号', email: 'help@xinjin.org', description: '新津区救助基地，致力于为流浪动物提供一个温馨、安全的环境，让它们感受到家的温暖。' },
          { base_name: '都江堰市救助基地', rescue_count: 768, director: '杨十五', contact_phone: "028-87123456", location: '都江堰市', address: '成都市都江堰市JJ路JJ号', email: 'help@dujiangyan.org', description: '都江堰市救助基地，用爱心和专业为流浪动物提供庇护，让它们重新找到家。' },
          { base_name: '彭州市救助基地', rescue_count: 656, director: '黄十六', contact_phone: "028-83876543", location: '彭州市', address: '成都市彭州市KK路KK号', email: 'help@pengzhou.org', description: '彭州市救助基地，用心守护每一只流浪动物，让它们在这里找到新的希望。' },
          { base_name: '邛崃市救助基地', rescue_count: 1134, director: '林十七', contact_phone: "028-88765432", location: '邛崃市', address: '成都市邛崃市LL路LL号', email: 'help@qionglai.org', description: '邛崃市救助基地，用爱心和责任为流浪动物提供一个温暖的家。' },
          { base_name: '崇州市救助基地', rescue_count: 1345, director: '钟十八', contact_phone: "028-82234567", location: '崇州市', address: '成都市崇州市MM路MM号', email: 'help@chongzhou.org', description: '崇州市救助基地，致力于为流浪动物提供一个温馨、安全的环境。' },
          { base_name: '简阳市救助基地', rescue_count: 1672, director: '徐十九', contact_phone: "028-27234567", location: '简阳市', address: '成都市简阳市NN路NN号', email: 'help@jianyang.org', description: '简阳市救助基地，用爱心和专业为流浪动物提供庇护，让它们重新找到家。' },
          { base_name: '金堂县救助基地', rescue_count: 1098, director: '何二十', contact_phone: "028-84987654", location: '金堂县', address: '成都市金堂县OO路OO号', email: 'help@jintang.org', description: '金堂县救助基地，用心守护每一只流浪动物，让它们在这里找到新的希望。' },
          { base_name: '大邑县救助基地', rescue_count: 1501, director: '高二十一', contact_phone: "028-88234567", location: '大邑县', address: '成都市大邑县PP路PP号', email: 'help@dayi.org', description: '大邑县救助基地，用爱心和责任为流浪动物提供一个温暖的家。' },
          { base_name: '蒲江县救助基地', rescue_count: 1723, director: '蔡二十二', contact_phone: "028-88567890", location: '蒲江县', address: '成都市蒲江县QQ路QQ号', email: 'help@pujiang.org', description: '蒲江县救助基地，致力于为流浪动物提供一个温馨、安全的环境。' }
        ];

        const option = {
          title: { text: '成都市宠物救助基地信息图', left: 'center' },
          tooltip: {
            trigger: 'item',
            formatter: function (params) {
              if (params.seriesName === '救助基地') {
                // 悬停在救助基地点
                const base = params.data.base;
                return `${base.base_name}<br>负责人: ${base.director}<br>联系电话: ${base.contact_phone}<br>电子邮箱: ${base.email}<br>位置: ${base.location}<br>地址: ${base.address}<br>已经被救助统计: ${base.rescue_count} (只)<br>${base.description}`;
              } else {
                // 悬停在区县
                const district = params.name;
                const basesInDistrict = rescueBases.filter(base => base.location === district);
                let tooltipContent = `${district}<br>救助站内宠物总数: ${params.value || 0} (只)<br>`;
                if (basesInDistrict.length > 0) {
                  basesInDistrict.forEach(base => {
                    tooltipContent += `<br>${base.base_name}<br>负责人: ${base.director}<br>联系电话: ${base.contact_phone}<br>电子邮箱: ${base.email}<br>位置: ${base.location}<br>地址: ${base.address}<br>已经被救助统计: ${base.rescue_count} (只)<br>${base.description}`;
                  });
                } else {
                  tooltipContent += '无救助基地';
                }
                return tooltipContent;
              }
            }
          },
          visualMap: {
            min: 500, max: 2000, left: 'left', top: 'bottom', text: ['高', '低'], calculable: true,
            inRange: { color: ['#e6a600', '#f2b900', '#f2d337', '#ffdf7f', '#ffcba2'] }
          },
          geo: { map: 'Chengdu', roam: true, itemStyle: { areaColor: '#e7e8ea' } },
          series: [
            {
              name: '成都市', type: 'map', geoIndex: 0,
              data: [
                {name: '锦江区', value: 942}, {name: '青羊区', value: 1402}, {name: '金牛区', value: 1996},
                {name: '武侯区', value: 1153}, {name: '成华区', value: 1754}, {name: '龙泉驿区', value: 1594},
                {name: '青白江区', value: 1844}, {name: '新都区', value: 1172}, {name: '温江区', value: 1399},
                {name: '双流区', value: 1612}, {name: '郫都区', value: 1790}, {name: '新津区', value: 1092},
                {name: '都江堰市', value: 884}, {name: '彭州市', value: 788}, {name: '邛崃市', value: 1306},
                {name: '崇州市', value: 1464}, {name: '简阳市', value: 1840}, {name: '金堂县', value: 1247},
                {name: '大邑县', value: 1648}, {name: '蒲江县', value: 1880}
              ]
            },
            {
              name: '救助基地', type: 'scatter', coordinateSystem: 'geo', symbol: 'pin', symbolSize: 50,
              label: { show: false },
              itemStyle: { color: '#FF0000' },
              data: rescueBases.map(base => ({
                name: base.base_name,
                value: [base.location, base.rescue_count], // 理想应为经纬度，这里用区名和数量
                base: base
              }))
            }
          ]
        };
        myChart.hideLoading();
        myChart.setOption(option);
      })
      .catch(error => {
        console.error('Error loading or parsing GeoJSON:', error);
        myChart.hideLoading();
      });
  }
});
</script> 