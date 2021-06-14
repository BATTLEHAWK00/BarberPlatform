<template>
  <!-- <a-alert :message="'欢迎来到' + title + '!'" type="success" /> -->

  <a-card title="理发店概况" :loading="loading" hoverable>
    <a-row>
      <a-col :span="8">
        <a-card>
          <a-statistic
            title="消费会员数与昨天相比"
            :value="statistics.yesterday.users"
            suffix="人"
            :value-style="
              statistics.yesterday.users > 0
                ? { color: '#3f8600' }
                : { color: '#cf1322' }
            "
          >
            <template #prefix>
              <arrow-up-outlined v-if="statistics.yesterday.users > 0" />
              <arrow-down-outlined v-if="statistics.yesterday.users < 0" />
            </template>
          </a-statistic>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card>
          <a-statistic
            title="订单数与昨天相比"
            :value="statistics.yesterday.orders"
            suffix="笔"
            :value-style="
              statistics.yesterday.orders > 0
                ? { color: '#3f8600' }
                : { color: '#cf1322' }
            "
          >
            <template #prefix>
              <arrow-up-outlined v-if="statistics.yesterday.orders > 0" />
              <arrow-down-outlined v-if="statistics.yesterday.orders < 0" />
            </template>
          </a-statistic>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card>
          <a-statistic
            title="预约人数与昨天相比"
            :value="statistics.yesterday.reservations"
            suffix="人/次"
            :value-style="
              statistics.yesterday.reservations > 0
                ? { color: '#3f8600' }
                : { color: '#cf1322' }
            "
          >
            <template #prefix>
              <arrow-up-outlined v-if="statistics.yesterday.reservations > 0" />
              <arrow-down-outlined
                v-if="statistics.yesterday.reservations < 0"
              />
            </template>
          </a-statistic>
        </a-card>
      </a-col>
    </a-row>
    <a-row type="flex">
      <a-col :span="6" :order="4">
        <a-card>
          <a-statistic title="会员数" :value="9" suffix="人"></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="6" :order="3">
        <a-card>
          <a-statistic title="管理员数" :value="9" suffix="人"></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="6" :order="2">
        <a-card>
          <a-statistic
            title="今日消费用户"
            :value="9"
            suffix="人"
          ></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="6" :order="1">
        <a-card>
          <a-statistic
            title="今日储值用户"
            :value="9"
            suffix="人"
          ></a-statistic>
        </a-card>
      </a-col>
    </a-row>
  </a-card>
</template>
<script>
import {ArrowDownOutlined, ArrowUpOutlined} from '@ant-design/icons-vue'
import {mapGetters} from 'vuex'

export default {
    components: {
      ArrowUpOutlined,
      ArrowDownOutlined,
    },
    computed: {
      ...mapGetters({
        title: 'settings/title',
      }),
    },
    mounted() {
      setTimeout(() => {
        this.loading = false
      }, 500)
    },
    data() {
      return {
        loading: true,
        statistics: {
          yesterday: {
            users: 8,
            orders: -9,
            reservations: 7,
          },
        },
      }
    },
  }
</script>
<style scoped></style>
