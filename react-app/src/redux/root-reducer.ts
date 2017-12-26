import {routerReducer as router} from 'react-router-redux';
import {reducer as counters} from './../redux/counters';
import {reducer as todos} from './../redux/todo';

import {RootState} from './../redux/root-state';
import {combineReducers} from 'redux';


export const rootReducer = combineReducers<RootState>({
    router,
    counters,
    todos
});
