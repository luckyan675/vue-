 <template>
    <div>
       <layout></layout>
        <!-- <Layout :main="com1"></Layout> -->
        <div class="main">
            <div class="search">
                <div>借出书名：<input type="text"></div>
            </div>
            
        </div>
    </div>
</template>

<script>
// 借出管理对应页面
import Layout from "@/components/common/Layout"
// import Layout from "../common/Layout.vue"
// import vue from 'vue'
// vue.component('com1',{
//      template: `<div class="search">
//                 <div>借出书名：<input type="text"></div>
//              </div>`
// })
 export default{
    name: 'Page2',
    data(){
        return{
            com1: `<div class="search">
                    <div>借出书名：<input type="text"></div>
                    </div>`
        }
    },
    components:{Layout},
    
}
// export default Page2

</script>