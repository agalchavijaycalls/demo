import {handleActions} from 'redux-actions';
import {alertActionTypes} from "../../constants";

const initialState: any = {};

export default handleActions<{}, string>({
    [alertActionTypes.SUCCESS]: (state, action: any) => {
        return {
            type: 'alert-success',
            message: action.payload
        };
    },

    [alertActionTypes.ERROR]: (state, action: any) => {
        return {
            type: 'alert-danger',
            message: action.payload
        };
    },
    [alertActionTypes.CLEAR]: (state, action: any) => {
        return {};
    },
}, initialState);