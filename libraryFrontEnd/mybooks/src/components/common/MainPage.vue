<template>
    <div id="app">
        <Layout></Layout>
    </div>
</template>

<script>
import Layout from "../common/Layout.vue"

export default {
    name:'homePage',
    data(){
        return{}
    },
    components:{
        Layout
    }

}

</script>
<style >
    #app{
        position: relative;
    }
</style>