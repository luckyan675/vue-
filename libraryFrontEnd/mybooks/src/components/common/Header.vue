
<template>
  <div class="header">
    <el-menu mode="horizontal" @select="handleSelect">
      <el-menu-item class="libname" index="1">图书管理系统</el-menu-item>
      <el-submenu
        v-for="(item, menu_index) in this.menu_data"
        :key="menu_index"
        :index="menu_index.toString()"
      >
        <template slot="title">{{item.menu}}</template>

        <el-menu-item
          v-for="(sub_item, sub_index) in item.sub_menu"
          :key="sub_index"
          :index="menu_index.toString()+sub_index.toString()"
        >
          <a :href="sub_item.href">{{sub_item.title}}</a>
        </el-menu-item>
      </el-submenu>
      <el-menu-item class="right" index="99">
        <a href="login.html" class="nav-right">{{this.username}}</a>
      </el-menu-item>
      <el-submenu class="right-menu">
         <el-menu-item>
          <a class="nav-right">我的信息</a>
        </el-menu-item>
        <el-menu-item>
          <a class="nav-right">修改密码</a>
        </el-menu-item>
        <el-menu-item>
          <a class="nav-right">退出</a>
        </el-menu-item>

      </el-submenu>
      
    </el-menu>
  </div>
</template>

<script>
import menu from "../../../config/menu";
export default {
  name: "Header",
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      menu_data: menu,
      username: "请登录"
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  mounted() {
    const userName = window.localStorage.getItem("userName");
    this.username = userName;
  },
  component: {
    username() {}
  }
};
</script>

<style>
a {
  text-decoration-line: none;
}
.el-menu--horizontal > .el-menu-item,
.el-menu--horizontal > .el-submenu .el-submenu__title {
  font-size: 0.16rem;
  color: #000;
}
.el-menu--horizontal > .libname {
  font-size: 0.28rem;
}
.header .right {
  position: absolute;
  right: 0.5rem;
  height: 100%;
  display: flex;
  align-items: center;
}
.header .right-menu {
  position: absolute;
  right: 0;
  display: flex;
}
</style>