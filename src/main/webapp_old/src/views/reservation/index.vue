<template>
  <a-card>
    <h1 style="text-align: center">预约进度</h1>
    <div>
      <span style="float: left">{{ currentTime.toLocaleString() }}</span>
      <span style="float: right">
        {{ new Date(data[0].time).toLocaleString() }}
      </span>
      <a-progress
        :percent="getCurrentProgress"
        status="active"
        :show-info="false"
      />
    </div>
    <a-timeline style="margin-top: 50px">
      <a-timeline-item v-for="(item, i) in data" :key="i">
        <span>
          {{ filterTimeStamp(item.time) }}
        </span>
        <a-card
          size="small"
          title="预约单"
          style="width: 200px; margin-top: 20px"
          hoverable
        >
          <p>ID:{{ item.reserveid }}</p>
          <p>来自 {{ item.username }}的预约</p>
          <p>预约人员: {{ item.adminname }}</p>
        </a-card>
      </a-timeline-item>
    </a-timeline>
  </a-card>
  <!-- <a-table :columns="table.columns" :data-source="table.data"></a-table> -->
</template>

<script>
  import { filterTimeStamp } from '@/utils/filter.js'

  export default {
    methods: {
      filterTimeStamp,
      getCurrentTime() {
        return new Date()
      },
    },
    computed: {
      getCurrentProgress() {
        let targetTime = this.data[0].time
        let currentTime = Date.parse(this.currentTime) // - 1 * 60 * 60 * 1000
        let progress =
          ((0.5 * 60 * 60 * 1000 - targetTime + currentTime) /
            (0.5 * 60 * 60 * 1000)) *
          100
        //console.log(targetTime + ' ' + currentTime + ' ' + progress)
        return progress
      },
    },
    mounted() {
      setInterval(() => {
        this.currentTime = this.getCurrentTime()
      }, 1000)
    },
    data() {
      return {
        currentTime: this.getCurrentTime(),
        data: [
          {
            time: Date.parse(this.getCurrentTime()) + 0.2 * 60 * 60 * 1000,
            reserveid: 1,
            username: 'yxl',
            adminname: 'asd',
          },
          {
            time: 1620549692000,
            reserveid: 2,
            username: 'yxl',
            adminname: 'asd',
          },
          {
            time: 1620549692000,
            reserveid: 3,
            username: 'yxl',
            adminname: 'asd',
          },
        ],
      }
    },
  }
</script>

<style></style>