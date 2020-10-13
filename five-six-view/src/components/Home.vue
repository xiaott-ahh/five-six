<template>
    <div class="home">
      <div class="home-view">
        <video-play></video-play>
        <page-content></page-content>
        <router-view/>
      </div>
    </div>
</template>

<script>
import VideoPlay from "@/components/home/VideoPlay";
import PageContent from "@/components/home/PageContent";
export default {
  name: 'Home',
  components: {PageContent, VideoPlay}
}
</script>

<style scoped>
  .home-view{
    /*position: relative;*/
    width: 100%;
    height: 100%;
  }
</style>
