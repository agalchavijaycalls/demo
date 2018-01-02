import {createAction} from 'redux-actions';
import {alertActionTypes} from "../../constants";

export const success = createAction<string>(alertActionTypes.SUCCESS);
export const error = createAction<string>(alertActionTypes.ERROR);
export const clear = createAction(alertActionTypes.CLEAR);

export const Actions = {success, error, clear};