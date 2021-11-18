import request from '@/utils/request'
import { tokenName } from '@/config'

export function firstTime() {
  return request({
    url: '/system/firstTime',
    method: 'get',
  })
}

export async function login(data) {
  return request({
    url: '/system/login',
    method: 'post',
    data,
  })
}

export function getUserInfo(accessToken) {
  //此处为了兼容mock.js使用data传递accessToken，如果使用mock可以走headers
  return request({
    url: '/system/userInfo',
    method: 'post',
    data: {
      [tokenName]: accessToken,
    },
  })
}

export function logout() {
  return request({
    url: '/system/logout',
    method: 'post',
  })
}

export function setLoginToken(username, password) {
  return request({
    url: '/system/setLoginToken',
    method: 'post',
    data: {
      username: username,
      password: password,
    },
  })
}
