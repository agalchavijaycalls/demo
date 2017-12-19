import {RouterState} from 'react-router-redux';
import {State as CounterState} from './../redux/counters/state';


interface StoreEnhancerState {
}

export interface RootState extends StoreEnhancerState {
    router: RouterState;
    counters: CounterState;
}