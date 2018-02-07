import { RouterState } from 'react-router-redux';
import { State as LayoutState } from './layout';

export interface RootState {
  router: RouterState;
  layout: LayoutState;
}