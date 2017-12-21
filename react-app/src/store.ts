import {applyMiddleware, compose, createStore, Store} from 'redux';
import {createEpicMiddleware} from 'redux-observable';
import {rootEpic, rootReducer, RootState} from './redux/index';
import browserHistory from 'history/createBrowserHistory';
import {routerMiddleware} from 'react-router-redux';
import {logger} from './redux/middleware';

const composeEnhancers = (
    process.env.NODE_ENV === 'development' &&
    window && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__
) || compose;

export const history = browserHistory();

function configureStore(initialState?: RootState) {
    // configure middlewares

    const middlewares = [
        createEpicMiddleware(rootEpic),routerMiddleware(history),logger
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
    )as Store<RootState>;
}

// pass an optional param to rehydrate state on app start
const store = configureStore();

// export store singleton instance
export default store;