<template>
  <div id="app">
    <nav-menu></nav-menu>
    <router-view/>
  </div>
</template>

<script>
import NavMenu from "@/components/common/NavMenu";
export default {
  name: 'App',
  components : {NavMenu}
}
</script>

<style scoped>
  #app{
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

</style>
