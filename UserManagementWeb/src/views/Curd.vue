<template>
  <div>

    <el-input v-model="query.name" style="width: 200px" placeholder="请输入姓名"></el-input>
    <el-input v-model="query.city" style="width: 200px"  placeholder="请输入城市"></el-input>
    <el-input v-model="query.phone" style="width: 200px"  placeholder="请输入电话"></el-input>
    <el-button @click="getList()" type="primary">查询</el-button>
    <el-button @click="handleCreate()" type="primary">新增</el-button>

    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="name"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="age"
          label="年龄">
      </el-table-column>
      <el-table-column
          prop="sex"
          label="性别">
      </el-table-column>
      <el-table-column
          prop="phone"
          label="电话">
      </el-table-column>
      <el-table-column
          prop="city"
          label="城市">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.row)">编辑</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.query.page"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="this.query.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total">
    </el-pagination>


    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="姓名" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄" >
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" >
          <el-input v-model="form.sex" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" >
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="城市" >
          <el-input v-model="form.city" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "curd",
  data() {
    return {
      tableData: [],
      total:0,
      query:{
        page:1,
        size:6,
        name:'',
        phone:'',
        city:''
      },
      dialogFormVisible: false,
      form: {
        id:'',
        name:'',
        age:'',
        phone:'',
        sex:'',
        city:''
      },
    }
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList(){
      this.axios.get('http://localhost:3312/user/getList',{
        params:{
          page:this.query.page,
          size:this.query.size,
          name:this.query.name,
          phone:this.query.phone,
          city:this.query.city
        }
      }).then((resp)=>{
        console.log(resp);
        this.tableData = resp.data.content.list;
        this.total = resp.data.content.total;
      })

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.query.size = val;
      this.getList();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.query.page = val;
      this.getList();
    },
    handleDelete(scope){
      let id = scope.row.id;
      this.axios.delete("http://localhost:3312/user/delete/"+id).then((resp)=>{
        let data = resp.data;
        if(data.success){
          this.getList();
          this.$message({
            message: '恭喜你，删除成功',
            type: 'success'
          });
        }
      })
    },
    handleCreate(){
      this.form = {};
      this.dialogFormVisible = true
    },
    handleEdit(row){
      this.form = row;
      this.dialogFormVisible = true
    },
    submitForm(){
      this.axios.post('http://localhost:3312/user/save/',this.form).then((resp)=>{
        let data = resp.data;
        if(data.success){
          this.dialogFormVisible = false;
          this.form = {};
          this.getList();
          this.$message({
            message: '恭喜你，操作成功',
            type: 'success'
          });
        }
      })
    }
  },
}
</script>

<style scoped>

</style>