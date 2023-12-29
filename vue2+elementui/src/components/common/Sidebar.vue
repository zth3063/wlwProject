<template>
  <el-aside width="auto" height="auto">
    <el-menu :collapse="isCollapse" :default-active="onRoutes" background-color="#232323" text-color="#ccc"
      active-text-color="#ddd" unique-opened router>
      <template v-for="item in items">
        <template v-if="item.subItems">
          <el-submenu :index="item.path">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <el-menu-item v-for="(subItem, i) in item.subItems" :index="subItem.path" :key="i">
              {{ subItem.title }}
            </el-menu-item>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.path">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </el-aside>
</template>

<script>
import Bus from "./bus";
export default {
  name: "Sidebar",
  data() {
    return {
      isCollapse: false,
      items: [],
      items2: [
        {
          title: '系统首页',
          path: '/index',
          icon: 'el-icon-s-tools',
          rights: 'true'
        },
        {
          title: 'echarts图表',
          path: 'echarts',
          icon: 'el-icon-star-on',
          subItems: [
            {
              title: '简单图表',
              path: '/chart-simple',
              rights: 'true'
            },
            {
              title: '复杂图表',
              path: '/chart-complex',
              rights: 'true'
            }
          ],
          rights: 'false'
        },
        {
          title: '公告管理',
          path: '/tab',
          icon: 'el-icon-message',
          rights: 'logs'
        },
        {
          title: '表格',
          path: '/table',
          icon: 'el-icon-tickets',
          rights: 'false'
        },
        {
          title: '错误页面',
          path: '/error',
          icon: 'el-icon-warning',
          rights: 'false'
        },
        {
          title: '404页面',
          path: '/404',
          icon: 'el-icon-error',
          rights: 'false'
        },
        {
          title: '系统设置',
          path: '/sets',
          icon: 'el-icon-setting',
          rights: 'sets'
        },
        {
          title: '故障分析',
          path: '/echarts',
          icon: 'el-icon-pie-chart',
          rights: 'logs'
        }, {
          title: '技术资料',
          path: '/technologies',
          icon: 'el-icon-folder-opened',
          rights: 'technologies'
        }, {
          title: '日志',
          path: '/logs',
          icon: 'el-icon-date',
          rights: 'logs'
        },{
          title: '设备管理',
          path: '/devices',
          icon: 'el-icon-cpu',
          rights: 'devices'
        },{
          title: '订单管理',
          path: '/orders',
          icon: 'el-icon-notebook-2',
          rights: 'orders'
        }
      ]
    }
  },
  computed: {
    onRoutes() {

      return this.$route.fullPath;
    }
  },
  created() {
    // 通过 event bus进行组件间的通信 来折叠和展开侧边栏
    Bus.$on('collapse', isCollapse => {

      this.isCollapse = isCollapse;
    });
  },
  mounted() {
    // 记录该人权力
    this.$axios({
      method: 'post',
      url: '/sets/' + localStorage.getItem('uposition')
    }).then(res => {
      this.$store.state.PositionForm = res.data.message;
      //this.$router.push('/');
      let a = 0;
      for (let i in this.$store.state.PositionForm) {
        for (let j in this.items2) {
          if (a == 0 && this.items2[j].rights == 'true') {
            this.items.push(this.items2[j])
          }
          else if (this.items2[j].rights == i && this.$store.state.PositionForm[i] != "无") {
            this.items.push(this.items2[j])
          }
        }
        a = 1;
      }
    }, err => {
      console.log(err);
    })
  }
}
</script>

<style scoped lang="less">
.el-menu {
  height: 100%;
  border: none;

  &:not(.el-menu--collapse) {
    width: 200px;
  }
}

.el-menu-item.is-active {
  border-left: 3px solid @mainColor;
  background-color: #171717 !important;
}
</style>
