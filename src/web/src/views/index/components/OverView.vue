<template>
  <a-alert :message="message" type="success" />

  <a-card>
    <h3>欢迎来到{{ title }}!</h3>
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
    data() {
      return {
        message: 'Hello!',
      }
    },
  }
</script>
<style scoped></style>
