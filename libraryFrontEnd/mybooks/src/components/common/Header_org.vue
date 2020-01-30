
<template>
    <div class="header">
        <!-- 折叠按钮 -->
        <i class="btn1 iconfont icon-gongnengcaidan menu f8"></i>

        <div class="logo f8">图书管理系统</div>
        <div class="header-con ">
            
            <!-- 全屏 -->
            <i class="iconfont icon-quanping menu f8 pd"></i>
            
            <!-- 消息提醒 -->
            <i class="iconfont icon-xiazai41 menu f8 pd"></i>
            <!-- 用户头像 -->
            <div class="user-con pd">
                <img src="../../assets/user.jpg"/>
            </div>
            <!-- 下拉菜单 -->
             <div class="username pd">栗子严</div>
             <!-- <div>
                 <span>{{username}}</span>
             </div> -->
        </div>
        
    </div>
</template>

<script>

export default {
    name: 'Header',
    data(){
        return{

        }
    },
    component:{
        username(){
            let username = localStorage.getItem('')
        }
    }
}
</script>

<style>
/* 公共样式 */
    .header ,body ,html{
        padding: 0;
        margin: 0;
    }
    .left{
        float: left;
    }
    .right{
        float: right;
    }
    .f8{
        color: #f8f8f8;
    }
    .pd{
        padding: 0 0.15rem;
    }
    .inline{
        display: inline-block;
    }
    .header, .header-con, .user-con{
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    /* 头部 */
    .header{
        position: fixed;
        overflow: hidden;
        background: #009688;
        width: 100%;
        height: 10vh; 
        z-index: 999;
        padding: 0 0.8rem;
        box-sizing: border-box
    }
    .btn1 {
    /* padding-right:0.5rem; */
    cursor: pointer;
    line-height: 70px;
    left: 0.8rem;

    }
    .logo{
        font-size: 30px;
        width: 300px;
        
    }
    .menu{
        font-size: 30px;
    }
    .user-con img{
        width: 50px;
        height: 50px;
    }
    .username{
        font-size: 0.16rem;
        color: #f8f8f8;
    }
</style>