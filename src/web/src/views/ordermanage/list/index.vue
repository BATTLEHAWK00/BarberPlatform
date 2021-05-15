<template>
  <a-table :columns="table.columns" :data-source="table.data"></a-table>
</template>

<script>
  import { getList } from '@/api/order.js'
  export default {
    mounted() {
      // var that = this
      getList().then((res) => {
        this.table.data = res.data
      })
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
            },
            {
              title: '订单状态',
              key: 'create_time',
              dataIndex: 'create_time',
            },
            {
              title: '支付时间',
              key: 'pay_time',
              dataIndex: 'pay_time',
            },
            {
              title: '支付方式',
              key: 'pay_method',
              dataIndex: 'pay_method',
            },
            {
              title: '原价格',
              key: 'price',
              dataIndex: 'price',
            },
            {
              title: '折扣',
              key: 'discount',
              dataIndex: 'discount',
            },
            {
              title: '实付金额',
              key: 'actual_payment',
              dataIndex: 'actual_payment',
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
