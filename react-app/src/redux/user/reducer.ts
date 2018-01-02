import {handleActions} from 'redux-actions';
import {userActionTypes} from "../../constants";

let user = JSON.parse(localStorage.getItem('user'));

const initialState: {} = user ? {loggedIn: true, user} : {};

const authReducer = handleActions<{}, {}>({
    [userActionTypes.LOGIN_REQUEST]: (state, action: any) => {
        return {
            loggingIn: true,
            user: action.user
        };
    },

    [userActionTypes.LOGIN_SUCCESS]: (state, action: any) => {
        return {
            loggedIn: true,
            user: action.user
        };
    },
    [userActionTypes.LOGIN_FAILURE]: (state, action: any) => {
        return {};
    },
    [userActionTypes.LOGOUT]: (state, action: any) => {
        return {};
    },
}, initialState);

const regReducer = handleActions<{}, {}>({
    [userActionTypes.REGISTER_REQUEST]: (state, action: any) => {
        return {registering: true};
    }, [userActionTypes.REGISTER_SUCCESS]: (state, action: any) => {
        return {};
    },
    [userActionTypes.REGISTER_FAILURE]: (state, action: any) => {
        return {};
    }
}, {});

export const reducers = {authReducer, regReducer};