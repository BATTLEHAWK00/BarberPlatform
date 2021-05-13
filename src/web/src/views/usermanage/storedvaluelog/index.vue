<template>
  <a-table :columns="columns" :data-source="data"></a-table>
</template>

<script>
  export default {
    data() {
      return {
        columns: [
          {
            title: '储值ID',
            dataIndex: 'id',
            key: 'id',
          },
          {
            title: '用户姓名',
            dataIndex: 'username',
            key: 'username',
          },
          {
            title: '储值金额',
            dataIndex: 'amount',
            key: 'amount',
          },
          {
            title: '储值时间',
            key: 'recharge_time',
            dataIndex: 'recharge_time',
          },
          {
            title: '储值类型',
            key: 'recharge_type',
            dataIndex: 'recharge_type',
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
      }
    },
  }
</script>

<style></style>
