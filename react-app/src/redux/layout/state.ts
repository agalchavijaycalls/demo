export interface State {
  layoutMode: string;
  profileMode: string;
  layoutCompact: boolean;
  darkMenu: boolean;

  overlayMenuActive: boolean;
  staticMenuDesktopInactive: boolean;
  staticMenuMobileActive: boolean;
  rotateMenuButton: boolean;
  topbarMenuActive: boolean;
  activeTopbarItem: string;
  rightPanelActive: boolean;
  menuActive: boolean;

  menuClick: boolean;
  topbarItemClick: boolean;
  rightPanelClick: boolean;
}

export const initialState: State = {

  layoutMode: 'static',
  profileMode: 'inline',
  layoutCompact: true,
  darkMenu: false,

  overlayMenuActive: false,
  staticMenuDesktopInactive: false,
  staticMenuMobileActive: false,
  rotateMenuButton: false,
  topbarMenuActive: false,
  activeTopbarItem: '',
  rightPanelActive: false,
  menuActive: false,

  menuClick: false,
  topbarItemClick: false,
  rightPanelClick: false

};