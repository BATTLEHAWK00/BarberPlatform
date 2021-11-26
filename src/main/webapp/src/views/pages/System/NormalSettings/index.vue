<template>
  <a-tabs default-active-key="1">
    <a-tabs-tab-pane key="1" tab="短信发送设置">
      <msg-settings></msg-settings>
    </a-tabs-tab-pane>
    <a-tabs-tab-pane key="2" tab="邮箱设置">
      <mail-settings></mail-settings>
    </a-tabs-tab-pane>
  </a-tabs>
</template>

<script>
  import MailSettings from './components/MailServiceSettings'
  import MsgSettings from './components/MsgServiceSettings'

  export default {
    name: 'NormalSettings',
    components: {
      MailSettings,
      MsgSettings,
    },
  }
</script>

<style scoped></style>
