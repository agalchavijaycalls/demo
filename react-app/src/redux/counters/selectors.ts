import {RootState} from './../../redux/root-state';

export const getCounter = (state: RootState) => state.counters.counter;