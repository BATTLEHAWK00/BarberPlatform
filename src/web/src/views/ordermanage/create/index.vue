<template>
  <a-form>
    <a-form-item label="选择用户">
      <a-button @click="modal.selectUserModal = true">选择用户</a-button>
      <user-select-modal
        v-model:showModal="modal.selectUserModal"
        @userSelected="onUserSelect"
      />
    </a-form-item>
    <a-form-item label="备注">
      <a-textarea
        v-model:value="form.remark"
        showCount
        allowClear
        size="small"
        :maxlength="255"
        :cols="100"
        :auto-size="{ minRows: 1, maxRows: 4 }"
      />
    </a-form-item>
    <a-form-item label="添加项目">
      <a-transfer
        :data-source="transfer.data"
        show-search
        :show-select-all="false"
      >
        <template #children="{ direction }">
          <a-table
            :columns="
              direction === 'left'
                ? transfer.table.leftColumns
                : transfer.table.rightColumns
            "
            :data-source="transfer.table.data"
          />
        </template>
      </a-transfer>
    </a-form-item>
    <a-form-item>
      <a-button type="primary" @click="onSubmit">提交</a-button>
      <a-button style="margin-left: 10px" @click="onReset">重置</a-button>
    </a-form-item>
  </a-form>
</template>

<script>
  import userSelectModal from '@/views/components/userSelectModal'
  export default {
    components: { userSelectModal },
    methods: {
      onUserSelect(data) {
        console.log(data)
      },
      onReset() {},
      onSubmit() {},
    },
    data() {
      return {
        form: {
          userid: null,
          remark: '',
        },
        transfer: {
          data: [],
          table: {
            data: [],
            leftColumns: [
              {
                dataIndex: 'left_name',
                title: '名称',
              },
              {
                dataIndex: 'left_price',
                title: '价格',
              },
              {
                dataIndex: 'left_remark',
                title: '描述',
              },
            ],
            rightColumns: [
              {
                dataIndex: 'right_name',
                title: '名称',
              },
              {
                dataIndex: 'right_price',
                title: '价格',
              },
              {
                dataIndex: 'right_amount',
                title: '数量',
              },
              {
                dataIndex: 'right_remark',
                title: '备注',
              },
            ],
          },
        },
        modal: {
          userData: {},
          selectUserModal: false,
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
  }
</script>

<style></style>
