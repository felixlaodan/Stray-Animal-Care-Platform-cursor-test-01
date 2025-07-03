import request from '@/core/api/index.js';

export const login = (data) => {
  return request({
    url: '/api/user/login',
    method: 'post',
    data,
  });
};

export const register = (data) => {
  return request({
    url: '/api/user/register',
    method: 'post',
    data,
  });
}; 