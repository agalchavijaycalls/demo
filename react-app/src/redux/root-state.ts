import {RouterState} from 'react-router-redux';
import {State as CounterState} from './../redux/counters/state';

export interface RootState {
    router: RouterState;
    counters: CounterState;
    todos: TodoStoreState;
    alert: any,
    authentication: any,
    registration: any
}