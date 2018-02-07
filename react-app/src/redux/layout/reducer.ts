import * as update from 'immutability-helper';
import { handleActions } from 'redux-actions';
import * as Actions from './constants';
import { initialState, State } from './state';

export default handleActions<State, string | boolean>({
  [Actions.ON_MENU_CLICK]: (state, action) => {
    return Object.assign({}, state, {menuClick: action.payload});
  },

  [Actions.ON_MENU_BUTTON_CLICK]: (state, action) => {
    let partialState: Partial<State> | undefined;
    partialState = {};
    partialState.menuClick = true;
    partialState.rotateMenuButton = !state.rotateMenuButton;
    partialState.topbarMenuActive = false;
    if (state.layoutMode === 'overlay') {
      partialState.overlayMenuActive = !state.overlayMenuActive;
    } else {
      if (action.payload) {
        partialState.staticMenuDesktopInactive = !state.staticMenuDesktopInactive;
      } else {
        partialState.staticMenuMobileActive = !state.staticMenuMobileActive;
      }
    }
    return partialState != null ? {...state, ...partialState} : state;
  },

  [Actions.ON_TOPBAR_MENUBUTTON_CLICK]: (state, action) => {
    let partialState: Partial<State> | undefined;
    partialState = {};
    partialState.topbarItemClick = true;
    partialState.topbarMenuActive = !state.topbarMenuActive;
    partialState.rotateMenuButton = false;
    partialState.overlayMenuActive = false;
    partialState.staticMenuMobileActive = false;
    return partialState != null ? {...state, ...partialState} : state;
  },
  [Actions.ON_TOPBAR_ITEM_CLICK]: (state, action) => {
    let partialState: Partial<State> | undefined;
    partialState = {};
    partialState.topbarItemClick = true;
    if (state.activeTopbarItem === action.payload) {
      partialState.activeTopbarItem = '';
    } else {
      partialState.activeTopbarItem = <string> action.payload;
    }

    return partialState != null ? {...state, ...partialState} : state;
  },
  [Actions.HIDE_OVERLAY_MENU]: (state, action) => {
    let partialState: Partial<State> | undefined;
    partialState = {};
    partialState.rotateMenuButton = false;
    partialState.overlayMenuActive = false;
    partialState.staticMenuMobileActive = false;
    return partialState != null ? {...state, ...partialState} : state;
  },
  [Actions.SET_MENU_ACTIVE]: (state, action) => {
    return Object.assign({}, state, {menuActive: action.payload});
  },
  [Actions.ON_RIGHT_PANEL_BUTTON_CLICK]: (state, action) => {
    let partialState: Partial<State> | undefined;
    partialState = {};
    partialState.rightPanelClick = true;
    partialState.rightPanelActive = !state.rightPanelActive;
    return partialState != null ? {...state, ...partialState} : state;
  },
  [Actions.ON_RIGHT_PANEL_CLICK]: (state, action) => {
    return Object.assign({}, state, {rightPanelClick: action.payload});
  },
  [Actions.ON_DOCUMENT_CLICK]: (state, action) => {
    let partialState: Partial<State> | undefined;
    partialState = {};
    if (!state.topbarItemClick) {
      partialState.activeTopbarItem = '';
      partialState.topbarMenuActive = false;
    }
    if (!state.menuClick) {
      if ((state.layoutMode === 'horizontal') || (state.layoutMode === 'slim')) {
        partialState.menuActive = false;
      }
      partialState.rotateMenuButton = false;
      partialState.overlayMenuActive = false;
      partialState.staticMenuMobileActive = false;
    }

    if (!state.rightPanelClick) {
      partialState.rightPanelActive = false;
    }

    partialState.topbarItemClick = false;
    partialState.menuClick = false;
    partialState.rightPanelClick = false;
    return partialState != null ? {...state, ...partialState} : state;
  },

  [Actions.SET_LAYOUT_COMPACT]: (state, action) => {
    return update(state, {layoutCompact: {$set: action.payload}});
  },

  [Actions.SET_LAYOUT_MODE]: (state, action) => {
    let profileMode: string = state.profileMode;
    if (action.payload === 'horizontal') {
      profileMode = 'top';
    }
    return Object.assign({}, state, {layoutMode: action.payload, profileMode: profileMode});
  },

  [Actions.SET_DARK_MENU]: (state, action) => {
    return Object.assign({}, state, {darkMenu: action.payload});
  },

  [Actions.SET_PROFILE_MODE]: (state, action) => {
    return Object.assign({}, state, {profileMode: action.payload});
  }

}, initialState);
