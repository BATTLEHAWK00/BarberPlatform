<template>
  <div class="index-container">
    <h1 style="text-align: center">欢迎访问{{ title }}后台!</h1>
    <over-view />
  </div>
</template>

<script>
  import OverView from './components/OverView'
  import { mapGetters } from 'vuex'
  export default {
    computed: {
      ...mapGetters({
        title: 'settings/title',
      }),
    },
    name: 'Index',
    components: { OverView },
  }
</script>
