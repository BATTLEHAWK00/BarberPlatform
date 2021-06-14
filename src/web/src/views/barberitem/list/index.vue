<template>
  <a-table
    :columns="table.columns"
    :data-source="table.data"
    rowKey="itemid"
    bordered
  >
    <template #time="{ text }">{{ filterTimeStamp(text) }}</template>
    <template #action="{ record }">
      <a-button type="primary" @click="onEditUser(record)">编辑</a-button>
    </template>
  </a-table>
</template>

<script>
import {getList} from '@/api/barberitem.js'
import {filterTimeStamp} from '@/utils/filter.js'

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
              title: '项目ID',
              dataIndex: 'itemid',
              key: 'itemid',
              sorter: (a, b) => a.adminid - b.adminid,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '名称',
              dataIndex: 'name',
              key: 'name',
            },
            {
              title: '单价',
              dataIndex: 'price',
              key: 'price',
              sorter: (a, b) => a.price - b.price,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '备注',
              key: 'remark',
              dataIndex: 'remark',
            },
            {
              title: '添加时间',
              key: 'addTime',
              dataIndex: 'addTime',
              slots: { customRender: 'time' },
              sorter: (a, b) => a.add_time - b.add_time,
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

<style></style>
