<template>
  <a-modal
    title="新增学院"
    :width="640"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleSubmit"
    @cancel="handleCancel"
  >
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          label="学院名"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
        >
          <a-input v-decorator="['name', {rules: [{required: true, min: 2, message: '请输入至少两个字符的用户名称！'}]}]"/>
        </a-form-item>
        <a-form-item
          label="班级列表,以逗号间隔"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
        >
          <a-input v-decorator="['classIds', {rules: [{required: true, message: '所有班级以学校间隔！'}]}]"/>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import { createAcademy, getRoleList } from '@/api/manage'

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
    add () {
      this.visible = true
    },
    handleSubmit () {
      const { form: { validateFields } } = this
      this.confirmLoading = true
      validateFields((errors, values) => {
        if (!errors) {
          createAcademy({
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
      this.form.resetFields()
    },
    handleRoleChange (value) {
      console.log(`selected ${value}`)
    }
  }
}
</script>
