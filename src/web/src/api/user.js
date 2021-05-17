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

export function verifyPasswd(data) {
  return request({
    url: '/user/id/' + data.uid + '/passwd/verify',
    method: 'post',
    data: {
      passwd: data.passwd,
    },
  })
}
