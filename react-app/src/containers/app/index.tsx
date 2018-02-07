import * as classNames from 'classnames';

import * as jQuery from 'jquery';
import 'nanoscroller';
import * as React from 'react';
import { connect } from 'react-redux';
import { Route } from 'react-router';
import { RouteComponentProps, Switch } from 'react-router-dom';
import { bindActionCreators } from 'redux';
import { Footer } from '../../components/core/footer';
import { Header } from '../../components/core/header/index';
import { InlineProfile } from '../../components/core/inlineprofile/index';
import { Menubar } from '../../components/core/menubar/menubar';
import { RightPanel } from '../../components/core/rightpanel/index';
import { DemoContainer } from '../demo';
import { Home } from '../home';
import { RootState } from './../../redux';
import * as LayoutActions from './../../redux/layout/actions';
import { About } from 'src/containers/about';
import { State as LayoutState } from './../../redux/layout/state';

export namespace App {
  export interface Props extends RouteComponentProps<void> {
    layout: LayoutState;
    actions: typeof LayoutActions;
  }

  export interface State {
    menu: Array<object>;
  }
}

function mapStateToProps(state: RootState) {
  return {
    layout: state.layout
  };
}

function mapDispathToProps(dispatch: any) {
  return {
    actions: bindActionCreators(LayoutActions as any, dispatch)
  };
}

@connect(mapStateToProps, mapDispathToProps)
export class App extends React.Component<App.Props, App.State> {

  layoutContainer: HTMLDivElement | null;
  layoutMenuScroller: any | null;
  menu: Array<any>;

  constructor(props: App.Props) {
    super(props);
    this.onDocumentClick = this.onDocumentClick.bind(this);
    this.onMenuClick = this.onMenuClick.bind(this);
    this.onMenuButtonClick = this.onMenuButtonClick.bind(this);
    this.onTopbarMenuButtonClick = this.onTopbarMenuButtonClick.bind(this);
    this.onTopbarItemClick = this.onTopbarItemClick.bind(this);
    this.onRootMenuItemClick = this.onRootMenuItemClick.bind(this);
    this.onRightPanelButtonClick = this.onRightPanelButtonClick.bind(this);
    this.onRightPanelClick = this.onRightPanelClick.bind(this);
    this.onMenuItemClick = this.onMenuItemClick.bind(this);
    this.createMenu();
  }

  componentDidMount() {
    jQuery(this.layoutMenuScroller).nanoScroller({flash: true});
  }

  onMenuClick(event: any) {
    this.props.actions.onMenuClick(true);

    if (!this.isHorizontal()) {
      setTimeout(() => {
        jQuery(this.layoutMenuScroller).nanoScroller();
      },         500);
    }
  }

  onMenuButtonClick(event: any) {
    this.props.actions.onMenuButtonClick(this.isDesktop());
    event.preventDefault();
  }

  onTopbarMenuButtonClick(event: any) {
    this.props.actions.onTopbarMenuButtonClick(true);
    event.preventDefault();
  }

  onTopbarItemClick(event: any, item: string) {
    this.props.actions.onTopbarItemClick(item);
    event.preventDefault();
  }

  onMenuItemClick(event: any) {
    if (!event.item.items) {
      this.props.actions.hideOverlayMenu(true);
    }
  }

  onRootMenuItemClick(event: any) {
    this.props.actions.setMenuActive(!this.props.layout.menuActive);
    event.originalEvent.preventDefault();
  }

  onRightPanelButtonClick(event: any) {
    this.props.actions.onRightPanelButtonClick(true);
    event.preventDefault();
  }

  onRightPanelClick(event: any) {
    this.props.actions.onRightPanelClick(true);
  }

  onDocumentClick(event: any) {
    this.props.actions.onDocumentClick();
  }

  isDesktop() {
    return window.innerWidth > 1024;
  }

  isHorizontal() {
    return this.props.layout.layoutMode === 'horizontal';
  }

  changeTheme(theme: string) {
    // this.changeStyleSheetUrl('layout-css', theme, 'layout');
    // this.changeStyleSheetUrl('theme-css', theme, 'theme');
  }

