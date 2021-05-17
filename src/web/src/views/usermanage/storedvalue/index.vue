<template>
  <a-card title="会员储值">
    <a-form :model="form">
      <a-form-item label="用户">
        <a-tag v-if="selectedUsername">
          【{{ form.userid }}】{{ selectedUsername }}
        </a-tag>
        <a-button @click="showModal = true">选择用户</a-button>
        <user-select-modal
          v-model:showModal="showModal"
          @userSelected="onUserSelect"
        />
      </a-form-item>
      <a-form-item label="储值金额">
        <a-input-number
          :min="0"
          :max="500"
          :step="10"
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
      <a-form-item label="储值密码">
        <a-input
          type="password"
          v-model:value="form.passwd"
          placeholder="输入密码"
          allowClear
        ></a-input>
      </a-form-item>
      <a-form-item label="备注">
        <a-textarea
          v-model:value="form.remark"
          placeholder="输入备注(可空)"
          :auto-size="{ minRows: 1, maxRows: 4 }"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="onSubmit">储值</a-button>
        <a-button style="margin-left: 10px" @click="onReset">重置</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script>
  import { verifyPasswd } from '@/api/user.js'
  import { recharge } from '@/api/storedvalue.js'
  import { Modal } from 'ant-design-vue'
  import userSelectModal from '@/views/components/userSelectModal'
  export default {
    components: {
      userSelectModal,
    },
    data() {
      return {
        showModal: false,
        selectedUsername: '',
        form: {
          userid: null,
          amount: 0,
          passwd: '',
          remark: '',
        },
      }
    },
    methods: {
      onUserSelect(data) {
        this.selectedUsername = data.username
        this.form.userid = data.userid
      },
      onSubmit() {
        verifyPasswd({
          uid: this.form.userid,
          passwd: this.form.passwd,
        }).then(() => {
          recharge({
            uid: this.form.userid,
            value: this.form.amount,
            type: 0,
            remark: this.form.remark,
          }).then(() => {
            this.onReset()
            Modal.success({ title: '储值成功!' })
          })
        })
      },
      onReset() {
        this.form.userid = null
        this.form.amount = 0
        this.form.passwd = ''
        this.form.remark = ''
        this.selectedUsername = ''
      },
    },
  }
</script>

<style></style>
