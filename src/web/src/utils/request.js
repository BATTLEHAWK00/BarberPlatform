import axios from 'axios'
import {
  baseURL,
  contentType,
  debounce,
  requestTimeout,
  successCode,
  tokenName,
} from '@/config'
import store from '@/store'
import qs from 'qs'
import router from '@/router'
import { isArray } from '@/utils/validate'
import { message } from 'ant-design-vue'

let loadingInstance

/**

 * @description 处理code异常
 * @param {*} code
 * @param {*} msg
 */
const handleCode = (code, msg) => {
  switch (code) {
    case 401:
      message.error(msg || '登录失效')
      store.dispatch('user/resetAll').catch(() => {})
      break
    case 403:
      router.push({ path: '/403' }).catch(() => {})
      break
    default:
      message.error(msg || `后端接口${code}异常`)
      break
  }
}

/**

 * @description axios初始化
 */
const instance = axios.create({
  baseURL,
  timeout: requestTimeout,
  headers: {
    'Content-Type': contentType,
  },
})

/**
 * @description axios请求拦截器
 */
instance.interceptors.request.use(
  (config) => {
    if (store.getters['user/accessToken'])
      config.headers[tokenName] = store.getters['user/accessToken']
    if (
      config.data &&
      config.headers['Content-Type'] ===
        'application/x-www-form-urlencoded;charset=UTF-8'
    )
      config.data = qs.stringify(config.data)
    if (debounce.some((item) => config.url.includes(item))) {
      //这里写加载动画
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

/**

 * @description axios响应拦截器
 */
instance.interceptors.response.use(
  (response) => {
    if (loadingInstance) loadingInstance.close()

    const { data, config } = response
    const { code, msg } = data
    // 操作正常Code数组
    const codeVerificationArray = isArray(successCode)
      ? [...successCode]
      : [...[successCode]]
    // 是否操作正常
    if (codeVerificationArray.includes(code)) {
      return data
    } else {
      handleCode(code, msg)
      return Promise.reject(
        '请求异常拦截:' + JSON.stringify({ url: config.url, code, msg }) ||
          'Error'
      )
    }
  },
  (error) => {
    if (loadingInstance) loadingInstance.close()
    const { response, msg } = error
    if (error.response && error.response.data) {
      const { status, data } = response
      handleCode(status, data.msg || msg)
      return Promise.reject(error)
    } else {
      let msg = error.message
      if (msg === 'Network Error') {
        msg = '后端接口连接异常'
      }
      if (msg.includes('timeout')) {
        msg = '后端接口请求超时'
      }
      if (msg.includes('Request failed with status code')) {
        const code = msg.substr(msg.length - 3)
        msg = '后端接口' + code + '异常'
      }
      message.error(msg || `后端接口未知异常`)
      return Promise.reject(error)
    }
  }
)

export default instance
