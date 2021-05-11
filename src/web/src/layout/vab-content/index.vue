<template>
  <a-layout-content class="vab-content">
    <router-view v-slot="{ Component }">
      <transition mode="out-in" name="fade-transform">
        <component :is="Component" />
      </transition>
    </router-view>
  </a-layout-content>
</template>

<script>
  export default {
    name: 'VabContent',
    watch: {
      $route: {
        handler() {
          if ('mobile' === this.device) {
            this.$store.dispatch('settings/foldSideBar')
          }
        },
        immediate: true,
      },
    },
  }
</script>

<style lang="less">
  .fade-transform-enter-active {
    transition: all 0.5s;
  }

  .fade-transform-leave-active {
    transition: all 0.25s;
  }

  .fade-transform-enter-from,
  .fade-transform-leave-to {
    transform: translateX(50px) scale(0.8);
    opacity: 0;
  }

  .vab-content {
    min-height: calc(
      100vh - @vab-header-height - @vab-padding - @vab-padding - @vab-padding -
        @vab-padding
    ) !important;
    padding: @vab-padding;
    margin: @vab-margin;
    background: #fff;
    .error-container {
      height: calc(
        100vh - @vab-header-height - @vab-padding - @vab-padding - @vab-padding -
          @vab-padding - @vab-padding - @vab-margin
      ) !important;
    }
  }
</style>
