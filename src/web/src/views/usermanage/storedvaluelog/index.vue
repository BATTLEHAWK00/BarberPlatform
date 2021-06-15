<template>
  <a-table
    :columns="table.columns"
    :data-source="table.data"
    :loading="table.loading"
    rowKey="rechargeid"
    bordered
  >
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
        this.table.loading = false
      })
    },
    methods: {
      filterTimeStamp,
    },
    data() {
      return {
        table: {
          loading: true,
          data: [],
          columns: [
            {
              title: '储值ID',
              dataIndex: 'rechargeid',
              key: 'rechargeid',
              sorter: (a, b) => a.rechargeid - b.rechargeid,
              sortDirections: ['descend', 'ascend'],
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
              sorter: (a, b) => a.recharge_amount - b.recharge_amount,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '储值时间',
              key: 'recharge_time',
              dataIndex: 'recharge_time',
              slots: { customRender: 'time' },
              sorter: (a, b) => a.recharge_time - b.recharge_time,
              sortDirections: ['descend', 'ascend'],
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
          ],
        },
      }
    },
  }
</script>

<style></style>