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

/**
 * (管理员) 分页查询所有用户
 * @param {object} params - 查询参数，例如 { pageNum: 1, pageSize: 10, keyword: '...' }
 */
export const adminGetAllUsers = (params) => {
  return request({
    url: '/api/admin/users',
    method: 'get',
    params
  });
};

/**
 * (管理员) 切换用户状态
 * @param {number} id - 用户ID
 */
export const adminToggleUserStatus = (id) => {
  return request({
    url: `/api/admin/users/${id}/status`,
    method: 'put'
  });
};

/**
 * (管理员) 创建一个新用户
 * @param {object} data - 用户数据
 */
export const adminCreateUser = (data) => {
  return request({
    url: '/api/admin/users',
    method: 'post',
    data
  });
};

/**
 * (管理员) 更新一个用户信息
 * @param {number} id - 用户ID
 * @param {object} data - 更新的数据
 */
export const adminUpdateUser = (id, data) => {
  return request({
    url: `/api/admin/users/${id}`,
    method: 'put',
    data
  });
}; 