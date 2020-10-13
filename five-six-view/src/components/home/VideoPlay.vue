<template>
    <div class="video-container" >
      <video id="v1" autoplay loop muted poster="../../assets/images/home-page-content.png">
        <source src="../../assets/hero.mp4" type="video/mp4"/>
      </video>
    </div>
</template>

<script>

export default {
  name: "VideoPlay"
}
</script>

<style scoped>

    video{
      position: fixed;
      /*object-fit: fill;*/
      right: 0;
      bottom: 0;
      height: auto;
      width: auto;
      min-height: 100%;
      min-width: 100%;
      z-index: -100;
      background-size: cover;
      display: block;
      /*加滤镜*/
      /*filter: blur(15px); //背景模糊设置 */
      /*-webkit-filter: grayscale(100%);*/
      /*filter:grayscale(100%); //背景灰度设置*/
    }
    source{
      min-width: 100%;
      min-height: 100%;
      height: auto;
      width: auto;
    }
</style>