import request from '@/utils/request'
export function recharge(data) {
  return request({
    url: '/storedvalue/uid/' + data.uid + '/recharge',
    method: 'post',
    data: data,
  })
}
