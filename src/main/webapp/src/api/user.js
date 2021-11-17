import request from '@/utils/request'

export function getList() {
  return request({
    url: '/user/list',
    method: 'get',
  })
}

export function getStoredValueLogs() {
  return request({
    url: '/storedvalue/log/list',
    method: 'get',
  })
}

export function register(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data: data,
  })
}

export function updateUser(data) {
  return request({
    url: '/user/id/' + data.userid + '/update',
    method: 'post',
    data: {
      name: data.username || undefined,
      gender: data.gender,
      phone: data.phone || undefined,
      remark: data.remark || undefined,
      passwd: data.passwd || undefined,
      birthdate: data.birthdate ? data.birthdate.valueOf() : undefined,
    },
  })
}

export function verifyPasswd(data) {
  return request({
    url: '/user/id/' + data.uid + '/passwd/verify',
    method: 'post',
    data: {
      passwd: data.passwd,
    },
  })
}

import { tokenName } from '@/config'

export async function login(data) {
  return request({
    url: '/login',
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
    url: '/userInfo',
    method: 'post',
    data: {
      [tokenName]: accessToken,
    },
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post',
  })
}

// export function register() {
//   return request({
//     url: '/register',
//     method: 'post',
//   })
// }
