import {handleActions} from 'redux-actions';
import {todoActionTypes as Actions} from '../../constants/todo-constants';

const initialState: TodoStoreState = [];

export default handleActions<TodoStoreState, any>({
    [Actions.ADD_TODO]: (state, action) => {
        return [{
            id: state.reduce((maxId, todo) => Math.max(todo.id, maxId), -1) + 1,
            completed: false,
            ...action.payload,
        }, ...state];
    },

    [Actions.RESET_WITH_TODOS]: (state, action) => {
        return action.payload;
    },

    [Actions.DELETE_TODO]: (state, action) => {
        return state.filter((todo:TodoItemData) => todo.id !== action.payload);
    },

    [Actions.EDIT_TODO]: (state, action) => {
        return state.map(todo => {
            return todo.id === action.payload.id
                ? {...todo, text: action.payload.title}
                : todo;
        });
    },

    [Actions.COMPLETE_TODO]: (state, action) => {
        return state.map(todo => {
            return todo.id === action.payload
                ? {...todo, completed: !todo.completed}
                : todo;
        });
    },

    [Actions.COMPLETE_ALL]: (state, action) => {
        const areAllMarked = state.every(todo => todo.completed);
        return state.map(todo => {
            return {
                ...todo,
                completed: !areAllMarked
            };
        });
    },

    [Actions.CLEAR_COMPLETED]: (state, action) => {
        return state.filter(todo => todo.completed === false);
    }
}, initialState);
