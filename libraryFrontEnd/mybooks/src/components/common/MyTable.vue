<template>
    <div>
        <table class="tb"  cellpadding="20px">
            <tr>
                <th v-for="item in theads" :key="item.name">{{item.name}}</th>
            </tr>
            <tr  v-for="item in tbodys" :key="item.name" class="choose">
                <td>{{ item.classify}}</td>
                <td>{{ item.bookname  }}</td>
                <td>{{ item.auth  }}</td>
                <td>{{ item.out  }}</td>
                <td>{{ item.num  }}</td>
                <td>{{item.add}}</td>
            </tr>
        </table>
    </div>
</template>
<script>
export default {
    name: 'MyTable',
    data(){
        return{
        //    myTHeads:this.theads
        }
    },
    props:['theads', 'tbodys']
}
</script>
<style>
    .tb {
        border: 1px solid #ccc;
        font-size: 0.2rem;
        border-collapse: collapse;
        /* background: #ddd; */
        border-left: none;
        border-right: none;
    }
    tr,th ,td{
        border: 1px solid #ccc;
        padding: 0.15rem 0.7rem;
        border-left: none;
        border-right: none;
    }
    .choose td:hover{
        background-color: rgb(68, 209, 209);
       transition:  background-color 0.3s linear
    }
    
    
</style>