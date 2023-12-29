<template>
  <div class="home">
    <div class="stat">
      <!--用户卡片-->
      <div class="stat-user">
        <div class="stat-user__title">
          生产执行管理系统
        </div>
        <div class="stat-user__detail">
          <p>欢迎您，{{ username }}</p>
          <p>当前时间：{{ nowTime }}</p>
        </div>
        <img class="stat-user__portrait" src="static/portrait.jpg" alt="">
      </div>
      <!--系统统计数据概览-->
      <div class="stat-info">
        <el-row :gutter="20" v-for="(info, index) in stat" :key="index">
          <el-col :span="8" v-for="(item, index2) in info" :key="index2">
            <div class="stat-info__item">
              <div class="stat-info__icon" :style="{ 'background-color': item.bgColor }">
                <i :class="item.icon"></i>
              </div>
              <div class="stat-info__detail">
                <p class="stat-info__total">{{ item.total }}</p>
                <p class="stat-info__title">{{ item.title }}</p>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <el-row :gutter="20" class="margin-t-20 list">
      <!--待办事项-->
      <el-col :span="12">
        <el-card>
          <div slot="header">
            <span><i class="el-icon-tickets margin-r-5"></i>待办事项</span>
            <!-- <i class="el-icon-plus" @click="addNewTodoItem" title="新增"></i> -->
          </div>
          <p v-for="(item, i) in todoList" :key="i">
            <el-checkbox v-model="item.isChecked"></el-checkbox>
            <span :class="{ active: item.isChecked }">{{ i + 1 > 9 ? i + 1 : '0' + (i + 1) }}-{{ item.title }}</span>
          </p>
        </el-card>
      </el-col>
      <!--最新消息-->
      <el-col :span="12">
        <el-card>
          <div slot="header">
            <span><i class="el-icon-news margin-r-5"></i>最新公告</span>
          </div>
          <p v-for="(item, i) in latestNewList" :key="i">
            <span class="latest-new-list__time"><i class="el-icon-time margin-r-5"></i>{{ item.time }}：</span>
            <span>{{ item.title }}</span>
          </p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Util from '@/assets/js/util';
let todoList = [], latestNewList = [];
// for (let i = 0; i < 10; i++) {

