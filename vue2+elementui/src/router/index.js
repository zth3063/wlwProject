import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

export default new Router({
  // mode: "history",
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/',
      component: () => import('../components/common/Base.vue'),
      meta: {
        title: '公共部分'
      },
      children: [
        {
          path: '/index',
          component: () => import('../components/page/Home.vue'),
          meta: {
            title: '系统首页'
          }
        },
        {
          path: '/chart-simple',
          component: () => import('../components/page/EchartsSimple.vue'),
          meta: {
            title: '简单图表'
          }
        },
        {
          path: '/chart-complex',
          component: () => import('../components/page/EchartsComplex.vue'),
          meta: {
            title: '复杂图表'
          }
        },
        {
          path: '/tab',
          component: () => import('../components/page/Tab.vue'),
          meta: {
            title: '公告管理'
          }
        },
        {
          path: '/table',
          component: () => import('../components/page/Table.vue'),
          meta: {
            title: '表格'
          }
        },
        {
          path: '/sets',
          component: () => import('../components/page/Sets.vue'),
          meta: {
            title: '系统设置'
          }
        },
        {
          path: '/echarts',
          component: () => import('../components/page/Echarts.vue'),
          meta: {
            title: '故障分析'
          }
        },
        {
          path: '/technologies',
          component: () => import('../components/page/Technologies.vue'),
          meta: {
            title: '技术资料'
          }
        },
        {
          path: '/logs',
          component: () => import('../components/page/Logs.vue'),
          meta: {
            title: '日志'
          }
        },
        {
          path: '/devices',
          component: () => import('../components/page/Devices.vue'),
          meta: {
            title: '设备管理'
          }
        },
        {
          path: '/orders',
          component: () => import('../components/page/orders.vue'),
          meta: {
            title: '订单管理'
          }
        }
      ]
    },
    {
      path: '/login',
      component: () => import('../components/page/Login.vue')
    },
    {
      path: '/error',
      component: () => import('../components/page/Error.vue')
    },
    {
      path: '/404',
      component: () => import('../components/page/404.vue')
    }
  ]
})
