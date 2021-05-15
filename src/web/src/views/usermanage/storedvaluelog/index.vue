<template>
  <a-table :columns="table.columns" :data-source="table.data">
    <template #time="{ text }">
      {{ filterTimeStamp(text) }}
    </template>
  </a-table>
</template>

<script>
  import { getStoredValueLogs } from '@/api/user.js'
  import { filterTimeStamp } from '@/utils/filter.js'
  export default {
    mounted() {
      getStoredValueLogs().then((res) => {
        this.table.data = res.data
      })
    },
    methods: {
      filterTimeStamp,
    },
    data() {
      return {
        table: {
          data: [],
          columns: [
            {
              title: '储值ID',
              dataIndex: 'rechargeid',
              key: 'rechargeid',
            },
            {
              title: '用户姓名',
              dataIndex: 'username',
              key: 'username',
            },
            {
              title: '储值金额',
              dataIndex: 'recharge_amount',
              key: 'recharge_amount',
            },
            {
              title: '储值时间',
              key: 'recharge_time',
              dataIndex: 'recharge_time',
              slots: { customRender: 'time' },
            },
            {
              title: '储值类型',
              key: 'type',
              dataIndex: 'type',
            },
            {
              title: '备注',
              key: 'remark',
              dataIndex: 'remark',
            },
            {
              title: '操作',
              key: 'action',
            },
          ],
        },
      }
    },
  }
</script>

<style></style>
