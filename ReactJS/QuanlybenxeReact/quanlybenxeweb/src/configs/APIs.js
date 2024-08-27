import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/Quanlybenxe/';

export const endpoints = {
    'categories': '/api/categories/', 
    'buses': '/api/buses/',
    'companies':'/api/companies/',
    'routes':'/api/routes/',
    'trips':'/api/trips/',
    'comments':'/api/comments/',
    'shipments':'/api/shipments',
    'bus_detail': (busID) => `/api/buses/${busID}/`,
    'trip_detail': (tripID) => `/api/trips/${tripID}/`,
    'register': '/api/users/',
    'login': '/api/login/',
    'current-user': '/api/current-user/',
    'users': '/api/users/',
    'pay': '/api/pay/'
}

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `${cookie.load('token')}`
        }
    });
}

export default axios.create({
    baseURL: BASE_URL
});