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
              title: '预约ID',
              dataIndex: 'id',
              key: 'id',
            },
            {
              title: '用户姓名',
              dataIndex: 'username',
              key: 'username',
            },
            {
              title: '预约时间',
              dataIndex: 'due_time',
              key: 'due_time',
            },
            {
              title: '创建时间',
              key: 'create_time',
              dataIndex: 'create_time',
            },
            {
              title: '预约理发师',
              key: 'targetbarber',
              dataIndex: 'targetbarber',
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
