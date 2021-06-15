<template>
  <a-modal
    :visible="showModal"
    :onCancel="onModalClose"
    title="选择用户"
    @ok="onUserSelect"
    :footer="null"
  >
    <a-table
      :columns="modal.columns"
      :data-source="modal.data"
      bordered
      size="small"
      :loading="modal.loading"
    >
      <template #username="{ record, text }">
        <a @click="onUserSelect(record)">{{ text }}</a>
      </template>
    </a-table>
  </a-modal>
</template>

<script>
  import { getList } from '@/api/user.js'

  export default {
    methods: {
      onUserSelect(data) {
        this.$emit('userSelected', data)
        this.onModalClose()
      },
      onModalClose() {
        this.$emit('update:showModal', false)
      },
    },
    mounted() {
      this.modal.loading = true
      getList().then((res) => {
        this.modal.data = res.data
        this.modal.loading = false
      })
    },
    data() {
      return {
        modal: {
          loading: true,
          data: [],
          columns: columns,
        },
      }
    },
    props: ['showModal'],
  }
  const columns = [
    {
      title: '会员号',
      dataIndex: 'userid',
      key: 'userid',
      sorter: (a, b) => a.userid - b.userid,
      sortDirections: ['descend', 'ascend'],
    },
    {
      title: '用户姓名',
      dataIndex: 'username',
      key: 'username',
      slots: { customRender: 'username' },
    },
    {
      title: '余额',
      key: 'balance',
      dataIndex: 'balance',
      sorter: (a, b) => a.balance - b.balance,
      sortDirections: ['descend', 'ascend'],
    },
  ]
</script>

<style></style>