import {
    Dispatch as ReduxDispatch,
    Reducer as ReduxReducer,
} from 'redux';

import { RootState, RootAction } from './../redux';

export type Dispatch = ReduxDispatch<RootAction>;
export type Reducer = ReduxReducer<RootState, RootAction>;

export type Api = {};
