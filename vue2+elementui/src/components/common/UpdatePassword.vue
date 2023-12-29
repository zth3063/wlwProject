<template>
  <el-dialog title="修改密码" :visible.sync="visible" :append-to-body="true">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="账号">
        <span>{{ dataForm.uid }}</span>
      </el-form-item>
      <el-form-item label="姓名" prop="uname">
        <el-input type="name" v-model="dataForm.uname"></el-input>
      </el-form-item>
      <el-form-item label="原密码" prop="uoldpassword">
        <el-input type="password" v-model="dataForm.uoldpassword"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="uPassword">
        <el-input type="password" v-model="dataForm.uPassword"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="uconfirmPassword">
        <el-input type="password" v-model="dataForm.uconfirmPassword"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data() {
    var validateConfirmPassword = (rule, value, callback) => {
      if (this.dataForm.newPassword !== value) {
        callback(new Error('确认密码与新密码不一致'))
      } else {
        callback()
      }
    }
    return {
      visible: false,
      dataForm: {
        uid: '',
        uname: '',
        uoldpassword: '',
        uPassword: '',
        uconfirmPassword: ''
      },
      dataRule: {
        password: [
          { required: true, message: '原密码不能为空', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '新密码不能为空', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '确认密码不能为空', trigger: 'blur' },
          { validator: validateConfirmPassword, trigger: 'blur' }
        ]
      }
    }
  },
  computed: {

  },
  methods: {
    // 初始化
    init() {
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        this.dataForm.uid = localStorage.getItem('userid')
        this.dataForm.uname = localStorage.getItem('username')
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$axios({
        method: 'post',
        url: '/changeUser',
        data: {
          uid: this.dataForm.uid,
          uname: this.dataForm.uname,
          upassword: this.dataForm.uPassword,
          uposition: localStorage.getItem('uposition'),
          uoldpassword: this.dataForm.uoldpassword,
        }
      }).then(res => {
        if (res.data.messagestate == false) {
          alert("密码错误")
        } else {
          console.log(res.data);
          localStorage.setItem('username', this.dataForm.uname,);
          this.$message.success("密码修改成功")
        }
      }, err => {
        console.log(err);
        alert("密码修改失败")
      })
    }
  }
}
</script>

