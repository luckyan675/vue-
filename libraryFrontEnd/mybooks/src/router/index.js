import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/components/page/Login"
import Page1 from "@/components/page/Page1"
import Home from '@/components/page/Home'
import Page2 from '@/components/page/Page2'
import Allbooks from '@/components/page/Allbooks'
import Addbook from '@/components/page/Addbook'
import Reader from '@/components/page/Reader'
Vue.use(Router)

export default new Router({

  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
      {
        path: '/home',
        name: 'Home',
        component: Home
      },
      {
        path: '/Page1',
        name: 'Page1',
        component: Page1
      },
      {
        path: '/Page2',
        name: 'Page2',
        component: Page2
      },
      {
        path: '/Allbooks',
        name: 'Allbooks',
        component: Allbooks
      },
      {
        path: '/Addbook',
        name: 'Addbook',
        component: Addbook
      },
      {
        path: '/Reader',
        name: 'Reader',
        component: Reader
      }
    ]
})
