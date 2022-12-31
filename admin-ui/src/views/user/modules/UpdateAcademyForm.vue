<template>
  <a-modal
    title="编辑学院"
    :width="640"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleSubmit"
    @cancel="handleCancel"
  >
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          label="学院编号"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
        >
          <a-input disabled="disabled" v-decorator="['id']"/>
        </a-form-item>
        <a-form-item
          label="学院名称"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
        >
          <a-input  v-decorator="['name', {rules: [{required: true, min: 2, message: '请输入至少两个字符的用户名称！'}]}]"/>
        </a-form-item>
        <a-form-item
          label="学院列表"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
        >
          <a-input v-decorator="['classIds', {rules: [{required: true, message: '请以逗号间隔每个班级'}]}]"/>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import { getRoleList, updateAcademy } from '@/api/manage'
import pick from 'lodash.pick'

export default {
  data () {
    return {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 13 }
      },
      visible: false,
      confirmLoading: false,

      form: this.$form.createForm(this),
      roles: []
    }
  },
  created () {
    getRoleList().then((res) => {
      this.roles = res.result
    })
  },
  methods: {
    edit (record) {
      this.visible = true
      this.$nextTick(() => {
        this.form.setFieldsValue(pick(record, 'id', 'name', 'classIds'))
        this.form.setFieldsValue({ 'classIds': record.classIds })
        this.form.setFieldsValue({ 'num1': 1000 })
        this.form.setFieldsValue({ 'num2': 100 })
      })
    },
    handleSubmit () {
      const { form: { validateFields } } = this
      this.confirmLoading = true
      validateFields((errors, values) => {
        if (!errors) {
          updateAcademy({
            id: values.id,
            name: values.name,
            classIds: values.classIds
          }).then((res) => {
            this.confirmLoading = false
            if (res.success) {
              this.visible = false
              this.form.resetFields()
              this.$emit('ok', values)
            } else {
              this.$message.warning(res.message)
            }
          })
        } else {
          this.confirmLoading = false
        }
      })
    },
    handleCancel () {
      this.visible = false
    },
    handleRoleChange (value) {
      console.log(`selected ${value}`)
    }
  }
}
</script>
