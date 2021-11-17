<template>
  <div>
    <a-table
      :columns="table.columns"
      :data-source="table.data"
      :loading="table.loading"
      :row-selection="{
        selectedRowKeys: table.selectedRowKeys,
        onChange: onSelectChange,
      }"
      bordered
      row-key="adminid"
    >
      <template #time="{ text }">{{ filterTimeStamp(text) }}</template>
      <template #expandedRowRender="{ record }">
        <a-descriptions :title="record.name" bordered layout="vertical">
          <a-descriptions-item label="电话">
            {{ record.phone }}
          </a-descriptions-item>
          <a-descriptions-item label="注册时间">
            {{ filterTimeStamp(record.regTime) }}
          </a-descriptions-item>
          <a-descriptions-item label="上一单ID">
            {{ record.lastOrderID }}
          </a-descriptions-item>
          <a-descriptions-item label="备注" span="2">
            {{ record.remark }}
          </a-descriptions-item>
        </a-descriptions>
      </template>
      <template #action="{ record }">
        <a-button type="primary" @click="onEditUser(record)">编辑</a-button>
      </template>
    </a-table>
  </div>
</template>

<script>
  import { getList } from '@/api/admin.js'
  import { filterTimeStamp } from '@/utils/filter.js'

  export default {
    mounted() {
      getList().then((res) => {
        this.table.data = res.data
        this.table.loading = false
      })
    },
    methods: {
      onAdminUpdated() {
        this.$forceUpdate()
      },
      filterTimeStamp,
      onSelectChange(selectedRowKeys) {
        console.log('selectedRowKeys changed: ', selectedRowKeys)
        this.table.selectedRowKeys = selectedRowKeys
      },
      onEditUser(data) {
        this.modal.adminData = data
        this.modal.show = true
      },
    },
    data() {
      return {
        modal: {
          adminData: null,
          show: false,
        },
        table: {
          selectedRowKeys: [],
          loading: true,
          data: [],
          columns: [
            {
              title: '管理员ID',
              dataIndex: 'adminId',
              key: 'adminId',
              sorter: (a, b) => a.adminid - b.adminid,
              sortDirections: ['descend', 'ascend'],
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
              title: '注册时间',
              key: 'regTime',
              dataIndex: 'regTime',
              slots: { customRender: 'time' },
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
