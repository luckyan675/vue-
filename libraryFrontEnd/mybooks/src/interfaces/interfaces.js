import axios from "axios"
import qs from "qs"

// 登录
const login = (account,passwd)=>{
    return axios.post('/api/login',qs.stringify({       // 还可以直接把参数拼接在url后边
        account,passwd
    }))
}

// 获取图书列表
const getbooklist = ()=>{
    return axios.get('/api/get/booklist')
}

// 获取图书分类列表
const getClassificationList = ()=>{
    return axios.get('/api/get/classificationlist')
}

// 关键字查询图书列表
const queryBookByKeyWd = (keywords)=>{
    return axios.get('/api/query/book?keyWords='+encodeURI(encodeURI(keywords)))
}

// 注册
const signin = (userData) => {
    return axios.get('/api/signin',qs.stringify(userData))
}
export {
    login,getbooklist,getClassificationList,queryBookByKeyWd,signin
}