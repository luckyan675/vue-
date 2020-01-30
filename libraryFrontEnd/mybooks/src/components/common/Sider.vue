<template>
    <div class="sider">
        <!-- <ul>
            <li class="menu-item" v-for="item in menu" :key="item.name">
                <router-link :to="item.path">{{item.name}}</router-link>
            </li>
        </ul> -->
        <!-- 用router-link to 地址 点击以后改变地址  可以用索引找到 -->

        <ul>
            <li class="menu-item" v-for="item in menu" :key="item.name">
                <div></div>
                <router-link :to="item.path">{{item.name}}</router-link>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name:'Sider',
    data(){
        return{
            menu:[
            {path:"/",name:'经典推荐'},
            {path:"/Page1",name:'书籍分类'},
            {path:"/Page2",name:'借出管理'},
            {path:"/Page3",name:'归还管理'},
            {path:"/Page4",name:'个人中心'},
            {path:"/Page5",name:'维护中心'},
            {path:"/Page6",name:'关于我们'},
        ]
        }
    },

}
</script>
<style>
    body, html,ul{
        margin: 0;
        padding:0;
    }
    html{
        font-size: 100px;
    }
    .sider{
        position: fixed;
        background: #333;
        width: 2rem;
        height: 90vh;
        margin-top: 10vh;
        z-index: 999;                                                              
    }
    .sider ul{
        position: relative;
        font-size: 0;
    }
    ul li.menu-item{
        list-style: none;
        font-size: 0.2rem;
        line-height: 0.8rem;
        display: inline-block;
        width: 100%;
        text-align: center;
        color: #f8f8f8;
    }
    ul li.menu-item:hover{
        background: #009688;
    }
    li.menu-item:first-child{
        margin-top:0.3rem;
    }
    .sider ul li a{
        text-decoration: none;
        color: #f8f8f8;
    }
</style>