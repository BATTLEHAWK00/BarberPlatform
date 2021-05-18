<template>
  <a-form>
    <a-form-item label="选择用户">
      <a-tag v-if="selectedUserName">
        【{{ form.userid }}】{{ selectedUserName }}
      </a-tag>
      <a-button @click="selectUserModal = true">选择用户</a-button>
      <user-select-modal
        v-model:showModal="selectUserModal"
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
        :target-keys="transfer.targetKeys"
        :row-key="(record) => record.itemid"
        :render="(item) => item.name"
        style="width: 800px"
        :filter-option="
          (inputValue, item) =>
            item.name.indexOf(inputValue) !== -1 || item.itemid == inputValue
        "
        @change="
          (nextTargetKeys) => {
            transfer.targetKeys = nextTargetKeys
          }
        "
      >
        <template
          #children="{ direction, filteredItems, selectedKeys, onItemSelect }"
        >
          <a-table
            :columns="
              direction === 'left'
                ? transfer.table.leftColumns
                : transfer.table.rightColumns
            "
            :row-selection="{
              selectedRowKeys: selectedKeys,
              onSelect({ key }, selected) {
                onItemSelect(key, selected)
              },
            }"
            row-key="itemid"
            :data-source="filteredItems"
            size="small"
          >
            <template #amount="{ record }">
              <a-input-number
                v-model:value="record.amount"
                :min="1"
                :max="99"
                :defaultValue="1"
              />
            </template>
          </a-table>
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
  import { Modal } from 'ant-design-vue'
  import { getList } from '@/api/barberitem.js'
  import { createOrder, addItem, getLastOrder } from '@/api/order.js'
  export default {
    components: { userSelectModal },
    methods: {
      onUserSelect(data) {
        this.selectedUserName = data.username
        this.form.userid = data.userid
      },
      onReset() {},
      onSubmit() {
        console.log(this.transfer.targetKeys)
        console.log(this.transfer.data)
        createOrder({
          sponsorid: 1,
          ownerid: this.form.userid,
          remark: this.form.remark,
        }).then(() => {
          this.transfer.targetKeys.forEach((itemid) => {
            getLastOrder().then((res) => {
              addItem(res.data.oid, {
                itemid: itemid,
                sponsorid: 1,
                ownerid: this.form.userid,
                amount: 5,
              })
            })
          })
          Modal.success({
            title: '创建成功!',
          })
        })
      },
      onChange(nextTargetKeys) {
        console.log(nextTargetKeys)
        this.transfer.targetKeys = nextTargetKeys
      },
    },
    mounted() {
      getList().then((res) => {
        this.transfer.data = res.data
      })
    },
    data() {
      return {
        selectUserModal: false,
        selectedUserName: '',
        form: {
          userid: null,
          remark: '',
        },
        transfer: {
          data: [],
          targetKeys: [],
          table: {
            leftColumns: [
              {
                dataIndex: 'itemid',
                title: 'ID',
              },
              {
                dataIndex: 'name',
                title: '名称',
              },
              {
                dataIndex: 'price',
                title: '价格',
              },
              {
                dataIndex: 'remark',
                title: '描述',
              },
            ],
            rightColumns: [
              {
                dataIndex: 'itemid',
                title: 'ID',
              },
              {
                dataIndex: 'name',
                title: '名称',
              },
              {
                dataIndex: 'price',
                title: '价格',
              },
              {
                dataIndex: 'amount',
                title: '数量',
                slots: { customRender: 'amount' },
              },
            ],
          },
        },
      }
    },
  }
</script>

<style></style>
