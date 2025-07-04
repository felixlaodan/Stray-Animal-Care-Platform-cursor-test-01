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