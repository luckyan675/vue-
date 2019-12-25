<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-text"></i> 全部订单
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input v-model="keyword" placeholder="请输入订单号查询" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="search">搜索</el-button>
      </div>
      <el-table :data="tableData" border class="table" ref="multipleTable">
        <el-table-column prop="id" label="索引id" sortable></el-table-column>
        <el-table-column prop="number" label="订单编号"></el-table-column>
        <el-table-column prop="name" label="名称" :formatter="nameFormatter"></el-table-column>
        <el-table-column prop="price" label="单价" :formatter="priceFormatter"></el-table-column>
        <el-table-column prop="count" label="数量"></el-table-column>
        <el-table-column prop="user_name" label="用户名" :formatter="userNameFormatter"></el-table-column>
        <el-table-column prop="nick_name" label="昵称" :formatter="nickNameFormatter"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-delete"
              class="red"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          background
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="count"
        ></el-pagination>
      </div>
    </div>

    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delVisible = false">取 消</el-button>
        <el-button type="primary" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getOrders, deleteOrder } from "@/lib/api.js";
import VueCropper from "vue-cropperjs";

export default {
  name: "basetable",
  data() {
    return {
      tableData: [],
      curPage: 1,
      count: 0,
      keyword: "",
      delVisible: false,
      idx: -1
    };
  },
  created() {
    this.getData();
  },
  methods: {
    // 分页导航
    handleCurrentChange(val) {
      this.curPage = val;
      this.getData();
    },
    // 获取 easy-mock 的模拟数据
    //根据关键字搜索
    search() {
      this.curPage = 1;
      this.getData();
    },
    //格式化价格
    priceFormatter(row, column) {
      return row.book.price;
    },
    //格式化名称
    nameFormatter(row, column) {
      return row.book.name;
    },
    //格式化username
    userNameFormatter(row, column) {
      return row.user.user_name;
    },
    //格式化nickname
    
    nickNameFormatter(row, column) {
      return row.user.nick_name;
    },
    //打开删除框
    handleDelete(index, row) {
      this.idx = index;
      this.delVisible = true;
    },
    // 确定删除
    async deleteRow() {
      let id = this.tableData[this.idx].id;
      const res = await deleteOrder(id);
      if (res.data.code === 0) {
        this.tableData.splice(this.idx, 1);
        this.$message.success("删除成功");
      }
      this.delVisible = false;
    },
    //获取书籍数据
    async getData() {
      const res = await getOrders({
        keyword: this.keyword,
        page: this.curPage,
        select_catalog: this.select_catalog,
        keyword: this.keyword
      });
      if (res.data.code === 0) {
        console.log(res);
        this.count = res.data.count;
        this.tableData = res.data.data;
      }
    }
  }
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.del-dialog-cnt {
  font-size: 16px;
  text-align: center;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
</style>