  createMenu() {
    this.menu = [
      {
        label: 'Dashboard', icon: 'dashboard', command: () => {
          window.location.hash = '/';
        }
      },
      {
        label: 'Themes', icon: 'palette', badge: '6',
        items: [
          {
            label: 'Indigo - Pink', icon: 'brush', command: (event: any) => {
              this.changeTheme('indigo');
            }
          },
          {
            label: 'Brown - Green', icon: 'brush', command: (event: any) => {
              this.changeTheme('brown');
            }
          },
          {
            label: 'Blue - Amber', icon: 'brush', command: (event: any) => {
              this.changeTheme('blue');
            }
          },
          {
            label: 'Blue Grey - Green', icon: 'brush', command: (event: any) => {
              this.changeTheme('blue-grey');
            }
          },
          {
            label: 'Dark - Blue', icon: 'brush', command: (event: any) => {
              this.changeTheme('dark-blue');
            }
          },
          {
            label: 'Dark - Green', icon: 'brush', command: (event: any) => {
              this.changeTheme('dark-green');
            }
          },
          {
            label: 'Green - Yellow', icon: 'brush', command: (event: any) => {
              this.changeTheme('green');
            }
          },
          {
            label: 'Purple - Cyan', icon: 'brush', command: (event: any) => {
              this.changeTheme('purple-cyan');
            }
          },
          {
            label: 'Purple - Amber', icon: 'brush', command: (event: any) => {
              this.changeTheme('purple-amber');
            }
          },
          {
            label: 'Teal - Lime', icon: 'brush', command: (event: any) => {
              this.changeTheme('teal');
            }
          },
          {
            label: 'Cyan - Amber', icon: 'brush', command: (event: any) => {
              this.changeTheme('cyan');
            }
          },
          {
            label: 'Grey - Deep Orange', icon: 'brush', command: (event: any) => {
              this.changeTheme('grey');
            }
          }
        ]
      },
      {
        label: 'Customization', icon: 'settings_application',
        items: [
          {
            label: 'Compact Size',
            icon: 'fiber_manual_record',
            command: () => this.props.actions.setLayoutCompact(true)
          },
          {
            label: 'Material Size',
            icon: 'fiber_smart_record',
            command: () => this.props.actions.setLayoutCompact(false)
          },
          {label: 'Static Menu', icon: 'menu', command: () => this.props.actions.setLayoutMode('static')},
          {label: 'Overlay Menu', icon: 'exit_to_app', command: () => this.props.actions.setLayoutMode('overlay')},
          {label: 'Slim Menu', icon: 'more_vert', command: () => this.props.actions.setLayoutMode('slim')},
          {
            label: 'Horizontal Menu',
            icon: 'border_horizontal',
            command: () => this.props.actions.setLayoutMode('horizontal')
          },
          {label: 'Light Menu', icon: 'label_outline', command: () => this.props.actions.setDarkMenu(false)},
          {label: 'Dark Menu', icon: 'label', command: () => this.props.actions.setDarkMenu(true)},
          {label: 'Inline Profile', icon: 'contacts', command: () => this.props.actions.setProfileMode('inline')},
          {label: 'Top Profile', icon: 'person_pin', command: () => this.props.actions.setProfileMode('top')}
        ]
      },
      {
        label: 'Components', icon: 'list', badge: '2', badgeStyleClass: 'teal-badge',
        items: [
          {
            label: 'Sample Page', icon: 'desktop_mac', command: () => {
              this.props.history.push('/sample');
            }
          },
          {
            label: 'Forms', icon: 'input', command: () => {
              this.props.history.push('/forms');
            }
          },
          {
            label: 'Data', icon: 'grid_on', command: () => {
              this.props.history.push('/data');
            }
          },
          {
            label: 'Panels', icon: 'content_paste', command: () => {
              this.props.history.push('/panels');
            }
          },
          {
            label: 'Overlays', icon: 'content_copy', command: () => {
              this.props.history.push('/overlays');
            }
          },
          {
            label: 'Menus', icon: 'menu', command: () => {
              this.props.history.push('/menus');
            }
          },
          {
            label: 'Messages', icon: 'message', command: () => {
              this.props.history.push('/messages');
            }
          },
          {
            label: 'Charts', icon: 'insert_chart', command: () => {
              this.props.history.push('/charts');
            }
          },
          {
            label: 'Misc', icon: 'toys', command: () => {
              this.props.history.push('/misc');
            }
          }
        ]
      },
      {
        label: 'Template Pages', icon: 'get_app',
        items: [
          {
            label: 'Empty Page', icon: 'hourglass_empty', command: () => {
              window.location.hash = '/empty';
            }
          },
          {label: 'Landing Page', icon: 'flight_land', url: 'assets/pages/landing.html', target: '_blank'},
          {label: 'Login Page', icon: 'verified_user', url: 'assets/pages/login.html', target: '_blank'},
          {label: 'Error Page', icon: 'error', url: 'assets/pages/error.html', target: '_blank'},
          {label: '404 Page', icon: 'error_outline', url: 'assets/pages/404.html', target: '_blank'},
          {label: 'Access Denied Page', icon: 'security', url: 'assets/pages/access.html', target: '_blank'}
        ]
      },
      {
        label: 'Menu Hierarchy', icon: 'menu',
        items: [
          {
            label: 'Submenu 1', icon: 'subject',
            items: [
              {
                label: 'Submenu 1.1', icon: 'subject',
                items: [
                  {label: 'Submenu 1.1.1', icon: 'subject'},
                  {label: 'Submenu 1.1.2', icon: 'subject'},
                  {label: 'Submenu 1.1.3', icon: 'subject'}
                ]
              },
              {
                label: 'Submenu 1.2', icon: 'subject',
                items: [
                  {label: 'Submenu 1.2.1', icon: 'subject'},
                  {label: 'Submenu 1.2.2', icon: 'subject'}
                ]
              }
            ]
          },
          {
            label: 'Submenu 2', icon: 'subject',
            items: [
              {
                label: 'Submenu 2.1', icon: 'subject',
                items: [
                  {label: 'Submenu 2.1.1', icon: 'subject'},
                  {label: 'Submenu 2.1.2', icon: 'subject'},
                  {label: 'Submenu 2.1.3', icon: 'subject'}
                ]
              },
              {
                label: 'Submenu 2.2', icon: 'subject',
                items: [
                  {label: 'Submenu 2.2.1', icon: 'subject'},
                  {label: 'Submenu 2.2.2', icon: 'subject'}
                ]
              }
            ]
          }
        ]
      },
      {
        label: 'Utils', icon: 'build', command: () => {
          window.location.hash = '/utils';
        }
      },
      {
        label: 'Documentation', icon: 'find_in_page', command: () => {
          window.location.hash = '/documentation';
        }
      }
    ];
  }

