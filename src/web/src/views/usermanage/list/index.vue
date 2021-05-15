<template>
  <a-table :columns="table.columns" :data-source="table.data">
    <template #gender="{ text }">{{ filterGender(text) }}</template>
    <template #time="{ text }">{{ filterTimeStamp(text) }}</template>
  </a-table>
</template>

<script>
  import { getList } from '@/api/user.js'
  import { filterTimeStamp } from '@/utils/filter.js'
  export default {
    mounted() {
      getList().then((res) => {
        this.table.data = res.data
      })
    },
    methods: {
      filterGender(gender) {
        console.log(gender)
        switch (gender) {
          case 0:
            return '男'
          case 1:
            return '女'
          default:
            return '未指定'
        }
      },
      filterTimeStamp,
    },
    data() {
      return {
        table: {
          data: [],
          columns: [
            {
              title: '会员号',
              dataIndex: 'userid',
              key: 'userid',
            },
            {
              title: '用户姓名',
              dataIndex: 'username',
              key: 'username',
            },
            {
              title: '用户性别',
              dataIndex: 'gender',
              key: 'gender',
              slots: { customRender: 'gender' },
            },
            {
              title: '手机号',
              key: 'phone',
              dataIndex: 'phone',
            },
            {
              title: '余额',
              dataIndex: 'balance',
              key: 'balance',
            },
            {
              title: '备注',
              dataIndex: 'remark',
              key: 'remark',
            },
            {
              title: '上次消费时间',
              dataIndex: 'lastconsume',
              key: 'lastconsume',
              slots: { customRender: 'time' },
            },
            {
              title: '注册时间',
              dataIndex: 'reg_time',
              key: 'reg_time',
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
