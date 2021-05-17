<template>
  <v-chart class="chart" :option="option" />
</template>

<script>
  import { use } from 'echarts/core'
  import { CanvasRenderer } from 'echarts/renderers'
  import { PieChart } from 'echarts/charts'
  import {
    TitleComponent,
    TooltipComponent,
    LegendComponent,
  } from 'echarts/components'
  import VChart from 'vue-echarts'
  import { ref, defineComponent } from 'vue'

  use([
    CanvasRenderer,
    PieChart,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
  ])

  export default defineComponent({
    name: 'HelloWorld',
    components: {
      VChart,
    },
    setup: () => {
      const option = ref({
        title: {
          text: '营收状况',
          left: 'center',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['洗发', '剪发', '吹发', '染发', '烫发'],
        },
        series: [
          {
            name: '消费项目',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              { value: 335, name: '洗发' },
              { value: 310, name: '剪发' },
              { value: 234, name: '吹发' },
              { value: 135, name: '染发' },
              { value: 1548, name: '烫发' },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)',
              },
            },
          },
        ],
      })

      return { option }
    },
  })
</script>

<style scoped>
  .chart {
    height: 400px;
  }
</style>
