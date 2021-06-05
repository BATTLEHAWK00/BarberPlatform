<template>
  <a-table
    :columns="table.columns"
    :data-source="table.data"
    :loading="table.loading"
    rowKey="oid"
  >
    <template #time="{ text }">{{ filterTimeStamp(text) }}</template>
    <template #state="{ text }">
      <a-tag color="blue">{{ filterOrderState(text) }}</a-tag>
    </template>
    <template #expandedRowRender="{ record }">
      <a-descriptions :title="'订单' + record.oid" bordered>
        <a-descriptions-item label="用户姓名">
          {{ record.username }}
        </a-descriptions-item>
        <a-descriptions-item label="创建者">
          {{ record.sponsor }}
        </a-descriptions-item>
        <a-descriptions-item label="订单状态">
          <a-tag color="blue">
            {{ filterOrderState(record.state) }}
          </a-tag>
        </a-descriptions-item>
        <a-descriptions-item label="创建时间">
          {{ filterTimeStamp(record.create_time) }}
        </a-descriptions-item>
        <a-descriptions-item label="原价格">
          {{ record.price }}
        </a-descriptions-item>
        <a-descriptions-item label="折扣">
          {{ record.discount }}
        </a-descriptions-item>
        <a-descriptions-item label="实付价格">
          {{ record.actual_payment }}
        </a-descriptions-item>
        <a-descriptions-item label="支付方式">
          {{ record.pay_method }}
        </a-descriptions-item>
        <a-descriptions-item label="备注">
          {{ record.remark }}
        </a-descriptions-item>
      </a-descriptions>
    </template>
    <template #action="{ record }">
      <div class="actions">
        <a v-if="record.state === 1">支付</a>
        <a>修改</a>
        <a>关闭</a>
      </div>
    </template>
  </a-table>
</template>

<script>
  import { getList } from '@/api/order.js'
  import { filterTimeStamp } from '@/utils/filter.js'
  export default {
    mounted() {
      // var that = this
      getList().then((res) => {
        this.table.data = res.data
        this.table.loading = false
      })
    },
    methods: {
      filterTimeStamp,
      filterOrderState(state) {
        switch (state) {
          case 0:
            return '已创建'
          case 1:
            return '待支付'
          case 2:
            return '已支付'
          case 3:
            return '已完成'
          case 4:
            return '已关闭'
          default:
            return '未知'
        }
      },
    },
    data() {
      return {
        table: {
          loading: true,
          data: [],
          columns: [
            {
              title: '订单号',
              dataIndex: 'oid',
              key: 'oid',
              sorter: (a, b) => a.recharge_time - b.recharge_time,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '用户姓名',
              dataIndex: 'username',
              key: 'username',
            },
            {
              title: '创建者',
              dataIndex: 'sponsor',
              key: 'sponsor',
            },
            {
              title: '创建时间',
              key: 'create_time',
              dataIndex: 'create_time',
              slots: { customRender: 'time' },
              sorter: (a, b) => a.create_time - b.create_time,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '支付时间',
              key: 'pay_time',
              dataIndex: 'pay_time',
              slots: { customRender: 'time' },
            },
            {
              title: '实付金额',
              key: 'actual_payment',
              dataIndex: 'actual_payment',
              sorter: (a, b) => a.actual_payment - b.actual_payment,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '订单状态',
              key: 'state',
              dataIndex: 'state',
              slots: { customRender: 'state' },
              sorter: (a, b) => a.state - b.state,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '操作',
              key: 'action',
              slots: { customRender: 'action' },
            },
          ],
        },
      }
    },
  }
</script>

<style scoped>
  .actions a {
    margin-right: 10px;
  }
</style>
