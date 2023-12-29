<template>
  <div>
    <el-tabs v-model="activeTab">
      <el-tab-pane :label="`草稿(${read.length})`" name="first">
        <el-table :data="read" :show-header="showHeader" :row-style="{ 'background': '#f5f5f5' }">
          <el-table-column prop="noticeDate" label="日期" width="200">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 5px">{{ scope.row.noticeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="noticeContent" label="消息内容">
          </el-table-column>
          <el-table-column width="100" label="操作" align="center">
            <template slot-scope="scope">
              <el-button @click="handleDel(scope.$index)" type="danger">发布</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button @click="handleDelAll" type="danger" class="btn-batch">发布全部</el-button>
        <div style="margin: 20px 0;">
          <el-row :gutter="1">
            <el-col :span="50">
              <el-select v-model="value" placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="50">
              <el-input class="el-textarea__inner" style="width: 300px;padding: 0%;" type="textarea" autosize
                placeholder="编辑新公告" v-model="inputtext">
              </el-input>
            </el-col>
          </el-row>
          <el-button @click="NewNotices" type="danger" class="btn-batch">编辑完成</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane :label="`公告板(${trash.length})`" name="second">
        <el-table :data="trash" :show-header="showHeader" :row-style="{ 'background': '#f5f5f5' }">
          <el-table-column prop="noticeDate" label="日期" width="200">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 5px">{{ scope.row.noticeDate }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="noticeContent" label="消息内容">
          </el-table-column>
          <el-table-column width="130" label="操作" align="center">
            <template slot-scope="scope" width="130" align="center">
              <el-button @click="handleRestore(scope.$index)" circle  type="default" size="small">撤回</el-button>
              <el-button @click="handleRestore2(scope.$index)" circle  type="danger" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button @click="handleRestoreAll" class="btn-batch" type="warning">全部撤回</el-button>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "Tab",
  data() {
    return {
      today: '',
      value: '【系统通知】',
      options: [{
        value: '【系统通知】',
        label: '【系统通知】'
      }, {
        value: '【天气预报】',
        name: '【天气预报】',
      }
      ],
      inputtext: '',
      activeTab: 'first',
      showHeader: false,
      read: [],
      trash: []
    };
  },
  methods: {
    NewNotices() {
      this.read.push({ noticeDate: this.today, noticeContent: this.value + this.inputtext, noticeState: 0 });
      this.$axios({
        method: 'post',
        url: '/InsertNotices',
        data: {
          noticeDate: this.today,
          noticeContent: this.value + this.inputtext,
          noticeState: 0
        }
      }).then(res => {
      }, err => {
        console.log(err);
      })
    },
    handleDel(index) {
      this.read[index].noticeState = 1;
      this.$axios({
        method: 'post',
        url: '/UpdateNotices',
        data: this.read[index]
      }).then(res => {
        this.trash.push(this.read.splice(index, 1)[0]);
      }, err => {
        console.log(err);
      })
    },
    handleDelAll() {
      for (let i = 0; i < this.read.length; i++) {
        this.read[i].noticeState = 1;
        this.$axios({
          method: 'post',
          url: '/UpdateNotices',
          data: this.read[i]
        }).then(res => {
        }, err => {
          console.log(err);
        })
      }
      this.trash = this.trash.concat(this.read.splice(0));
    },
    handleRestore(index) {
      this.trash[index].noticeState = 0;
      this.$axios({
        method: 'post',
        url: '/UpdateNotices',
        data: this.trash[index]
      }).then(res => {
        this.read.push(this.trash.splice(index, 1)[0]);
      }, err => {
        console.log(err);
      })
    },
    handleRestore2(index) {
      this.$axios({
        method: 'post',
        url: '/DeleteNotices',
        data: this.trash[index]
      }).then(res => {
        this.$message.success('删除成功');
        this.trash.splice(index, 1)[0]
      }, err => {
        console.log(err);
      })
    },
    handleRestoreAll() {
      for (let i = 0; i < this.trash.length; i++) {
        this.trash[i].noticeState = 0;
        this.$axios({
        method: 'post',
        url: '/UpdateNotices',
        data: this.trash[i]
      }).then(res => {
      }, err => {
        console.log(err);
      })
      }
      this.read = this.read.concat(this.trash.splice(0));
    },
    getCurrentDate() {
      let now = new Date();
      let year = now.getFullYear();
      let month = now.getMonth() + 1;
      let day = now.getDate();
      return year + "-" + month + "-" + day;
    },
  },
  mounted() {
    this.today = this.getCurrentDate();
    this.$axios({
      method: 'post',
      url: '/FindAllNotices',
    }).then(res => {
      for (let i = 0; i < res.data.message.length; i++) {
        if (res.data.message[i].noticeState == 0) {
          this.read.push(res.data.message[i]);
        } else {
          this.trash.push(res.data.message[i]);
        }
      }
    }, err => {
      console.log(err);
    })
  }
}
</script>

<style scoped lang="less">
.el-tab-pane {
  padding: 20px 0;
  .border-radius;
}

.el-input__inner {
  height: 37px !important;
}

.btn-batch {
  margin: 20px 0 0 10px;
}

.el-textarea__inner {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
</style>
