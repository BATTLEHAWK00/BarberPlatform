<template>
  <div>
    <a-row>
      <a-col :span="24">
        <a-carousel autoplay>
          <div><h3>1</h3></div>
          <div><h3>2</h3></div>
          <div><h3>3</h3></div>
          <div><h3>4</h3></div>
        </a-carousel>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="8">
        <a-alert type="info" :show-icon="false" banner>
          <template #message>
            <h2>Hello</h2>
          </template>
          <template #description>
            <p>欢迎来到理发店管理系统！</p>
            <p>点击左侧导航栏使用本系统。</p>
          </template>
        </a-alert>
      </a-col>
      <a-col :span="16">
        <a-card>col-12</a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
  export default {
    name: 'Index',
  }
</script>
<style scoped>
  .ant-carousel >>> .slick-slide {
    text-align: center;
    height: 140px;
    line-height: 140px;
    background: #81b6d9;
    overflow: hidden;
  }

  .ant-carousel >>> .slick-slide h3 {
    color: #fff;
  }

  .ant-card {
    height: 100%;
  }
</style>
