import request from '@util/request';
async function fuzzySearch(query: string) {
  return request.get('/customer/fuzzySearch', {
    params: {
      query,
    },
  });
}

export default {
  fuzzySearch,
};
