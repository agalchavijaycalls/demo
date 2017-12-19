import {applyMiddleware, compose, createStore} from 'redux';
import {createEpicMiddleware} from 'redux-observable';
import {rootEpic, rootReducer, RootState} from './redux/index';
import createHistory from 'history/createBrowserHistory';
import {routerMiddleware} from 'react-router-redux';

const composeEnhancers = (
    process.env.NODE_ENV === 'development' &&
    window && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__
) || compose;

export const history = createHistory();

function configureStore(initialState?: RootState) {
    // configure middlewares
    const middlewares = [
        createEpicMiddleware(rootEpic),routerMiddleware(history)
    ];
    // compose enhancers
    const enhancer = composeEnhancers(
        applyMiddleware(...middlewares),
    );
    // create store
    return createStore(
        rootReducer,
        initialState!,
        enhancer,
    );
}

// pass an optional param to rehydrate state on app start
const store = configureStore();

// export store singleton instance
export default store;