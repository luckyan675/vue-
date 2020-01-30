<template>
  <div>
    <input type="text">
    <el-button @click="resetDateFilter">清除过滤器</el-button>
    <!-- <el-button @click="clearFilter">清除所有过滤器</el-button> -->
    <el-table ref="filterTable" :data="tableData" style="width: 100%">
      <el-table-column
        prop="date"
        label="图书分类"
        sortable
        width="180"
        column-key="date"
        :filters="[
                    {text: '社会科学', value: '社会科学'}, 
                    {text: '哲学', value: '哲学'}, 
                    {text: '自然科学', value: '自然科学'},
                    {text: '马列主义、毛泽东思想', value: '马列主义、毛泽东思想'}, 
                    {text: '综合性图书', value: '综合性图书'}
                ]"
        :filter-method="filterHandler"
      ></el-table-column>
      <el-table-column prop="number" label="书本编号" width="180"></el-table-column>
      <el-table-column prop="name" label="书名" width="180"></el-table-column>
      <el-table-column prop="author" label="作者" width="180"></el-table-column>
      <el-table-column prop="address" label="出版社" :formatter="formatter"></el-table-column>
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
export default {
  name: "Allbooks",
  data() {
    return {
      tableData: [
        {
          date: "社会科学",
          number: '000001',
          name: "朝花夕拾",
          author: "鲁迅",
          address: "上海市普陀区金沙江路 1518 弄",
          tag: "家"
        },
        {
          date: "社会科学",
           number: '000002',
          name: "呐喊",
          author: "鲁迅",
          address: "上海市普陀区金沙江路 1517 弄",
          tag: "公司"
        },
        {
          date: "社会科学",
           number: '000003',
          name: "彷徨",
          author: "鲁迅",
          address: "上海市普陀区金沙江路 1519 弄",
          tag: "家"
        },
        {
          date: "综合性图书",
           number: '000001',
          name: "王小虎",
          author: "111",
          address: "上海市普陀区金沙江路 1516 弄",
          tag: "公司"
        },
         {
          date: "马列主义、毛泽东思想",
           number: '000001',
          name: "王小虎",
          author: "毛泽东",
          address: "上海市普陀区金沙江路 1516 弄",
          tag: "公司"
        },
         {
          date: "社会科学",
           number: '000001',
          name: "经济学原理（上下）",
          author: "[美] 曼昆",
          address: "机械工业出版社",
          tag: "家"
        },
         {
          date: "自然科学",
          name: "世界地理",   
          author: "未知",
          address: "上海市普陀区金沙江路 1519 弄",
          tag: "家"
        }
      ]
    };
  },
  methods: {
    resetDateFilter() {
      this.$refs.filterTable.clearFilter("date");
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
  }
};
</script>