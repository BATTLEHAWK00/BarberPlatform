<template>
  <a-form :model="form">
    <a-form-item label="用户">
      <span>{{ selectedUsername }}</span>
      <a-button @click="selectUserModal = true">选择用户</a-button>
      <a-modal
        v-model:visible="selectUserModal"
        title="Basic Modal"
        @ok="onUserSelect"
      >
        <p>Some contents...</p>
        <p>Some contents...</p>
        <p>Some contents...</p>
      </a-modal>
    </a-form-item>
    <a-form-item>
      <a-button type="primary" @click="onSubmit">储值</a-button>
      <a-button style="margin-left: 10px" @click="onReset">重置</a-button>
    </a-form-item>
  </a-form>
</template>

<script>
  export default {
    data() {
      return {
        selectUserModal: false,
        selectedUsername: '',
        form: {
          userid: null,
        },
      }
    },
    methods: {
      onUserSelect() {
        this.selectUserModal = false
      },
      onSubmit() {},
      onReset() {},
    },
  }
</script>

<style></style>
