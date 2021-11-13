<template>
  <div>
    <a-table
      :columns="table.columns"
      :data-source="table.data"
      :loading="table.loading"
      bordered
      rowKey="userid"
    >
      <template #time="{ text }">{{ filterTimeStamp(text) }}</template>
      <template #expandedRowRender="{ record }">
        <a-descriptions :title="record.username" bordered layout="vertical">
          <a-descriptions-item label="性别">
            {{ filterGender(record.gender) }}
          </a-descriptions-item>
          <a-descriptions-item label="注册时间">
            {{ filterTimeStamp(record.regTime) }}
          </a-descriptions-item>
          <a-descriptions-item label="电话">
            {{ record.phone }}
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
    <edit-user-modal
      v-model:showModal="modal.show"
      :user-data="modal.userData"
    />
  </div>
</template>

<script>
  import { getList } from '@/api/user.js'
  import { filterTimeStamp } from '@/utils/filter.js'
  import editUserModal from './components/editUserModal.vue'

  export default {
    components: {
      editUserModal,
    },
    mounted() {
      getList().then((res) => {
        this.table.data = res.data
        this.table.loading = false
      })
    },
    methods: {
      filterGender(gender) {
        switch (gender) {
          case 0:
            return '男'
          case 1:
            return '女'
          default:
            return '未指定'
        }
      },
      onEditUser(data) {
        this.modal.userData = data
        this.modal.show = true
      },
      filterTimeStamp,
    },
    data() {
      return {
        modal: {
          userData: null,
          show: false,
        },
        table: {
          loading: true,
          data: [],
          columns: [
            {
              title: '会员号',
              dataIndex: 'userId',
              key: 'userId',
              width: '10%',
              sorter: (a, b) => a.userid - b.userid,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '用户姓名',
              dataIndex: 'username',
              key: 'username',
            },
            {
              title: '余额',
              dataIndex: 'balance',
              key: 'balance',
              sorter: (a, b) => a.balance - b.balance,
              sortDirections: ['descend', 'ascend'],
            },
            {
              title: '上次消费时间',
              dataIndex: 'lastConsumeTime',
              key: 'lastConsumeTime',
              slots: { customRender: 'time' },
              sorter: (a, b) => a.lastconsume - b.lastconsume,
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