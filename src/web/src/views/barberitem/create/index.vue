<template>
  <a-card title="消费项目创建">
    <a-form :model="form">
      <!-- <a-form-item>
        <a-button type="primary" @click="onSubmit">注册</a-button>
        <a-button style="margin-left: 10px">重置</a-button>
      </a-form-item> -->
      <a-form-item label="项目名称">
        <a-input v-model:value="form.name" placeholder="输入名称" />
      </a-form-item>
      <a-form-item label="价格">
        <a-input-number
          :min="0"
          :max="1000"
          :step="10"
          v-model:value="form.price"
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
      <a-form-item>
        <a-button type="primary" @click="onSubmit">创建</a-button>
        <a-button style="margin-left: 10px" @click="onReset">重置</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script>
  import { createItem } from '@/api/barberitem.js'
  import { Modal } from 'ant-design-vue'
  export default {
    data() {
      return {
        form: {
          name: '',
          remark: '',
          price: 0,
        },
      }
    },
    methods: {
      onSubmit() {
        createItem(this.form).then(() => {
          Modal.success({
            title: '创建成功!',
          })
          this.onReset()
        })
      },
      onReset() {
        this.form.name = ''
        this.form.price = 0
        this.form.remark = ''
      },
    },
  }
</script>

<style></style>
