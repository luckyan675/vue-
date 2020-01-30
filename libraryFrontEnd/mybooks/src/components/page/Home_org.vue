<template>
    <!-- <div class="main"> -->
    <div>
        <Layout></Layout>
        <div class="main">
            <ul class="books">
                <li class="book-name" v-for="item in pics" :key="item.name">
                    <img class="left" :src="item._src" alt="">
                    <span class="right" style="display:flex; flex-direction: column;">
                        <span class="title">{{item.name}}</span>
                        <span class="des">{{item.abstract}}</span>
                    </span>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
// 热门推荐 对应的页面
import Layout from "../common/Layout.vue"

export default {
    name:'Home',
    data(){
        return{
            pics:[
                {name:'星河',_src:require("../../assets/user.jpg"),abstract:"是文明消亡，传承断绝，还是奋起一搏，去寻求那千万分之一的飘渺希望？"},
                {name:'小王子',_src:require("../../assets/user.jpg"),abstract:"《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子"},
                {name:'毛概',_src:require("../../assets/user.jpg"),abstract:"《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子"},
                {name:'高数',_src:require("../../assets/user.jpg"),abstract:"《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子"},
                {name:'高数1',_src:require("../../assets/user.jpg"),abstract:"《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子5"},
                {name:'高数2',_src:require("../../assets/user.jpg"),abstract:"《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子"},
                {name:'高数3',_src:require("../../assets/user.jpg"),abstract:"《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子"},
                {name:'高数4',_src:require("../../assets/user.jpg"),abstract:"《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子"}
            ]
        }
    },
    components:{Layout},
    // render(h) {
    //     return h(()=>{
    //             console.log(1111)
    //         })
    // },
}
</script>

<style >
    .main .books{
        list-style: none;
        display: flex;
        /* width: 60%; */
        flex-wrap: wrap;

    }
    .main .book-name{
        font-size: 0.14rem;
        /* line-height: 1rem; */
        display: flex;
        width: 50%;
        text-align: left;
        border-bottom: 2px solid #ccc;
        padding: 0.5rem 0 0.05rem 0;
    }
    .main .book-name .left{
        width: 20%;
        height: 1.6rem; 
    }
    .main .book-name .right{
        width: 80%;
        height: 1rem;
        padding-right: 32px;
    }
    .main .book-name img.right .title{
        border-bottom:1px solid #000; 
    } 
    .des{
        display: inline-block;
        /* */
    }
    /* .buttom-line{
        border: 1px solide #ccc;
    } */
</style>