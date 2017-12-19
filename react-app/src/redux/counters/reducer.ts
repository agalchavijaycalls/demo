import {State} from './state';
import {RootAction} from './../../redux/root-action';
import {DECREMENT, INCREMENT} from './../../redux/counters/actions';
import {combineReducers} from 'redux';

export const reducer = combineReducers<State, RootAction>({
    counter: (state = 0, action) => {
        switch (action.type) {
            case INCREMENT:
                return state + 1;

            case DECREMENT:
                return state + 1;

            default:
                return state;
        }
    },
});
