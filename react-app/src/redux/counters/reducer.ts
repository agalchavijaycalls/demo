import {State} from './state';
import {ActionTypes} from './../../redux/counters/actions';
import {handleActions} from 'redux-actions';

const initialState: State = {
    counter: 0
};

export default handleActions<State, void>({
    [ActionTypes.INCREMENT]: (state, action) => {
        return {
            counter: state.counter + 1
        };
    },

    [ActionTypes.DECREMENT]: (state, action) => {
        return {
            counter: state.counter - 1
        };
    },
}, initialState);