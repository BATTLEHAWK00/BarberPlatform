<template>
  <!-- <a-alert :message="'欢迎来到' + title + '!'" type="success" /> -->

  <a-card title="理发店概况" :loading="loading">
    <a-row type="flex">
      <a-col :span="6" :order="4">
        <a-card>
          <a-statistic title="会员数" :value="9" suffix="人"></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="6" :order="3">
        <a-card>
          <a-statistic title="管理员数" :value="9" suffix="人"></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="6" :order="2">
        <a-card>
          <a-statistic
            title="今日消费用户"
            :value="9"
            suffix="人"
          ></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="6" :order="1">
        <a-card>
          <a-statistic
            title="今日储值用户"
            :value="9"
            suffix="人"
          ></a-statistic>
        </a-card>
      </a-col>
    </a-row>
  </a-card>
</template>
<script>
  import { mapGetters } from 'vuex'
  export default {
    computed: {
      ...mapGetters({
        title: 'settings/title',
      }),
    },
    mounted() {
      setTimeout(() => {
        this.loading = false
      }, 500)
    },
    data() {
      return {
        loading: true,
        message: 'Hello!',
      }
    },
  }
</script>
<style scoped></style>
