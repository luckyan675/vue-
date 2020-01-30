<template>
  <div>
    <Header></Header>
    <el-form class="bookform" ref="form" :model="form" label-width="0.8rem">
      <el-form-item label="图书名称">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="作者">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="出版社">
        <el-input></el-input>
      </el-form-item>
      <el-form-item label="书籍类型">
        <el-input></el-input>
      </el-form-item>
      
      <el-form-item label="书籍编号">
        <el-input></el-input>
      </el-form-item>
      
      <el-form-item label="库存数量">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="form.desc" class="text-input"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加图书</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Header from "../common/Header";
export default {
  name: "Addbook",
  data() {
    return {
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: ""
      }
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    }
  },
  components: { Header: Header }
};
</script>
<style>
.bookform{
  padding: 0.2rem;
}
.bookform input{
  width: 35vw;

}
.bookform .el-form-item{
  display: flex;
}
</style>