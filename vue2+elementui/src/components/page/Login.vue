<template>
  <div class="login-wrapper">
    <div class="login-main">
      <h3 class="login-title">生产执行管理系统</h3>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
        <el-form-item prop="uid">
          <el-input v-model="ruleForm.uid" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="upassword">
          <el-input type="password" placeholder="密码" v-model="ruleForm.upassword"
            @keyup.enter.native="postForm(ruleForm)"></el-input>
        </el-form-item>
        <!-- <el-form-item prop="code">
          <el-input type="text" placeholder="请输入验证码" class="code"  v-model="ruleForm.code"></el-input>
          <span>
          <img src="data:image/png;base64,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"
   alt="验证码" >
          </span>
        </el-form-item> -->
        <el-form-item>
          <el-button class="login-btn" type="primary" @click="postForm(ruleForm)">登录</el-button>
          <!-- <p class="login-tip">提示：用户名和密可以随便填。</p> -->
        </el-form-item>
      </el-form>
    </div>
    <canvas id="canvas" class="login-anim"></canvas>
  </div>
</template>

<script>
export default {
  name: 'login',
  data: function () {
    return {
      ruleForm: {
        uid: '',
        uname: '',
        upassword: '',
        code: ''
      },
      PositionForm: [],
      rules: {
        uid: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        upassword: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    //登录验证
    postForm(LoginForm) {
      this.$axios({
        method: 'post',
        url: '/login',
        data: {
          uid: LoginForm.uid,
          upassword: LoginForm.upassword
        }
      }).then(res => {
        if (res.data.messagestate == 'false') {
          this.$message.success('密码错误');
        } else {
          console.log(res.data.message);
          localStorage.setItem('userid', this.ruleForm.uid);
          localStorage.setItem('username', res.data.message.uname);
          localStorage.setItem('uposition', res.data.message.uposition);
          // 记录该人权力
          this.$axios({
            method: 'post',
            url: '/sets/' + localStorage.getItem('uposition')
          }).then(res => {
            this.$store.state.PositionForm = res.data.message;
            this.$router.push('/index');
          }, err => {
            console.log(err);
          })
        }
      }, err => {
        console.log(err);
      })
    }
  },
  mounted() {
    // 实现页面底部波纹特效
    let canvas = document.getElementById('canvas');
    let ctx = canvas.getContext('2d');
    canvas.width = canvas.parentNode.offsetWidth;
    canvas.height = canvas.parentNode.offsetHeight;
    console.log(canvas.width, canvas.height);

    // 如果浏览器支持requestAnimFrame则使用requestAnimFrame否则使用setTimeout
    window.requestAnimFrame = (function () {

      return window.requestAnimationFrame
        || window.webkitRequestAnimationFrame
        || window.mozRequestAnimationFrame
        || function (callback) {

          window.setTimeout(callback, 1000 / 60);
        };
    })();

    // 波浪大小
    let boHeight = 40;// canvas.height / 10;
    let posHeight = canvas.height - 150;// canvas.height / 1.2;

    // 初始角度为0
    let step = 0;
    // 定义三条不同波浪的颜色
    let lines = ["rgba(69, 159, 117, 0.1)", "rgba(95, 170, 135, 0.6)", "rgba(69, 159, 117, 0.4)"];

    function loop() {

      ctx.clearRect(0, 0, canvas.width, canvas.height);
      step++;
      // 画3个不同颜色的矩形
      for (let j = lines.length - 1; j >= 0; j--) {

        ctx.fillStyle = lines[j];

        //每个矩形的角度都不同，每个之间相差45度
        let angle = (step + j * 70) * Math.PI / 180; // 50
        let deltaHeight = Math.sin(angle) * boHeight;
        let deltaHeightRight = Math.cos(angle) * boHeight;
        ctx.beginPath();
        ctx.moveTo(0, posHeight + deltaHeight);
        ctx.moveTo(0, posHeight + deltaHeight);
        ctx.bezierCurveTo(canvas.width / 2, posHeight + deltaHeight - boHeight, canvas.width / 2, posHeight + deltaHeightRight - boHeight, canvas.width, posHeight + deltaHeightRight);
        ctx.lineTo(canvas.width, canvas.height);
        ctx.lineTo(0, canvas.height);
        ctx.lineTo(0, posHeight + deltaHeight);
        ctx.closePath();
        ctx.fill();
      }
      requestAnimFrame(loop);
    }
    loop();
  }
}
</script>

<style scoped lang="less">
.login-wrapper {
  height: 100%;
}

#canvas {
  width: 100%;
}

.login-main {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 320px;
  height: 350px;
  padding: 20px 35px;
  border: 1px solid #3e8f69;
  margin: -185px 0 0 -160px;

  &::before,
  &::after {
    position: absolute;
    height: 12px;
    content: "";
  }

  &::before {
    left: 4px;
    right: 4px;
    top: -12px;
    z-index: 2;
    background-color: #8fc5ac;
  }

  &::after {
    left: 10px;
    right: 10px;
    top: -22px;
    z-index: 1;
    background-color: #6ab291;
  }
}

.login-title {
  padding-bottom: 15px;
  border-bottom: 2px solid @mainColor;
  margin: 15px 0 20px 0;
  color: #555;
  text-align: center;
  font-size: 30px;
}

.login-btn {
  width: 100%;
  height: 36px;
  margin-top: 10px;
  font-size: 16px;
}

.login-tip {
  color: #459f75;
  font-size: 12px;
  line-height: 30px;
}

.login-anim {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: -1;
}

img {
  vertical-align: bottom;
  -webkit-appearance: none;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  display: inline-block;
  height: 36px;
  outline: none;
  width: 48%;
}

.code {
  width: 50%;
}</style>