  public render() {
    let layoutClassName = classNames('layout-wrapper', {'layout-compact': this.props.layout.layoutCompact});
    let layoutContainerClassName = classNames('layout-container', {
      'menu-layout-static': this.props.layout.layoutMode !== 'overlay',
      'menu-layout-overlay': this.props.layout.layoutMode === 'overlay',
      'layout-menu-overlay-active': this.props.layout.overlayMenuActive,
      'menu-layout-slim': this.props.layout.layoutMode === 'slim',
      'menu-layout-horizontal': this.props.layout.layoutMode === 'horizontal',
      'layout-menu-static-inactive': this.props.layout.staticMenuDesktopInactive,
      'layout-menu-static-active': this.props.layout.staticMenuMobileActive
    });
    let menuClassName = classNames('layout-menu', {'layout-menu-dark': this.props.layout.darkMenu});

    let profileMenu = null;
    if (this.props.layout.profileMode === 'inline' && this.props.layout.layoutMode !== 'horizontal') {
      profileMenu = (<InlineProfile/>);
    }
    return (
        <div className={layoutClassName} onClick={this.onDocumentClick}>
          <div ref={(el) => this.layoutContainer = el} className={layoutContainerClassName}>
            <Header layout={this.props.layout} actions={this.props.actions}/>

            <div className={menuClassName} onClick={this.onMenuClick}>
              <div ref={(el) => this.layoutMenuScroller = el} className="nano">
                <div
                    className="nano-content menu-scroll-content"
                >
                  {profileMenu}
                  <Menubar
                      model={this.menu}
                      onMenuItemClick={this.onMenuItemClick}
                      onRootMenuItemClick={this.onRootMenuItemClick}
                      layoutMode={this.props.layout.layoutMode}
                      active={this.props.layout.menuActive}
                  />
                </div>
              </div>
            </div>

            <div className="layout-main">
              <Switch>
                <Route
                    exact={true}
                    path="/about"
                    component={About}
                />
                <Route
                    exact={true}
                    path="/home"
                    component={Home}
                />
                <Route
                    exact={true}
                    path="/demo"
                    component={DemoContainer}
                />
              </Switch>
              <Footer/>
            </div>
            <RightPanel expanded={this.props.layout.rightPanelActive} onContentClick={this.onRightPanelClick}/>

            <div className="layout-mask"/>
          </div>
        </div>
    );
  }
}