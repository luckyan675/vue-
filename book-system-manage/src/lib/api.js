import axios from '@/axios/index'
//获取一级分类
const getCatalogs = (params) => axios({
    url: '/catalogs',
    method: 'get',
    params
})
//修改一级分类
const updateCatalog = (data) => axios({
    url: `/catalogs/${data.id}`,
    method: 'put',
    data
})
//创建一级分类
const createCatalog = (data) => axios({
    url: `/catalogs`,
    method: 'post',
    data
})
const deleteCatalog = (id) => axios({
    url: `/catalogs/${id}`,
    method: 'delete'
})
//获取所有书籍
const getBooks = (params) => axios({
    url: '/books',
    method: 'get',
    params
})
//更新书籍信息
const updateBook = (data) => axios({
    url: `/books/${data.id}`,
    method: 'put',
    data
})
//创建书籍
const createBook = (data) => axios({
    url: `/books`,
    method: 'post',
    data
})
//删除书籍
const deleteBook = (id) => axios({
    url: `/books/${id}`,
    method: 'delete'
})
//查询订单
const getOrders = (params) => axios({
    url: '/orders',
    method: 'get',
    params
})
//删除订单
const deleteOrder = (id) => axios({
    url: `/orders/${id}`,
    method: 'delete'
})
//获取所有用户
const getUsers = (params) => axios({
    url: '/users',
    method: 'get',
    params
})
//注册
const register = (data) => axios({
    url: `/register`,
    method: 'post',
    data
})
//更新用户
const updateUser = (data) => axios({
    url: `/users/${data.id}`,
    method: 'put',
    data
})
const deleteUser = (id) => axios({
    url: `/users/${id}`,
    method: 'delete'
})
export {
    getCatalogs,
    updateCatalog,
    createCatalog,
    deleteCatalog,

    getBooks,
    updateBook,
    createBook,
    deleteBook,

    getOrders,
    deleteOrder,

    getUsers,
    register,
    updateUser,
    deleteUser
}
