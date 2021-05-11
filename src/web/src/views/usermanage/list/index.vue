<template>
  <a-table :columns="columns" :data-source="data">
    <template #name="{ text }">
      <a>{{ text }}</a>
    </template>
    <template #customTitle>
      <span>
        <smile-outlined />
        会员号
      </span>
    </template>
  </a-table>
</template>

<script>
  export default {
    data() {
      return {
        columns: [
          {
            dataIndex: '会员号',
            key: 'userid',
            slots: { title: 'customTitle', customRender: '会员号' },
          },
          {
            title: '用户姓名',
            dataIndex: 'username',
            key: 'username',
          },
          {
            title: '用户性别',
            dataIndex: 'sex',
            key: 'sex',
          },
          {
            title: '手机号',
            key: 'phone',
            dataIndex: 'phone',
            slots: { customRender: 'tags' },
          },
          {
            title: 'Action',
            key: 'action',
            slots: { customRender: 'action' },
          },
        ],
      }
    },
  }
</script>

<style></style>
