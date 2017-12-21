import {createAction} from 'redux-actions';

const INCREMENT = 'INCREMENT';
const DECREMENT = 'DECREMENT';

export const ActionTypes={INCREMENT,DECREMENT};

const increment = createAction(ActionTypes.INCREMENT);
const decrement = createAction(ActionTypes.DECREMENT);

export const Actions={increment,decrement};

// export type Actions = {
//     INCREMENT: {
//         type: typeof INCREMENT;
//     },
//     DECREMENT: {
//         type: typeof DECREMENT;
//     }
// }
//
// // Action creators
// export const actionCreators = {
//     increment: (): Actions[typeof INCREMENT] => ({
//         type: INCREMENT
//     }),
//     decrement: (): Actions[typeof DECREMENT] => ({
//         type: DECREMENT
//     })
// };