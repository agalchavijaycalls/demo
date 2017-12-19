export const INCREMENT = 'INCREMENT';
export const DECREMENT = 'DECREMENT';

export type Actions = {
    INCREMENT: {
        type: typeof INCREMENT;
    },
    DECREMENT: {
        type: typeof DECREMENT;
    }
}

// Action creators
export const actionCreators = {
    increment: (): Actions[typeof INCREMENT] => ({
        type: INCREMENT
    }),
    decrement: (): Actions[typeof DECREMENT] => ({
        type: DECREMENT
    })
};