import request from '@/utils/request'

export function getList() {
  return request({
    url: '/order/list',
    method: 'get',
  })
}

export function createOrder(params) {
  return request({
    url: '/order/create',
    method: 'post',
    params,
  })
}

export function getListByUID(uid) {
  return request({
    url: '/order/list/uid/' + uid,
    method: 'get',
  })
}

export function getOrderByID(id) {
  return request({
    url: '/order/id/' + id,
    method: 'get',
  })
}

export function addItem(id, params) {
  return request({
    url: '/order/id/' + id + '/item',
    method: 'post',
    params,
  })
}

export function deleteItem(id, params) {
  return request({
    url: '/order/id/' + id + '/item',
    method: 'delete',
    params,
  })
}

export function updateItemAmount(oid, itemid, params) {
  return request({
    url: '/order/id/' + oid + '/item/' + itemid,
    method: 'put',
    params,
  })
}