//   todoList.push({
//     title: `今天需要做的待办事项咯~~~`,
//     isChecked: false
//   });
// }
export default {
  name: "Home",
  data() {
    return {
      stat: [
        [
          {
            icon: 'el-icon-user-solid',
            title: '公司员工总数',
            total: 0,
            bgColor: '#ebcc6f'
          },
          {
            icon: 'el-icon-document-checked',
            title: '历史订单总数',
            total: 19,
            bgColor: '#3acaa9'
          },
          {
            icon: 'el-icon-s-order',
            title: '公司设备总数',
            total: 190857,
            bgColor: '#67c4ed'
          }
        ],
        [
          {
            icon: 'el-icon-user',
            title: '今日打卡人数',
            total: 9397,
            bgColor: '#af84cb'
          },
          {
            icon: 'el-icon-document',
            title: '进行中的订单',
            total: 9097,
            bgColor: '#67c4ed'
          },
          {
            icon: 'el-icon-s-claim',
            title: '空闲可用设备',
            total: 397,
            bgColor: '#ebcc6f'
          }
        ]
      ],
      username: localStorage.getItem('username'),
      nowTime: new Date().Format('yyyy-MM-dd hh:mm:ss'),
      todoList,
      latestNewList: [],
      position: '',
      today: '',
    }
  },
  methods: {
    getCurrentDate() {
      let now = new Date();
      let year = now.getFullYear();
      let month = now.getMonth() + 1;
      let day = now.getDate();
      return year + "-" + month + "-" + day;
    },
    setNowTime() {

      setInterval(() => {

        this.nowTime = new Date().Format('yyyy-MM-dd hh:mm:ss')
      }, 1000);
    },
    addNewTodoItem() {

      this.$prompt('请输入待办事项主题', '新增待办事项', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {

        this.$message({
          type: 'success',
          message: '新增待办事项成功'
        });
        this.todoList.unshift({
          title: value,
          isChecked: false
        });
      }).catch(() => {

        this.$message({
          type: 'info',
          message: '取消新增待办事项'
        });
      });
    }
  },
  mounted() {
    this.today = this.getCurrentDate();
    this.setNowTime();
    this.$axios({
      method: 'post',
      url: '/FindAllNotices',
    }).then(res => {
      for (let i = 0; i < res.data.message.length; i++) {
        if (res.data.message[i].noticeState == 1) {
          this.latestNewList.push({ time: res.data.message[i].noticeDate, title: res.data.message[i].noticeContent });
        }
      }
    }, err => {
      console.log(err);
    });
    //员工总数
    this.$axios({
      method: 'post',
      url: '/CountUser',
    }).then(res => {
      this.stat[0][0].total = res.data.message;
    }, err => {
      console.log(err);
    });
    //打卡总数
    this.$axios({
      method: 'post',
      url: '/CountUserDay',
    }).then(res => {
      this.stat[1][0].total = res.data.message;
    }, err => {
      console.log(err);
    });
    //历史订单数
    this.$axios({
      method: 'post',
      url: '/ordersFindallCount',
    }).then(res => {
      this.stat[0][1].total = res.data.message;
    }, err => {
      console.log(err);
    });
    //进行中的订单
    this.$axios({
      method: 'post',
      url: '/FindOrdersingCount',
      data: {
        startDate: this.today
      }
    }).then(res => {
      this.stat[1][1].total = res.data.message;
    }, err => {
      console.log(err);
    });
    //公司设备
    this.$axios({
      method: 'post',
      url: '/DevicesAllCount',
    }).then(res => {
      this.stat[0][2].total = res.data.message;
    }, err => {
      console.log(err);
    });
    //空闲设备
    this.$axios({
      method: 'post',
      url: '/DevicesAllCountDeviceStatus',
    }).then(res => {
      this.stat[1][2].total = res.data.message;
    }, err => {
      console.log(err);
    });

    this.position = localStorage.getItem('uposition');
    this.todoList = [];
    //管理员
    if (this.position == '管理员') {
      this.$axios({
        method: 'post',
        url: '/FindOrdersing',
        data: {
          startDate: this.today,
        }
      }).then(res => {
        for (let i = 0; i < res.data.message.length; i++) {
          this.todoList.push({ title: "正在进行的订单：订单号[" + res.data.message[i].oid + "]", isChecked: false })
        }
      }, err => {
        console.log(err);
      });
    }
    //技术员
    if (this.position == '技术员') {
      this.$axios({
        method: 'post',
        url: '/DevicesAllDeviceStatus'
      }).then(res => {


        for (let i = 0; i < res.data.message.length; i++) {
          let keys = Object.keys(res.data.message[i]);
          let values = Object.values(res.data.message[i]);
          this.todoList.push({ title: "可用数量:["+ values[0] + "]"+"---"+ "可用设备类型:[" + keys[0]   + "]" , isChecked: false })
        }
      }, err => {
        console.log(err);
      });
    }
    //工程师
    if (this.position == '工程师') {
      this.$axios({
        method: 'post',
        url: '/DevicesAllFault',
        data: {
          startDate: this.today,
        }
      }).then(res => {
        for (let i = 0; i < res.data.message.length; i++) {
          this.todoList.push({ title: "待维修的设备：设备号[" + res.data.message[i].deviceId + "]", isChecked: false })
        }
      }, err => {
        console.log(err);
      });
    }
    //经理
    if (this.position == '经理') {
      this.$axios({
        method: 'post',
        url: '/NewordersFindall',
        data: {
          startDate: this.today,
        }
      }).then(res => {
        for (let i = 0; i < res.data.message.length; i++) {
          this.todoList.push({ title: "待安排订单：订单号[" + res.data.message[i].oid + "]", isChecked: false })
        }
      }, err => {
        console.log(err);
      });
    }
    //厂商
    if (this.position == '厂商') {
      this.$axios({
        method: 'post',
        url: '/FindAllTechnologiesIsNull',
      }).then(res => {
        for (let i = 0; i < res.data.message.length; i++) {
          this.todoList.push({ title: "待补充技术资料:[" + res.data.message[i].techId + "]" + ":"+ res.data.message[i].manual +"", isChecked: false })
        }
      }, err => {
        console.log(err);
      });
    }
  }
}
</script>

<style scoped lang="less">
.home {
  height: calc(~'100% - 40px');
}

.stat {
  display: flex;
  height: 230px;
}

.stat-user {
  position: relative;
  width: 300px;
  background-color: @boxBgColor;
  box-shadow: 2px 2px 5px #ccc;
}

.stat-user__title {
  height: 100px;
  background-color: @mainColor;
  color: white;
  font-size: 18px;
  font-weight: bold;
  text-align: center;
  line-height: 70px;
}

.stat-user__detail {
  padding-top: 50px;
  color: #666;
  font-size: 13px;
  text-align: center;
}

.stat-user__portrait {
  position: absolute;
  top: 50%;
  left: 50%;
  .circle(80px);
  border: 3px solid white;
  box-shadow: 0 0 5px #ccc;
  margin-top: -55px;
  margin-left: -40px;
}

.stat-info {
  flex: 1;
  margin-left: 20px;
}

.el-row+.el-row {
  margin-top: 10px;
}

.stat-info__item {
  display: flex;
  height: 110px;
  box-shadow: 2px 2px 5px #ccc;
  background-color: @boxBgColor;
}

.stat-info__icon {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80px;
  color: white;

  [class*='el-icon'] {
    font-size: 50px;
  }
}

.stat-info__detail {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.stat-info__total {
  color: #0085d0;
  font-size: 27px;
  font-weight: bold;
}

.stat-info__title {
  color: #666;
  font-size: 12px;
}

.list {
  display: flex;
  height: calc(~'100% - 250px');

  .el-col {
    height: 100%;
  }
}

.el-card {
  height: 100%;
  background-color: @boxBgColor;

  .el-icon-plus {
    float: right;
    color: @dangerColor;
    font-weight: bold;
    cursor: pointer;
  }
}

.el-card__header span {
  color: @subColor;
}

.el-card__body {
  p {
    border-bottom: 1px solid #e5e5e5;
    color: #555;
    font-size: 15px;
    line-height: 30px;
  }

  .active {
    color: #666;
    text-decoration: line-through;
  }
}

.latest-new-list__time {
  color: #666;
  font-size: 14px;
}
</style>
