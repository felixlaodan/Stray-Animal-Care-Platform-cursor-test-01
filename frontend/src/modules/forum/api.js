import request from '@/core/api/index.js';

// 获取帖子列表（分页）
export const getPosts = (params) => {
  return request({
    url: '/forum-post/list',
    method: 'get',
    params: {
      pageNum: params.page,
      pageSize: params.size,
      keyword: params.keyword,
    },
  });
};

// 根据ID获取帖子详情
export const getPostById = (id) => {
  return request({
    url: `/forum-post/${id}`,
    method: 'get'
  });
};

// 创建新帖子
export const createPost = (data) => {
  return request({
    url: '/forum-post',
    method: 'post',
    data
  });
};

// 更新帖子
export const updatePost = (id, data) => {
    return request({
        url: `/forum-post/${id}`,
        method: 'put',
        data
    });
};

// 删除帖子
export const deletePost = (id) => {
    return request({
        url: `/forum-post/${id}`,
        method: 'delete'
    });
};

// 添加评论
export const addComment = (data) => {
  return request({
    url: `/forum-comment`,
    method: 'post',
    data
  });
};

// 根据帖子ID获取评论列表
export const getCommentsByPostId = (postId) => {
  return request({
    url: `/forum-comment/post/${postId}`,
    method: 'get',
  });
};

// 删除评论
export const deleteComment = (id) => {
    return request({
        url: `/forum-comment/${id}`,
        method: 'delete'
    });
};

// 更新评论
export const updateComment = (id, data) => {
    return request({
        url: `/forum-comment/${id}`,
        method: 'put',
        data
    });
};

// 切换帖子点赞状态
export const toggleLikePost = (postId) => {
    return request({
        url: `/forum-post/${postId}/like`,
        method: 'post'
    });
}; 