import {createAction} from 'redux-actions';

const INCREMENT = 'INCREMENT';
const DECREMENT = 'DECREMENT';

export const ActionTypes={INCREMENT,DECREMENT};

const increment = createAction(ActionTypes.INCREMENT);
const decrement = createAction(ActionTypes.DECREMENT);

export const Actions={increment,decrement};