import {createAction} from 'redux-actions';

const SUCCESS = 'SUCCESS';
const ERROR = 'ERROR';
const CLEAR = 'CLEAR';

export const ActionTypes = {SUCCESS, ERROR, CLEAR};

export const success = createAction<string>(ActionTypes.SUCCESS);
export const error = createAction<string>(ActionTypes.ERROR);
export const clear = createAction(ActionTypes.CLEAR);

export const Actions = {success, error, clear};