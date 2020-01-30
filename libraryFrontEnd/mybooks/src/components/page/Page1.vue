 <template>
    <div>
        <Layout></Layout>
        <div class="main">
            <div class="table-style">
                <div class="search">
                    <div>书名：<input type="text" name="booksname"></div>
                    
                    <div>作者：<input type="text" name="author"></div>

                    <div>出版社：<input type="text" name="house"></div>
                    <div class="btn" ><button class="iconfont icon-sousuo">查询</button></div>
                </div>
                <MyTable :theads="theads" :tbodys="tbodys"></MyTable>
            </div>
        </div>
    </div>
</template>

<script>
import Layout from "../common/Layout.vue"
import MyTable from "../common/MyTable.vue"
// 书籍分类 对应的页面 组件名为Page1
export default {
    name: 'Page1',
    data(){
        return{
            list:[
                {name: '经典文学类'},
                {name: '科幻类'},
                {name: '现代小说'},
                {name: '少儿读物'},
                {name: '杂志报刊'},
                {name: '其他'},
            ],
            theads:[
                { name:"分类" },
                { name:"书名" },
                { name:"作者" },
                {name:"出版社"},
                {name:"现有库存"},
                { name:"借阅记录" }
            ],
            tbodys:[
                {
                    classify: '经典文学', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                {
                    classify: '现代小说', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                 {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                }, 
                {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                 {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                 {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                 {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                 {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                 {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },
                 {
                    classify: '科学文化', 
                    bookname: '鲁迅全集', 
                    auth: '鲁迅',
                    out: '作家出版社',
                    num: '100',
                    add: '添加到购物车'
                },


               
                // ['', '鲁迅全集', '鲁迅', '作家出版社', '100'],
                // ['', '鲁迅全集', '鲁迅', '作家出版社', '100'],
                // ['新闻周刊', '鲁迅全集', '鲁迅', '作家出版社', '100'],
                // ['经典文学', '鲁迅全集', '鲁迅', '作家出版社', '100'],
            ]
        }
    },
    components:{Layout,MyTable}
}
</script>
<style>
    .table-style{
        display: flex;
        flex-direction: column;
        align-items: center
    }
    .main .search{
        width: 90%;
        display: flex;
        justify-content: space-around;
        font-size: 0.16rem;
        color: #000;
    }
    .main .search div{
        margin-top: 0.3rem;
        margin-bottom: 0.3rem
    }
    .search div input{
        height: 0.3rem;
    }
    .btn button{
        background: rgb(236, 151, 90);
        color:#000;
        height: 0.35rem;
    }
    .classify{
        font-size: 0.3rem;
        
    }
</style>