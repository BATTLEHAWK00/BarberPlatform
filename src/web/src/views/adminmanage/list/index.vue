<template>
  <a-table :columns="table.columns" :data-source="table.data">
    <template #time="{ text }">{{ filterTimeStamp(text) }}</template>
  </a-table>
</template>

<script>
  import { getList } from '@/api/admin.js'
  import { filterTimeStamp } from '@/utils/filter.js'
  export default {
    mounted() {
      getList().then((res) => {
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
              title: '管理员ID',
              dataIndex: 'adminid',
              key: 'adminid',
            },
            {
              title: '姓名',
              dataIndex: 'name',
              key: 'name',
            },
            {
              title: '电话',
              dataIndex: 'phone',
              key: 'phone',
            },
            {
              title: '备注',
              key: 'remark',
              dataIndex: 'remark',
            },
            {
              title: '上一单',
              key: 'last_order',
              dataIndex: 'last_order',
            },
            {
              title: '注册时间',
              key: 'reg_time',
              dataIndex: 'reg_time',
              slots: { customRender: 'time' },
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
