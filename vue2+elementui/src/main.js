import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/icon.css'
import '@/assets/css/common.less';
import '@/assets/css/variable.less';
import '@/assets/css/theme/459f75/index.css';
// import 'element-ui/lib/theme-chalk/index.css';
import axios from './axios/axios.js';
import axios2 from './axios/axios2.js';
import echarts from 'echarts';
import store from './store/index.js'
 require('./mock');

Vue.config.productionTip = false;
Vue.use(ElementUI, {size: 'medium'});
Vue.prototype.$axios = axios; // axios不能使用use
Vue.prototype.$axios2 = axios2; // mock用的
Vue.prototype.$echarts = echarts;

// 使用router.beforeEach注册一个全局前置守卫，对路由进行权限跳转
router.beforeEach((to, from, next) => {

  // 未匹配到路由时 跳转到error页面
  if (0 === to.matched.length) {

    next('/error');
    return false;
  }

  const role = localStorage.getItem('username');
  if (!role && to.path !== '/login') {

    next('/login');
  } else if (to.meta.permission) {

    // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    role === 'admin' ? next() : next('/error');
  } else {

    // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
    if(navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {

      Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
        confirmButtonText: '确定'
      });
    } else {

      next();
    }
  }
});

new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
});
