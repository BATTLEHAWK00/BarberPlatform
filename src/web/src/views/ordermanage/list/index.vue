<template>
  <a-table :columns="table.columns" :data-source="table.data">
    <template #time="{ text }">{{ filterTimeStamp(text) }}</template>
    <template #state="{ text }">
      <a-tag color="blue">{{ filterOrderState(text) }}</a-tag>
    </template>
    <template #expandedRowRender="{ record }">
      <a-descriptions :title="'订单' + record.orderid">
        <a-descriptions-item label="用户姓名">
          {{ record.ownerid }}
        </a-descriptions-item>
        <a-descriptions-item label="创建者">
          {{ record.sponsorid }}
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
          {{ filterTimeStamp(record.price) }}
        </a-descriptions-item>
        <a-descriptions-item label="折扣">
          {{ filterTimeStamp(record.discount) }}
        </a-descriptions-item>
        <a-descriptions-item label="实付价格">
          {{ filterTimeStamp(record.actual_payment) }}
        </a-descriptions-item>
        <a-descriptions-item label="支付方式">
          {{ filterTimeStamp(record.pay_method) }}
        </a-descriptions-item>
        <a-descriptions-item label="备注">
          {{ filterTimeStamp(record.remark) }}
        </a-descriptions-item>
      </a-descriptions>
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
          data: [],
          columns: [
            {
              title: '订单号',
              dataIndex: 'orderid',
              key: 'orderid',
              sorter: (a, b) => a.recharge_time - b.recharge_time,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '用户姓名',
              dataIndex: 'ownerid',
              key: 'ownerid',
            },
            {
              title: '创建者',
              dataIndex: 'sponsorid',
              key: 'sponsorid',
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
            },
          ],
        },
      }
    },
  }
</script>

<style></style>
