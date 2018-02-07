import browserHistory from 'history/createBrowserHistory';
import { routerMiddleware } from 'react-router-redux';
import { applyMiddleware, compose, createStore, Store } from 'redux';
import { createLogger } from 'redux-logger';
import { createEpicMiddleware } from 'redux-observable';
import thunkMiddleware from 'redux-thunk';
import { rootEpic, rootReducer, RootState } from './index';

const composeEnhancers = (process.env.NODE_ENV === 'development' && window
    && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__) || compose;

const history = browserHistory();

function configureStore(initialState?: RootState) {
  // configure middlewares

  const middlewares = [createEpicMiddleware(rootEpic), routerMiddleware(history), createLogger(), thunkMiddleware];
  // compose enhancers
  const enhancer = composeEnhancers(applyMiddleware(...middlewares));
  // create store
  return createStore(rootReducer, initialState!, enhancer)as Store<RootState>;
}

// pass an optional param to rehydrate state on app start
const store = configureStore();

// export store singleton instance
export { store, history };