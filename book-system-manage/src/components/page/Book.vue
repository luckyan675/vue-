<template>
  <div class="table">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-text"></i> 全部书籍
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-select
          v-model="select_catalog"
          placeholder="筛选类别"
          class="handle-select mr10"
          @change="handleSearchChange"
        >
          <el-option key="all" label="全部" value></el-option>
          <el-option
            v-for="(item,index) in catalogsData"
            :key="index"
            :label="item.name"
            :value="item.name"
          ></el-option>
        </el-select>
        <el-input v-model="keyword" placeholder="筛选关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="search">搜索</el-button>
        <el-button type="primary" icon="add" @click="handleAdd">添加书籍</el-button>
      </div>
      <el-table :data="tableData" border class="table" ref="multipleTable">
        <el-table-column prop="id" label="索引id" sortable></el-table-column>
        <el-table-column prop="number" label="图书封面">
          <template slot-scope="scope">
            <img :src="scope.row.avatar_url" :alt="scope.$index" width="100">
          </template>
        </el-table-column>
        <el-table-column prop="number" label="图书编号"></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="content" label="内容" :formatter="contentFormatter"></el-table-column>
        <el-table-column prop="price" label="价格"></el-table-column>
        <el-table-column prop="count" label="库存"></el-table-column>
        <el-table-column prop="catalog" label="类别" :formatter="catalogFormatter"></el-table-column>
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
        <el-form-item label="图书封面" required>
          <el-input v-model="form.avatar_url" placeholder="请输入url"></el-input>
        </el-form-item>
        <el-form-item label="图书编号" required>
          <el-input v-model="form.number"></el-input>
        </el-form-item>
        <el-form-item label="名称" required>
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="内容" required>
          <el-input v-model="form.content" :autosize="true" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="价格" required>
          <el-input-number v-model="form.price" :precision="2" :step="0.1"></el-input-number>
        </el-form-item>
        <el-form-item label="类别" required>
          <el-select v-model="form.catalog_id" placeholder="筛选类别" class="handle-select mr10">
            <el-option
              v-for="(item,index) in catalogsData"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
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
        <el-form-item label="图书封面" required>
          <el-input v-model="form.avatar_url" placeholder="请输入url"></el-input>
        </el-form-item>
        <el-form-item label="图书编号" required>
          <el-input v-model="form.number"></el-input>
        </el-form-item>
        <el-form-item label="名称" required>
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="内容" required>
          <el-input v-model="form.content" :autosize="true" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="价格" required>
          <el-input-number v-model="form.price" :precision="2" :step="0.1"></el-input-number>
        </el-form-item>
        <el-form-item label="类别" required>
          <el-select v-model="form.catalog_id" placeholder="筛选类别" class="handle-select mr10">
            <el-option
              v-for="(item,index) in catalogsData"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
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

    <!-- 图片裁剪提示框 -->
    <!-- <el-dialog title="裁剪图片" :visible.sync="dialogVisible" width="30%">
      <vue-cropper
        ref="cropper"
        :src="imgSrc"
        :ready="cropImage"
        :zoom="cropImage"
        :cropmove="cropImage"
        style="width:100%;height:300px;"
      ></vue-cropper>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelCrop">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>-->
  </div>
</template>

<script>
import {
  getCatalogs,
  getBooks,
  updateBook,
  createBook,
  deleteBook
} from "@/lib/api.js";
import VueCropper from "vue-cropperjs";

export default {
  name: "basetable",
  data() {
    return {
      tableData: [],
      catalogsData: [],
      curPage: 1,
      count: 0,
      select_catalog: "",
      keyword: "",
      editVisible: false,
      delVisible: false,
      addVisible: false,
      defaultSrc: "./static/img/img.jpg",
      fileList: [],
      imgSrc: "",
      cropImg: "",
      dialogVisible: false,
      form: {
        name: "",
        date: "",
        address: ""
      },
      idx: -1
    };
  },
  created() {
    this.getAllCatalogs();
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
    //根据类别搜索书籍
    handleSearchChange() {
      this.curPage = 1;
      this.getData();
    },
    //格式化类别
    catalogFormatter(row, column) {
      return row.catalog.name;
    },
    //格式化内容
    contentFormatter(row, column) {
      return row.content.slice(0, 30) + "...";
    },
    //打开编辑框
    handleEdit(index, row) {
      this.idx = index;
      const item = this.tableData[index];
      //深拷贝，防止取消的时候也会修改原来数据
      this.form = Object.assign({}, item);
      this.editVisible = true;
    },
    //打开删除框
    handleDelete(index, row) {
      this.idx = index;
      this.delVisible = true;
    },
    //打开添加框
    handleAdd() {
      this.form = {};
      this.addVisible = true;
    },
    // 保存编辑
    async saveEdit() {
      const res = await updateBook(this.form);
      if (res.data.code === 0) {
        const findItem = this.findCatalogItem(this.form.catalog_id);
        this.$message.success(`修改第 ${this.idx + 1} 行成功`);
        res.data.data.catalog = findItem;
        this.$set(this.tableData, this.idx, res.data.data);
      }
      this.editVisible = false;
    },
    //保存添加
    async saveAdd() {
      const res = await createBook(this.form);
      if (res.data.code === 0) {
        const findItem = this.findCatalogItem(this.form.catalog_id);
        this.tableData.push({ ...res.data.data, catalog: findItem });
        this.$message.success(`添加分类成功`);
      }
      this.form = {};
      this.addVisible = false;
    },
    //根据catalog_id找到对应的catalog item
    findCatalogItem(id) {
      const findItem = this.catalogsData.find(item => item.id === id);
      return findItem;
    },
    // 确定删除
    async deleteRow() {
      let id = this.tableData[this.idx].id;
      const res = await deleteBook(id);
      if (res.data.code === 0) {
        this.tableData.splice(this.idx, 1);
        this.$message.success("删除成功");
      }
      this.delVisible = false;
    },
    //获取书籍数据
    async getData() {
      const res = await getBooks({
        keyword: this.keyword,
        page: this.curPage,
        select_catalog: this.select_catalog,
        keyword: this.keyword
      });
      if (res.data.code === 0) {
        this.count = res.data.count;
        this.tableData = res.data.data;
      }
    },
    //获取所有一级分类
    async getAllCatalogs() {
      const res = await getCatalogs({
        pageSize: 1000,
        page: 1
      });
      if (res.data.code === 0) {
        this.catalogsData = res.data.data;
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
