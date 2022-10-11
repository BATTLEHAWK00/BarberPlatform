import request from '@util/request';

async function login(username: string, password: string) {
  request.get('/auth/login', {
    params: {
      username,
      password,
    },
  });
}

export default {
  login,
};
