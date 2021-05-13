<template>
  <a-table :columns="table.columns" :data-source="table.data"></a-table>
</template>

<script>
  export default {
    data() {
      return {
        table: {
          data: [],
          columns: [
            {
              title: '订单号',
              dataIndex: 'oid',
              key: 'oid',
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
