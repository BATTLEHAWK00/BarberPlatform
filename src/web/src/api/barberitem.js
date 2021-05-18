import request from '@/utils/request'
export function getList() {
  return request({
    url: '/barberitem/list',
    method: 'get',
  })
}

export function createItem(data) {
  return request({
    url: '/barberitem/',
    method: 'post',
    data,
  })
}
