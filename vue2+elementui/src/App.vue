<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style lang="less">
  html, body, #app {
    width: 100%;
    height: 100%;
  }
  .el-tab-pane, .el-table tr {
    background-color: @boxBgColor;
  }
</style>
