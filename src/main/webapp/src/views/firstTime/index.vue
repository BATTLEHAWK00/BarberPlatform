<template>
  <div>
    <div class="login-container">
      <a-card class="box-center">
        <div class="login-container-form">
          <div class="login-container-hello">欢迎来到理发店管理系统！</div>
          <div class="login-container-title">
            您第一次使用本系统，请设置用户名和密码。
          </div>
          <a-form :model="form" @submit="onSubmit" @submit.prevent>
            <a-form-item>
              <a-input v-model:value="form.username" placeholder="用户名">
                <template v-slot:prefix>
                  <UserOutlined style="color: rgba(0, 0, 0, 0.25)" />
                </template>
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-input
                v-model:value="form.password"
                type="password"
                placeholder="密码"
              >
                <template v-slot:prefix>
                  <LockOutlined style="color: rgba(0, 0, 0, 0.25)" />
                </template>
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-input
                v-model:value="form.confirmPassword"
                type="password"
                placeholder="再次输入密码"
              >
                <template v-slot:prefix>
                  <LockOutlined style="color: rgba(0, 0, 0, 0.25)" />
                </template>
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-button
                type="primary"
                html-type="submit"
                :disabled="
                  form.username === '' ||
                  form.password === '' ||
                  form.confirmPassword === ''
                "
              >
                确认
              </a-button>
            </a-form-item>
          </a-form>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script>
  import { firstTime, setLoginToken } from '@/api/system'
  import { message } from 'ant-design-vue'

  export default {
    name: 'firstTime',
    data() {
      return {
        form: {
          username: 'admin',
          password: '',
          confirmPassword: '',
        },
      }
    },
    async mounted() {
      if ((await firstTime()).data === 'No') {
        await this.$router.push('/login')
      }
    },
    methods: {
      async onSubmit() {
        await setLoginToken(this.form.username, this.form.password)
        message.success('设置成功！')
        await this.$router.push('/login')
      },
    },
  }
</script>

<style scoped>
  .box-center {
    position: absolute;
    width: 400px;
    top: 100px;
    left: calc(50% - 200px);
    box-shadow: 1px 3px 15px #d0d0d0;
  }
</style>
