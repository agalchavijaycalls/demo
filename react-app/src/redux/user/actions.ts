import {createAction} from 'redux-actions';
import {bindActionCreators} from 'redux';
import {userService} from '../../services/user-service';
import {error as alertError, success as alertSuccess} from './../alert/actions';
import {history} from './../../store';
import {userActionTypes} from "../../constants";

const loginRequest = createAction<string>(userActionTypes.LOGIN_REQUEST);
const loginSuccess = createAction<string>(userActionTypes.LOGIN_SUCCESS);
const loginFailure = createAction<string>(userActionTypes.LOGIN_FAILURE);
const registerRequest = createAction<string>(userActionTypes.REGISTER_REQUEST);
const registerSuccess = createAction(userActionTypes.REGISTER_SUCCESS);
const registerFailure = createAction<string>(userActionTypes.REGISTER_FAILURE);
const getAllRequest = createAction(userActionTypes.GETALL_REQUEST);
const getAllSuccess = createAction<any>(userActionTypes.GETALL_SUCCESS);
const getAllFailure = createAction<string>(userActionTypes.GETALL_FAILURE);
const deleteRequest = createAction<any>(userActionTypes.DELETE_REQUEST);
const deleteSuccess = createAction<any>(userActionTypes.DELETE_SUCCESS);
const deleteFailure = createAction<any>(userActionTypes.DELETE_FAILURE);
const logoutAction = createAction(userActionTypes.LOGOUT);

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
        bindActionCreators({logoutAction}, dispatch);

        userService.logout();
        logoutAction();
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

function _delete(id:any) {
    return (dispatch:any) => {
        bindActionCreators({deleteRequest, deleteSuccess, deleteFailure}, dispatch);

        deleteRequest(id);

        userService.delete(id)
            .then(
                user => {
                    deleteSuccess(id);
                },
                error => {
                    deleteFailure({id, error});
                }
            );
    };

}

export const userActions = {
    login,
    logout,
    register,
    getAll,
    delete: _delete
};