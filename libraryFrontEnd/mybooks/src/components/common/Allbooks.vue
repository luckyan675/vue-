<template>
  <div class="booklist">
    <div class="filter">
      <input type="text" v-model="keywords" placeholder="书名、作者、出版社、简介、语言"/>
      <el-button @click="queryData">确定</el-button>
    </div>
    <!-- <el-button @click="clearFilter">清除所有过滤器</el-button> -->
    <el-table ref="filterTable" :data="tableData" style="width: 100%">
      <el-table-column prop="classification.className" sortable label="图书分类" width="180"></el-table-column>
      <el-table-column type="index"  label="书本编号" width="180"></el-table-column>
      <el-table-column prop="name" label="书名" width="180"></el-table-column>
      <el-table-column prop="author" label="作者" width="180"></el-table-column>
      <el-table-column prop="publish" label="出版社" ></el-table-column>
      <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">详情</el-button>
          <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">借阅</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {getbooklist,queryBookByKeyWd} from "../../interfaces/interfaces"
export default {
  name: "Allbooks",
  data() {
    return {
      tableData: [],
      keywords:""
    };
    
  },
  methods: {
    queryData() {
      queryBookByKeyWd(this.keywords).then(res=>{
        this.tableData = res.data.data;
      })

    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }
  },
  mounted() {
     getbooklist().then(res=>{
       const booklist = res.data;
       this.tableData = booklist;
     })
  },
};
</script>

<style>
.filter{
  display: flex;
  margin: 0.2rem 0;
}
.filter input{
  outline: none;
  border:1px solid #DCDFE6;
  width:2.5rem;
  padding-left: 0.1rem;
  margin-right: 0.2rem;
}
.filter input , button{
  box-sizing: content-box;
  height: 0.4rem;
}
.booklist{
  padding:0 0.5rem;
}
</style>