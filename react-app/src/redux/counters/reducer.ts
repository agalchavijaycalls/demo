import {State} from './state';
import {ActionTypes} from './../../redux/counters/actions';
import {handleActions} from 'redux-actions';

const initialState: State = {
    counter: 0
};

export default handleActions<State, string>({
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


// export const reducer = combineReducers<State, RootAction>({
//     counter: (state = 0, action) => {
//         switch (action.type) {
//             case INCREMENT:
//                 return state + 1;
//
//             case DECREMENT:
//                 return state + 1;
//
//             default:
//                 return state;
//         }
//     },
// });
