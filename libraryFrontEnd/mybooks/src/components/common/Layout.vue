<template>
    <div>
        <Header></Header>
        <Sider></Sider>
        <div class="main">{{main}}</div>
        <!-- <Main> -->
            <!-- 接收所有子路由的页面 -->
            
            <!-- <router-view></router-view> -->
            <!-- 接收 根路由 -->
        <!-- </Main> -->
    </div>
</template>

<script>
    import Header from '../common/Header';
    import Sider from '../common/Sider';

//     const mainNode=document.getElementsByClassName('main')[0]
//    main.innerHTML= main

    //  document.getElementsByClassName('main')[0].innerHTML=main

    export default {
        name:'Main',
        data(){
            return{
            
            }
        },
        components:{
            'Header':Header,
            'Sider' :Sider,
        },
        props:['main']

    }
</script>
<style >
.main{
    position: absolute;
    background: #eee;
    width: calc(100vw - 2rem);
    height: 90vh;
    top: 10vh;
    left: 2rem;
    overflow-x: hidden;
    /* padding: 0.16rem */
} 
</style>