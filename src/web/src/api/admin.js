import request from '@/utils/request'
import { tokenName } from '@/config'

export function getList() {
  return request({
    url: '/admin/list',
    method: 'get',
  })
}

export async function login(data) {
  return request({
    url: '/admin/login',
    method: 'post',
    data,
  })
}

export async function socialLogin(data) {
  return request({
    url: '/socialLogin',
    method: 'post',
    data,
  })
}

export function getUserInfo(accessToken) {
  //此处为了兼容mock.js使用data传递accessToken，如果使用mock可以走headers
  return request({
    url: '/admin/userInfo',
    method: 'post',
    data: {
      [tokenName]: accessToken,
    },
  })
}

export function logout(accessToken) {
  return request({
    url: '/admin/logout',
    method: 'post',
    data: {
      [tokenName]: accessToken,
    },
  })
}

export function register() {
  return request({
    url: '/register',
    method: 'post',
  })
}
