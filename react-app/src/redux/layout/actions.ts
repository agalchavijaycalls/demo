import { createAction } from 'redux-actions';
import * as Actions from './constants';

export const setLayoutMode = createAction<string>(Actions.SET_LAYOUT_MODE);
export const setProfileMode = createAction<string>(Actions.SET_PROFILE_MODE);
export const setLayoutCompact = createAction<boolean>(Actions.SET_LAYOUT_COMPACT);
export const setDarkMenu = createAction<boolean>(Actions.SET_DARK_MENU);

export const onMenuClick = createAction<boolean>(Actions.ON_MENU_CLICK);
export const onMenuButtonClick = createAction<boolean>(Actions.ON_MENU_BUTTON_CLICK);
export const onTopbarMenuButtonClick = createAction<boolean>(Actions.ON_TOPBAR_MENUBUTTON_CLICK);
export const onTopbarItemClick = createAction<string>(Actions.ON_TOPBAR_ITEM_CLICK);
export const hideOverlayMenu = createAction<boolean>(Actions.HIDE_OVERLAY_MENU);
export const setMenuActive = createAction<boolean>(Actions.SET_MENU_ACTIVE);
export const onRightPanelButtonClick = createAction<boolean>(Actions.ON_RIGHT_PANEL_BUTTON_CLICK);
export const onRightPanelClick = createAction<boolean>(Actions.ON_RIGHT_PANEL_CLICK);
export const onDocumentClick = createAction(Actions.ON_DOCUMENT_CLICK);