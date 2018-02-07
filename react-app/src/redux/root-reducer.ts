import { routerReducer } from 'react-router-redux';
import { combineReducers } from 'redux';
import { reducer as layoutReducer } from './layout';
import { RootState } from './root-state';

export const rootReducer = combineReducers<RootState>({
  router: routerReducer, layout: layoutReducer
});
