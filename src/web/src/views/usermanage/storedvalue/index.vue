<template>
  <a-card title="会员储值">
    <a-form :model="form">
      <a-form-item label="用户">
        <span>{{ selectedUsername }}</span>
        <a-button @click="selectUserModal = true">选择用户</a-button>
        <a-modal
          v-model:visible="selectUserModal"
          title="选择用户"
          @ok="onUserSelect"
        >
          <a-table :columns="modal.columns" :data-source="modal.data"></a-table>
        </a-modal>
      </a-form-item>
      <a-form-item label="储值金额">
        <a-input-number
          :min="0"
          :max="500"
          :step="0.1"
          v-model:value="form.amount"
        />
        <a-radio-group
          v-model:value="form.amount"
          button-style="solid"
          style="margin-left: 10px"
        >
          <a-radio-button :value="50">50</a-radio-button>
          <a-radio-button :value="100">100</a-radio-button>
          <a-radio-button :value="200">200</a-radio-button>
          <a-radio-button :value="400">400</a-radio-button>
        </a-radio-group>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="onSubmit">储值</a-button>
        <a-button style="margin-left: 10px" @click="onReset">重置</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script>
  export default {
    data() {
      return {
        selectUserModal: false,
        selectedUsername: '',
        form: {
          userid: null,
          amount: 0,
        },
        modal: {
          data: [],
          columns: [
            {
              title: '会员号',
              dataIndex: 'userid',
              key: 'userid',
              slots: { title: 'customTitle', customRender: '会员号' },
            },
            {
              title: '用户姓名',
              dataIndex: 'username',
              key: 'username',
            },
            {
              title: '手机号',
              key: 'phone',
              dataIndex: 'phone',
            },
            {
              title: '余额',
              key: 'balance',
              dataIndex: 'balance',
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
    methods: {
      onUserSelect() {
        this.selectUserModal = false
      },
      onSubmit() {},
      onReset() {
        this.form.userid = null
        this.form.amount = 0
      },
    },
  }
</script>

<style></style>
