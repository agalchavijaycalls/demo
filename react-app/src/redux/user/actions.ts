import {createAction} from 'redux-actions';
import {bindActionCreators} from 'redux';
import {userService} from '../../services/user-service';
import {error as alertError} from './../alert/actions';

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


export const ActionTypes = {LOGIN_REQUEST,LOGIN_SUCCESS, LOGIN_FAILURE,REGISTER_REQUEST
    ,REGISTER_SUCCESS,REGISTER_FAILURE,GETALL_REQUEST,GETALL_SUCCESS,GETALL_FAILURE
    ,DELETE_REQUEST,DELETE_SUCCESS,DELETE_FAILURE};

const loginRequest = createAction<string>(ActionTypes.LOGIN_REQUEST);
const loginSuccess = createAction<string>(ActionTypes.LOGIN_SUCCESS);
const loginFailure = createAction<string>(ActionTypes.LOGIN_FAILURE);
const registerRequest = createAction<string>(ActionTypes.REGISTER_REQUEST);
const registerSuccess = createAction<string>(ActionTypes.REGISTER_SUCCESS);
const registerFailure = createAction(ActionTypes.REGISTER_FAILURE);
const getAllRequest = createAction<string>(ActionTypes.GETALL_REQUEST);
const getAllSuccess = createAction<string>(ActionTypes.GETALL_SUCCESS);
const getAllFailure = createAction(ActionTypes.GETALL_FAILURE);

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