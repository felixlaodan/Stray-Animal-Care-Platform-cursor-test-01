import apiClient from '@/core/api';

export const getPosts = (params) => {
  return apiClient.get('/api/posts', { params });
};

export const getPostDetail = (id) => {
  return apiClient.get(`/api/posts/${id}`);
};

export const addComment = (postId, commentData) => {
  return apiClient.post(`/api/posts/${postId}/comments`, commentData);
};

export const createPost = (postData) => {
  return apiClient.post('/api/posts', postData);
};

export const deletePost = (postId, userId) => {
  return apiClient.delete(`/api/posts/${postId}`, { params: { userId } });
};

export const deleteComment = (commentId, userId) => {
  return apiClient.delete(`/api/comments/${commentId}`, { params: { userId } });
};

export const updatePost = (postId, userId, postData) => {
  return apiClient.put(`/api/posts/${postId}`, postData, { params: { userId } });
};

export const updateComment = (commentId, userId, commentData) => {
  return apiClient.put(`/api/comments/${commentId}`, commentData, { params: { userId } });
}; 