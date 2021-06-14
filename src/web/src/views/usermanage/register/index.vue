<template>
  <a-card title="会员注册">
    <a-form :model="form">
      <!-- <a-form-item>
        <a-button type="primary" @click="onSubmit">注册</a-button>
        <a-button style="margin-left: 10px">重置</a-button>
      </a-form-item> -->
      <a-form-item label="用户名">
        <a-input v-model:value="form.username" placeholder="输入姓名" />
      </a-form-item>
      <a-form-item label="手机号">
        <a-input v-model:value="form.phone" placeholder="输入手机号" />
      </a-form-item>
      <a-form-item label="性别">
        <a-radio-group v-model:value="form.gender" button-style="solid">
          <a-radio-button :value="null">未指定</a-radio-button>
          <a-radio-button :value="0">男</a-radio-button>
          <a-radio-button :value="1">女</a-radio-button>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="消费密码">
        <a-input-password
          v-model:value="form.passwd"
          placeholder="输入消费密码"
          allowClear
          :visibilityToggle="false"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="onSubmit">注册</a-button>
        <a-button style="margin-left: 10px" @click="onReset">重置</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script>
import {register} from '@/api/user.js'
import {Modal} from 'ant-design-vue'

export default {
    data() {
      return {
        form: {
          username: '',
          phone: '',
          passwd: '',
          gender: null,
        },
      }
    },
    methods: {
      onSubmit() {
        register(this.form).then(() => {
          Modal.success({
            title: '注册成功!',
          })
          this.onReset()
        })
      },
      onReset() {
        this.form.username = ''
        this.form.phone = ''
        this.form.passwd = ''
        this.form.sex = null
      },
    },
  }
</script>

<style></style>
