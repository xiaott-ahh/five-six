<template>
  <div class="login-form">
    <el-form :model="loginForm" label-position="top" ref="loginForm" label-width="100px" class="login">
      <el-form-item label="用户名/邮箱">
        <el-input size="small" type="text" v-model="loginForm.userName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input size="small" type="password" v-model="loginForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-button style="padding-left: 20px" type="text" @click="forgetPassword">忘记密码？</el-button>
      <el-checkbox size="medium" style="padding-left: 120px;color: white" v-model="checked">记住我</el-checkbox>
      <el-form-item style="padding-left: 100px">
        <el-button  class="submit" size="medium" round type="primary" @click="login">提交</el-button>
      </el-form-item>
      <el-divider></el-divider>
      <el-form-item>
        <a class="login-way" href="/">
          <img src="../../assets/ico/wechat.png" style="object-fit: fill"/>
        </a>
        <a class="login-way" href="https://baidu.com">
          <img src="../../assets/ico/github.png" style="object-fit: fill"/>
        </a>
        <a class="login-way" href="https://baidu.com">
          <img src="../../assets/ico/weibo.png" style="object-fit: fill"/>
        </a>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data () {
    return {
      loginForm: {
        userName: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      this.$axios
          .post('/login', {
            userName: this.loginForm.userName,
            password: this.loginForm.password
          },)
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              alert(successResponse.data.message)
            }
            if (successResponse.data.code === 400) {
              alert(successResponse.data.message)
            }
          })
          .catch(failResponse => {
            alert(failResponse.data);
          })
    }
  }
}
</script>

<style scoped>
  .login-form {
    width:300px;
    padding-top: 10px;
  }
  .el-form-item {
    padding-left: 20px;
    padding-right: 20px;
  }
  /deep/ .el-form-item__label {
    color: white;
  }

  .submit.el-button {
    width: 100px;
  }
  a.login-way {
    padding: 23px;
  }

</style>