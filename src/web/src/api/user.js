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
