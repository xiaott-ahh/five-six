<template>
  <div class="nav" style="padding: 0;height: 60px">
    <div class="nav-logo" style="width: 20%;">
      <a class="logo" href="/">
        <img src="../../assets/images/logo.png" style="object-fit: fill"/>
      </a>
    </div>
    <div class="nav-left" style="width: 40%;padding-right: 30px">
      <el-menu
          theme="dark"
          :default-active="activeIndex2"
          mode="horizontal"
          @select="handleSelect"
          background-color=transparent
          text-color=white
          active-text-color=white>
        <el-menu-item index="1">今日放映</el-menu-item>
        <el-menu-item index="2">向我推荐</el-menu-item>
        <el-menu-item index="3">影讯</el-menu-item>
      </el-menu>
    </div>
    <div class="nav-search" style="width: 20%;padding-top: 1px">
      <el-input
          placeholder="快速搜索..."
          prefix-icon="el-icon-search"
          size="mini"
          style="margin-top: 15px;"
          v-model="keywords">
      </el-input>
    </div>
    <div class="nav-sep" style="width: 1%;padding-left: 20px">
      <h3 style="color: white">|</h3>
    </div>
    <div class="nav-right" style="width: 15%">
      <el-menu
          theme="dark"
          class="nav-right"
          :default-active="activeIndex2"
          mode="horizontal"
          @select="handleSelect"
          background-color=transparent
          text-color=white
          active-text-color=white>
        <el-menu-item index="5">注册</el-menu-item>
        <el-submenu index="6" show-timeout="100" hide-timeout="800">
          <template slot="title">登录</template>
          <login></login>
        </el-submenu>
      </el-menu>
    </div>
  </div>
</template>

<script>
import '../../assets/my-ele-css/my-input.css'
import Login from "@/components/common/Login";
export default {
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },

  components: {Login}
}
</script>

<style scoped>
    .nav {
      width:100%;
      height: 60px;
      display: flex;
      display: -webkit-flex;
      /* 从左端开始沿水平轴防止flex item */
      flex-direction: row;
      /* 强制 flex item不换行，沿着同一行堆叠 */
      flex-wrap: nowrap;
      /* flex item在主轴上的对齐方式，这里定义左对齐 */
      justify-content: flex-start;
      /* 定义交叉轴对其方式 */
      align-items: flex-start
    }
    .el-menu {
      border: none !important;
    }
    .el-menu-item:hover {
      background-color: #3a91ba !important;
    }
    .el-submenu:hover {
      background-color: #3a91ba !important;
    }
    /deep/ .el-submenu__title {
      background-color: transparent !important;
    }
    .el-menu-item {
      border-bottom: none !important;
      background-color: transparent !important;
    }
    .logo img{
      background:no-repeat;
    }
</style>

