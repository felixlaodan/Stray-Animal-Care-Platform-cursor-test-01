import request from '@/core/api/index.js';

// --- Adoption Info API ---

export const getAdoptionInfos = (params) => {
  return request({
    url: '/api/rescue/adoption-info',
    method: 'get',
    params
  });
};

export const getAdoptionInfoById = (id) => {
  return request({
    url: `/api/rescue/adoption-info/${id}`,
    method: 'get'
  });
};

export const createAdoptionInfo = (data) => {
  return request({
    url: '/api/rescue/adoption-info',
    method: 'post',
    data
  });
};

export const updateAdoptionInfo = (data) => {
    return request({
        url: `/api/rescue/adoption-info`,
        method: 'put',
        data
    });
};

export const deleteAdoptionInfo = (id) => {
    return request({
        url: `/api/rescue/adoption-info/${id}`,
        method: 'delete'
    });
};

export const deleteAdoptionInfoBatch = (ids) => {
    return request({
        url: `/api/rescue/adoption-info/batch`,
        method: 'delete',
        data: ids
    });
};

// --- Adoption Records API ---

export const getAdoptionRecords = (params) => {
    return request({
        url: '/api/rescue/adoption-records',
        method: 'get',
        params
    });
};

export const getAdoptionRecordById = (id) => {
    return request({
        url: `/api/rescue/adoption-records/${id}`,
        method: 'get'
    });
};

export const createAdoptionRecord = (data) => {
    return request({
        url: '/api/rescue/adoption-records',
        method: 'post',
        data
    });
};

export const updateAdoptionRecord = (data) => {
    return request({
        url: `/api/rescue/adoption-records`,
        method: 'put',
        data
    });
};

export const deleteAdoptionRecord = (id) => {
    return request({
        url: `/api/rescue/adoption-records/${id}`,
        method: 'delete'
    });
};

export const deleteAdoptionRecordBatch = (ids) => {
    return request({
        url: `/api/rescue/adoption-records/batch`,
        method: 'delete',
        data: ids
    });
};

// --- Upload Records API ---

export const getUploadRecords = (params) => {
    return request({
        url: '/api/rescue/upload-records',
        method: 'get',
        params
    });
};

export const getUploadRecordById = (id) => {
    return request({
        url: `/api/rescue/upload-records/${id}`,
        method: 'get'
    });
};

export const createUploadRecord = (data) => {
    return request({
        url: '/api/rescue/upload-records',
        method: 'post',
        data
    });
};

export const updateUploadRecord = (data) => {
    return request({
        url: `/api/rescue/upload-records`,
        method: 'put',
        data
    });
};

export const deleteUploadRecord = (id) => {
    return request({
        url: `/api/rescue/upload-records/${id}`,
        method: 'delete'
    });
};

export const deleteUploadRecordBatch = (ids) => {
    return request({
        url: `/api/rescue/upload-records/batch`,
        method: 'delete',
        data: ids
    });
}; 