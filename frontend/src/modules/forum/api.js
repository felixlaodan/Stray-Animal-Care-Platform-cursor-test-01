import apiClient from '@/core/api';

export const getPosts = (params) => {
  return apiClient.get('/api/posts', { params });
};

export const getPostDetail = (id) => {
  return apiClient.get(`/api/posts/${id}`);
}; 