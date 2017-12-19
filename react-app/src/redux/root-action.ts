// RootActions
import {RouterAction, LocationChangeAction} from 'react-router-redux';

import {Actions as CountersActions} from './../redux/counters';

type ReactRouterAction = RouterAction | LocationChangeAction;

export type RootAction =
    | ReactRouterAction
    | CountersActions[keyof CountersActions];