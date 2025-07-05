import request from '@/core/api/index.js';

// --- Adoption Info & Record API ---

export const getAdoptionInfosPage = (params) => {
    return request({
        url: '/api/adoption/infos',
        method: 'get',
        params
    });
};

export const getAdoptionInfoDetail = (id) => {
    return request({
        url: `/api/adoption/infos/${id}`,
        method: 'get'
    });
};

export const createAdoptionRecord = (data) => {
    return request({
        url: '/api/adoption/records',
        method: 'post',
        data
    });
};

export const updateAdoptionRecord = (id, data) => {
    return request({
        url: `/api/adoption/records/${id}`,
        method: 'put',
        data
    });
};

export const getMyAdoptionRecords = () => {
    return request({
        url: '/api/adoption/records/my',
        method: 'get'
    });
};

// --- Upload Records API ---

/**
 * 分页查询上报记录
 * @param {object} params - 查询参数，例如 { pageNum: 1, pageSize: 10 }
 */
export const getUploadRecordsPage = (params) => {
    return request({
        url: '/api/rescue/upload-records',
        method: 'get',
        params
    });
};

/**
 * 创建一个新的上报记录
 * @param {object} data - 上报记录的数据，包含图片URL列表
 */
export const createUploadRecord = (data) => {
    return request({
        url: '/api/rescue/upload-records',
        method: 'post',
        data
    });
};

/**
 * 根据ID获取单个上报记录详情
 * @param {number} id - 记录ID
 */
export const getUploadRecordById = (id) => {
    return request({
        url: `/api/rescue/upload-records/${id}`,
        method: 'get'
    });
};

/**
 * 更新一个上报记录
 * @param {number} id - 记录ID
 * @param {object} data - 更新的数据
 */
export const updateUploadRecord = (id, data) => {
    return request({
        url: `/api/rescue/upload-records/${id}`,
        method: 'put',
        data
    });
};

/**
 * 删除一个上报记录
 * @param {number} id - 记录ID
 */
export const deleteUploadRecord = (id) => {
    return request({
        url: `/api/rescue/upload-records/${id}`,
        method: 'delete'
    });
};

/**
 * (管理员) 获取所有上报记录
 * @param {object} params - 查询参数，例如 { pageNum: 1, pageSize: 10, keyword: '...' }
 */
export const adminGetAllUploads = (params) => {
    return request({
        url: '/api/admin/uploads',
        method: 'get',
        params
    });
};

/**
 * (管理员) 删除一个上报记录
 * @param {number} id - 记录ID
 */
export const adminDeleteUploadRecord = (id) => {
    return request({
        url: `/api/admin/uploads/${id}`,
        method: 'delete'
    });
};

/**
 * (管理员) 根据ID获取单个上报记录详情
 * @param {number} id - 记录ID
 */
export const adminGetUploadById = (id) => {
    return request({
        url: `/api/admin/uploads/${id}`,
        method: 'get'
    });
};

/**
 * (管理员) 更新一个上报记录
 * @param {number} id - 记录ID
 * @param {object} data - 更新的数据
 */
export const adminUpdateUploadRecord = (id, data) => {
    return request({
        url: `/api/admin/uploads/${id}`,
        method: 'put',
        data
    });
}; 