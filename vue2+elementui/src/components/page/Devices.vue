<template>
    <div>
      <!--分页-->
      <!-- <el-pagination
          :page-sizes="[10, 20, 30, 40]"
          :page-size="10"
          :total="100"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination> -->
      <!-- 查询 -->
      <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
        <el-form-item>
          <el-input v-model="dataForm.roleName" placeholder="设备号" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="getDataList()">查询</el-button>
          <el-button v-if="isvalue()" :key="10" type="primary" @click="addOrUpdateHandle()">新增</el-button>
          <el-button v-if="isvalue()" :key="20" type="danger" @click="deleteHandle()"
            :disabled="dataListSelections <= 0">批量删除</el-button>
        </el-form-item>
      </el-form>
      <!--表格内容-->
      <el-table ref="list" :data="tableData" style="width: 100%" border stripe highlight-current-row
        :default-sort="{ prop: 'date', order: 'descending' }" @row-click="handleRowClick"
        @select-all="handleCheckedAllAndCheckedNone" @select="handleCheckedAllAndCheckedNone"
        @selection-change="changedForm">
        <el-table-column type="selection" width="45" align="center">
        </el-table-column>
        <el-table-column type="index" label="序号" width="50">
        </el-table-column>
        <el-table-column property="deviceId" label="设备号">
        </el-table-column>
        <el-table-column property="faultId" label="设备故障号">
        </el-table-column>
        <el-table-column property="repairId" label="设备维修号">
        </el-table-column>
        <el-table-column property="maintenanceId" label="设备保养号">
        </el-table-column>
        <el-table-column property="deviceName" label="设备名称">
        </el-table-column>
        <el-table-column property="deviceType" label="设备类型">
        </el-table-column>
        <el-table-column property="deviceStatus" label="设备状态">
        </el-table-column>
        <el-table-column property="effciency" label="生产效率">
        </el-table-column>
        <el-table-column property="manufacturer" label="生产厂家">
        </el-table-column>
        <el-table-column property="model" label="型号">
        </el-table-column>
        <el-table-column property="Specification" label="规格">
        </el-table-column>
        <el-table-column property="Life" label="使用寿命">
        </el-table-column>
        <el-table-column property="techId" label="技术资料号">
        </el-table-column>
        <el-table-column label="操作" width="130" align="center">
          <template slot-scope="scope">
            <el-button v-if="isvalue()" circle icon="el-icon-edit-outline" type="primary" title="编辑" size="small"
              @click.stop="rowEdit(scope.$index, scope.row)"></el-button>
            <el-button v-if="isvalue()" circle icon="el-icon-delete" type="danger" title="删除" size="small"
              @click.stop="rowDel(scope.$index, scope.row, $event);"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--编辑-弹出层-->
      <el-dialog title="编辑" :visible.sync="isShowEditDialog" width="445px" @close="dialogClose">
        <el-form ref="editForm" :model="formFileds" label-width="70px" :rules="rules">
          <el-form-item label="设备号">
            <el-input v-model="formFileds.deviceId"></el-input>
          </el-form-item>
          <el-form-item label="设备故障号">
            <el-input v-model="formFileds.faultId"></el-input>
          </el-form-item>
          <el-form-item label="设备维修号">
            <el-input v-model="formFileds.repairId"></el-input>
          </el-form-item>
          <el-form-item label="设备保养号">
            <el-input v-model="formFileds.maintenanceId"></el-input>
          </el-form-item>
          <el-form-item label="设备名称">
            <el-input v-model="formFileds.deviceName"></el-input>
          </el-form-item>
          <el-form-item label="设备类型">
            <el-input v-model="formFileds.deviceType"></el-input>
          </el-form-item>
          <el-form-item label="设备状态">
            <el-input v-model="formFileds.deviceStatus"></el-input>
          </el-form-item>
          <el-form-item label="生产效率">
            <el-input v-model="formFileds.effciency"></el-input>
          </el-form-item>
          <el-form-item label="生产厂家">
            <el-input v-model="formFileds.manufacturer"></el-input>
          </el-form-item>
          <el-form-item label="型号">
            <el-input v-model="formFileds.model"></el-input>
          </el-form-item>
          <el-form-item label="规格">
            <el-input v-model="formFileds.Specification"></el-input>
          </el-form-item>
          <el-form-item label="使用寿命">
            <el-input v-model="formFileds.Life"></el-input>
          </el-form-item>
          <el-form-item label="技术资料号">
            <el-input v-model="formFileds.techId"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleEdit(isCreateNew)" class="pull-right margin-l-25">确定</el-button>
            <el-button @click="isShowEditDialog = false;" class="pull-right">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </template>
    
  <script>
  export default {
    name: "devices",
    data() {
      return {
        dataForm: {
          roleName: ''
        },
        dataListSelections: [],
        formFileds: {
            deviceId: '',
            faultId: '',
            repairId: '',
            maintenanceId: '',
            deviceName: '',
            deviceType: '',
            deviceName: '',
            deviceStatus: '',
            effciency: '',
            manufacturer: '',
            model: '',
            Specification: '',
            Life: '',
            techId: '',
        },
        rules: {
          name: [
            { required: true, message: '姓名不能为空', trigger: 'blur, change' }
          ],
          address: [
            { required: true, message: '地址不能为空', trigger: 'blur, change' }
          ]
        },
        tableData: [],
        PositionId: [],
        isShowEditDialog: false,
        isCreateNew: false,
        WillChangeIndex: 0,
      }
    },
    methods: {
      changedForm(val) {
        console.log(val)
        this.dataListSelections = val
      },
      deleteHandle() {
        console.log(this.dataListSelections)
        for (var i = 0; i < this.dataListSelections.length; i++) {
          this.PositionId.push(this.dataListSelections[i].deviceId)
        }
        console.log(this.PositionId)
        console.log(this.dataListSelections)
        this.$confirm('确定对[' + this.PositionId + ']号资料进行批量删除操作?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          // type: 'warning'
        }).then(() => {
          this.$axios({
            method: 'post',
            url: '/DeleteLogs',
            data: this.PositionId
          }).then(res => {
            alert("批量删除成功")
            this.tableData = res.data.message;
          }, err => {
            console.log(err);
          })
        }).catch(() => { })
      },
      addOrUpdateHandle() {
        this.isCreateNew = true;
        this.isShowEditDialog = true;
        //dataForm.push(formFileds);
      },
      getDataList() {
        if (this.dataForm.roleName == '') {
          this.$axios({
            method: 'get',
            url: '/DevicesAll'
          }).then(res => {
            // tableData = res.data.message;
            this.tableData = [];
            this.tableData = res.data.message;
            //console.log(this.tableData);
          }, err => {
            console.log(err);
          });
        }
        else {
          this.$axios({
            method: 'post',
            url: '/FindDevicesById/' + this.dataForm.roleName
          }).then(res => {
            if (res.data.message == null) {
              alert("未找到该资料")
            } else {
              this.tableData = [];
              this.tableData[0] = res.data.message;
              this.tableData[0].deviceId = this.dataForm.roleName
            }
  
          }, err => {
            this.tableData = [];
            alert("未找到该资料")
            console.log(err);
          });
        }
      },
      isvalue() {
        if (this.$store.state.PositionForm.devices == '更改') {
          return true;
        } else {
          return false;
        }
      },
      handleRowClick(row, event, column) {
  
        // 仅选中当前行
        this.setCurRowChecked(row);
      },
      handleCheckedAllAndCheckedNone(selection) {
  
        // 当前选中仅一行时操作-（当前表格行高亮）
        1 != selection.length && this.$refs.list.setCurrentRow();
      },
      dialogClose() {
  
        // 清空编辑表单
        this.$refs.editForm.resetFields();
      },
      rowEdit(index, row) {
  
        //   this.setCurRowChecked(row);
  
        // 给编辑弹出层赋值
        // ***这里需要注意的是：因为加了排序 所以tableData的顺序和实际显示的行顺序不一样
        //   for (let key in this.formFileds) {
  
        //     this.formFileds[key] = row[key];
        //   }
        this.WillChangeIndex = index;
        this.formFileds = this.tableData[index];
        this.isCreateNew = false;
        this.isShowEditDialog = true;
      },
      handleEdit(id) {
        console.log(id)
        if (id == true) {
          let newFormposition = this.formFileds.deviceId;
          let newForm = {};
          Object.assign(newForm, this.formFileds)
          this.$axios({
            method: 'post',
            url: '/InsertDevices',
            data: {
                newForm,
            }
          }).then(res => {
            this.tableData.push(this.formFileds);
          }, err => {
            console.log(err);
          });
        }
        else {
          this.$refs.editForm.validate(isValid => {
            if (!isValid) return;
            this.isShowEditDialog = false;
            let newFormposition = this.formFileds.position;
            let newForm = {};
            Object.assign(newForm, this.formFileds)
            this.$delete(newForm, 'position');
            console.log(newForm)
            this.$axios({
              method: 'post',
              url: '/UpdateDevices',
              data: {
                position: newFormposition,
                jsRights: newForm
              }
            }).then(res => {
              // 保存编辑后的数据
              Object.assign(this.tableData[this.WillChangeIndex], this.formFileds);
            }, err => {
              console.log(err);
            });
          });
        }
        this.$message.success('编辑成功');
      },
      rowDel(index, row, event) {
  
        // 让当前删除按钮失焦
        event.target.blur();
  
        this.$confirm('确定要删除当前行吗？', '删除', {
          comfirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(() => {
          this.PositionForm = [];
          this.PositionForm.push(this.tableData[index].deviceId);
          this.$axios({
            method: 'post',
            url: '/DeleteDevices',
            data: this.PositionForm
          }).then(res => {
            this.tableData = res.data.message;
          }, err => {
            console.log(err);
          })
          this.tableData.splice(row.id, 1);
          this.$message.success('删除成功');
          return false;
        });
      },
      // 选中当前行-当前行的复选框被勾选
      setCurRowChecked(row) {
  
        this.$refs.list.clearSelection();
        this.$refs.list.toggleRowSelection(row);
      }
    },
    mounted() {
      this.$axios({
        method: 'get',
        url: '/DevicesAll'
      }).then(res => {
        // tableData = res.data.message;
        this.tableData = res.data.message;
        //console.log(this.tableData);
      }, err => {
        console.log(err);
      });
    }
  }
  
  </script>
    
  <style scoped lang="less">
  .el-form {
    padding: 0 10px;
  }
  
  .el-date-editor {
    width: 100% !important;
  }
  </style>