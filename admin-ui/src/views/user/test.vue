<template>
  <s-table
    ref="table"
    size="default"
    :columns="columns"
    :data="loadData"
    :alert="options.alert"
    :rowSelection="options.rowSelection"
    showPagination="auto"
  >
      <span slot="locked" slot-scope="text">
        <a-badge :status="text | lockedTypeFilter" :text="text | lockedFilter"/>
      </span>

    <span slot="classIds" slot-scope="classIds">
        <a-tag v-for="roleName in classIds" :key="roleName">{{ roleName }}</a-tag>
      </span>
    <span slot="action" slot-scope="text, record">
        <template>
          <a v-action:user:update @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              更多 <a-icon type="down"/>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a href="javascript:;" @click="handleLocked(record)">
                  {{ record.locked?'启用':'禁用' }}
                </a>
              </a-menu-item>
              <a-menu-item>
                <a href="javascript:;" v-action:user:delete @click="handleDelete(record)">删除</a>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </template>
      </span>
    <span slot="action2" slot-scope="text, record">
        <template>
          <a v-action:user:update @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              更多 <a-icon type="down"/>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a href="javascript:;" @click="handleLocked(record)">
                  {{ record.locked?'启用':'禁用' }}
                </a>
              </a-menu-item>
              <a-menu-item>
                <a href="javascript:;" v-action:user:delete @click="handleDelete(record)">删除</a>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </template>
      </span>
  </s-table>
</template>

<script>
import STable from '@/components'
import { getAcademyList } from '@/api/manage'

export default {
  components: {
    STable
  },
  data () {
    return {
      columns: [
        {
          title: '规则编号',
          dataIndex: 'id'
        },
        {
          title: '描述',
          dataIndex: 'description'
        },
        {
          title: '服务调用次数',
          dataIndex: 'numOfStu'
        },
        {
          title: '状态',
          dataIndex: 'status'
        },
        {
          title: '更新时间',
          dataIndex: 'updatedAt'
        },
        {
          table: '操作',
          dataIndex: 'action',
          scopedSlots: { customRender: 'action' }
        }
      ],
      // 查询条件参数
      queryParam: {},
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        console.log('loadData.parameter', parameter)
        return getAcademyList(Object.assign(parameter, this.queryParam))
          .then(res => {
            return res.result
          })
      }
    }
  },
  methods: {
    edit (row) {
      // axios 发送数据到后端 修改数据成功后
      // 调用 refresh() 重新加载列表数据
      // 这里 setTimeout 模拟发起请求的网络延迟..
      setTimeout(() => {
        this.$refs.table.refresh() // refresh() 不传参默认值 false 不刷新到分页第一页
      }, 1500)
    }
  }
}
</script>
