import {createAction} from 'redux-actions';
import {bindActionCreators} from 'redux';
import {userService} from '../../services/user-service';
import {error as alertError, success as alertSuccess} from './../alert/actions';
import {history} from './../../store';

const LOGIN_REQUEST = 'LOGIN_REQUEST';
const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
const LOGIN_FAILURE = 'LOGIN_FAILURE';
const REGISTER_REQUEST = 'REGISTER_REQUEST';
const REGISTER_SUCCESS = 'REGISTER_SUCCESS';
const REGISTER_FAILURE = 'REGISTER_FAILURE';
const GETALL_REQUEST = 'GETALL_REQUEST';
const GETALL_SUCCESS = 'GETALL_SUCCESS';
const GETALL_FAILURE = 'GETALL_FAILURE';
const DELETE_REQUEST = 'DELETE_REQUEST';
const DELETE_SUCCESS = 'DELETE_SUCCESS';
const DELETE_FAILURE = 'DELETE_FAILURE';
const LOGOUT = 'LOGOUT';


export const ActionTypes = {LOGIN_REQUEST,LOGIN_SUCCESS, LOGIN_FAILURE,REGISTER_REQUEST
    ,REGISTER_SUCCESS,REGISTER_FAILURE,GETALL_REQUEST,GETALL_SUCCESS,GETALL_FAILURE
    ,DELETE_REQUEST,DELETE_SUCCESS,DELETE_FAILURE,LOGOUT};

const loginRequest = createAction<string>(ActionTypes.LOGIN_REQUEST);
const loginSuccess = createAction<string>(ActionTypes.LOGIN_SUCCESS);
const loginFailure = createAction<string>(ActionTypes.LOGIN_FAILURE);
const registerRequest = createAction<string>(ActionTypes.REGISTER_REQUEST);
const registerSuccess = createAction(ActionTypes.REGISTER_SUCCESS);
const registerFailure = createAction<string>(ActionTypes.REGISTER_FAILURE);
const getAllRequest = createAction(ActionTypes.GETALL_REQUEST);
const getAllSuccess = createAction<any>(ActionTypes.GETALL_SUCCESS);
const getAllFailure = createAction<string>(ActionTypes.GETALL_FAILURE);
const logout = createAction(ActionTypes.LOGOUT);

function login(username:string,password:string){
    return (dispatch:any)=>{
        bindActionCreators({loginRequest,loginSuccess,loginFailure,alertError},dispatch);
        loginRequest(username);

        userService.login(username, password)
            .then(
                (user:any) => {
                    dispatch(loginSuccess(user));
                    history.push('/');
                },
                (error:any) => {
                    dispatch(loginFailure(error));
                    dispatch(alertError(error));
                }
            );
    };
}

function logout() {
    return (dispatch:any)=> {
        bindActionCreators({logout}, dispatch);
        userService.logout();
        logout();
    }

}

function register(user:any) {
    return (dispatch:any) => {
        bindActionCreators({registerRequest, registerSuccess, registerFailure, alertError, alertSuccess}, dispatch);
        registerRequest(user);

        userService.register(user)
            .then(
                (user:any) => {
                    registerSuccess();
                    history.push('/login');
                    alertSuccess('Registration successful');
                },
                (error:any) => {
                    registerFailure(error);
                    alertError(error);
                }
            );
    };
}

function getAll() {
    return (dispatch:any) => {
        bindActionCreators({getAllRequest, getAllSuccess, getAllFailure}, dispatch);
        getAllRequest();

        userService.getAll()
            .then(
                (users:any) => getAllSuccess(users),
                (error:any) => getAllFailure(error)
            );
    };

}