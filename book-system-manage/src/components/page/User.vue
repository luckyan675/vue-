<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 用户管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input v-model="keyword" placeholder="昵称或者用户名" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="search">搜索</el-button>
        <el-button type="primary" icon="add" @click="handleAdd">添加用户</el-button>
      </div>
      <el-table :data="tableData" border class="table" ref="multipleTable">
        <el-table-column prop="id" label="索引id" sortable></el-table-column>
        <el-table-column prop="user_name" label="用户名"></el-table-column>
        <el-table-column prop="nick_name" label="昵称"></el-table-column>
        <el-table-column prop="authority" label="身份" :formatter="userFormatter"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
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

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.user_name"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nick_name"></el-input>
        </el-form-item>
        <el-form-item label="身份">
          <el-select v-model="form.authority" placeholder="筛选类别" class="handle-select mr10">
            <el-option key="user" label="用户" :value="0"></el-option>
            <el-option key="admin" label="管理员" :value="1"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 添加弹出框 -->
    <el-dialog title="添加" :visible.sync="addVisible" width="30%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.user_name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nick_name"></el-input>
        </el-form-item>
        <el-form-item label="身份">
          <el-select v-model="form.authority" placeholder="筛选类别" class="handle-select mr10">
            <el-option key="user" label="用户" :value="0"></el-option>
            <el-option key="admin" label="管理员" :value="1"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAdd">确 定</el-button>
      </span>
    </el-dialog>

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
import {
  getUsers,
  updateCatalog,
  createCatalog,
  deleteCatalog,
  register,
  updateUser,
  deleteUser
} from "@/lib/api.js";

export default {
  name: "basetable",
  data() {
    return {
      url: "./static/vuetable.json",
      tableData: [],
      curPage: 1,
      count: 0,
      keyword: "",
      editVisible: false,
      delVisible: false,
      addVisible: false,
      form: {
        name: ""
      },
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
    //根据关键字搜索
    search() {
      this.curPage = 1;
      this.getData();
    },
    userFormatter(row, column) {
      return row.authority === 0 ? "用户" : "管理员";
    },
    handleEdit(index, row) {
      this.idx = index;
      const item = this.tableData[index];
      this.form = Object.assign({}, item);
      this.editVisible = true;
    },
    handleAdd() {
      this.form = { authority: 0 };
      this.addVisible = true;
    },
    handleDelete(index, row) {
      this.idx = index;
      this.delVisible = true;
    },
    // 保存编辑
    async saveEdit() {
      const res = await updateUser(this.form);
      if (res.data.code === 0) {
        this.$message.success(`修改第 ${this.idx + 1} 行成功`);
        this.$set(this.tableData, this.idx, res.data.data);
      }
      this.editVisible = false;
    },
    //获取全部一级分类
    async getData() {
      const res = await getUsers({
        keyword: this.keyword,
        page: this.curPage
      });
      if (res.data.code === 0) {
        this.count = res.data.count;
        this.tableData = res.data.data;
      }
    },
    //保存添加
    async saveAdd() {
      const res = await register(this.form);
      if (res.data.code === 0) {
        this.tableData.push(res.data.data);
        this.$message.success(`添加用户成功`);
      }
      this.form = { authority: 0 };
      this.addVisible = false;
    },
    // 确定删除
    async deleteRow() {
      let id = this.tableData[this.idx].id;
      const res = await deleteUser(id);
      if (res.data.code === 0) {
        this.tableData.splice(this.idx, 1);
        this.$message.success("删除成功");
      }
      this.delVisible = false;
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
