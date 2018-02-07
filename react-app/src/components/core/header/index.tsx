import * as classNames from 'classnames';
import * as React from 'react';
import * as LayoutActions from './../../../redux/layout/actions';
import { State as LayoutState } from './../../../redux/layout/state';

export namespace Header {
  export interface Props {
    layout: LayoutState;
    actions: typeof LayoutActions;
  }

  export interface State {
  }
}

export class Header extends React.Component<Header.Props, Header.State> {
  constructor(args: Header.Props) {
    super(args);
    this.onMenuButtonClick = this.onMenuButtonClick.bind(this);
    this.onRightPanelButtonClick = this.onRightPanelButtonClick.bind(this);
    this.onTopbarMenuButtonClick = this.onTopbarMenuButtonClick.bind(this);
    this.onTopbarItemClick = this.onTopbarItemClick.bind(this);
  }

  onTopbarItemClick(event: any, item: string) {
    this.props.actions.onTopbarItemClick(item);
    event.preventDefault();
  }

  onMenuButtonClick(event: any) {
    this.props.actions.onMenuButtonClick(this.isDesktop());
    event.preventDefault();
  }

  onRightPanelButtonClick(event: any) {
    this.props.actions.onRightPanelButtonClick(true);
    event.preventDefault();
  }

  onTopbarMenuButtonClick(event: any) {
    this.props.actions.onTopbarMenuButtonClick(true);
    event.preventDefault();
  }

  isDesktop() {
    return window.innerWidth > 1024;
  }

  isHorizontal() {
    return this.props.layout.layoutMode === 'horizontal';
  }

  public render() {

    let topbarItemsClassName = classNames('topbar-items animated fadeInDown',
                                          {'topbar-items-visible': this.props.layout.topbarMenuActive});
    let profileMenu = null;
    if (this.props.layout.profileMode === 'top' || this.isHorizontal()) {
      profileMenu = (
          <li
              className={classNames('profile-item',
                                    {'active-top-menu': this.props.layout.activeTopbarItem === 'profile'})}
          >
            <a
                onClick={(e) => this.onTopbarItemClick(e, 'profile')}
            >
              <img
                className="profile-image"
                src="assets/images/avatar.png"
                alt="Profile"
              />
              <span
                className="topbar-item-name"
              >
                Jane Williams
              </span>
            </a>

            <ul
                className="ultima-menu animated fadeInDown"
            >
              <li
                  role="menuitem"
              >
                <a>
                  <i
                      className="material-icons"
                  >
                    person
                  </i>
                  <span>
                    Profile
                  </span>
                </a>
              </li>
              <li
                  role="menuitem"
              >
                <a>
                  <i
                    className="material-icons"
                  >
                    security
                  </i>
                  <span>
                    Privacy
                  </span>
                </a>
              </li>
              <li
                  role="menuitem"
              >
                <a>
                  <i
                      className="material-icons"
                  >
                    settings_applications
                  </i>
                  <span>
                    Settings
                  </span>
                </a>
              </li>
              <li
                  role="menuitem"
              >
                <a>
                  <i
                      className="material-icons"
                  >
                    power_settings_new
                  </i>
                  <span>
                    Logout
                  </span>
                </a>
              </li>
            </ul>
          </li>
      );
    }

    return (
        <div
            className="topbar clearfix"
        >
          <div
              className="topbar-left"
          >
            <div
                className="logo"
            >
              Logo
            </div>
          </div>
          <div
              className="topbar-right"
          >
            <a
                id="menu-button"
                onClick={this.onMenuButtonClick}
            >
              <i>
                a
              </i>
            </a>
            <a
              id="rightpanel-menu-button"
              onClick={this.onRightPanelButtonClick}
            >
              <i className="material-icons">more_vert</i>
            </a>

            <a
                id="topbar-menu-button"
                onClick={this.onTopbarMenuButtonClick}
            >
              <i
                className="material-icons"
              >
                menu
              </i>
            </a>

            <ul
                className={topbarItemsClassName}
            >
              {profileMenu}
              <li
                  className={classNames({'active-top-menu': this.props.layout.activeTopbarItem === 'settings'})}
              >
                <a
                    onClick={(e) => this.onTopbarItemClick(e, 'settings')}
                >
                  <i
                    className="topbar-icon material-icons"
                  >
                    settings
                  </i>
                  <span
                    className="topbar-item-name"
                  >
                    Settings
                  </span>
                </a>
                <ul
                    className="ultima-menu animated fadeInDown"
                >
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        palette
                      </i>
                      <span>
                        Change Theme
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        favorite_border
                      </i>
                      <span>
                        Favorites
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        lock
                      </i>
                      <span>
                        Lock Screen
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        wallpaper
                      </i>
                      <span>
                        Wallpaper
                      </span>
                    </a>
                  </li>
                </ul>
              </li>
              <li
                  className={classNames({'active-top-menu': this.props.layout.activeTopbarItem === 'messages'})}
              >
                <a
                    onClick={(e) => this.onTopbarItemClick(e, 'messages')}
                >
                  <i
                    className="topbar-icon material-icons animated swing"
                  >
                    message
                  </i>
                  <span
                    className="topbar-badge animated rubberBand"
                  >
                    5
                  </span>
                  <span
                    className="topbar-item-name"
                  >
                    Messages
                  </span>
                </a>
                <ul
                    className="ultima-menu animated fadeInDown"
                >
                  <li
                      role="menuitem"
                  >
                    <a
                        className="topbar-message"
                    >
                      <img
                          src="assets/images/avatar1.png"
                          width="35"
                          alt="avatar1"
                      />
                      <span>
                        Give me a call
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a
                        className="topbar-message"
                    >
                      <img
                          src="assets/images/avatar2.png"
                          width="35"
                          alt="avatar2"
                      />
                      <span>
                        Sales reports attached
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a
                        className="topbar-message"
                    >
                      <img
                          src="assets/images/avatar3.png"
                          width="35"
                          alt="avatar3"
                      />
                      <span>
                        About your invoice
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a
                        className="topbar-message"
                    >
                      <img
                          src="assets/images/avatar2.png"
                          width="35"
                          alt="avatar2"
                      />
                      <span>
                        Meeting today at 10pm
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a
                        className="topbar-message"
                    >
                      <img
                          src="assets/images/avatar4.png"
                          width="35"
                          alt="avatar4"
                      />
                      <span>
                        Out of office
                      </span>
                    </a>
                  </li>
                </ul>
              </li>
              <li
                  className={classNames({'active-top-menu': this.props.layout.activeTopbarItem === 'notifications'})}
              >
                <a
                    onClick={(e) => this.onTopbarItemClick(e, 'notifications')}
                >
                  <i
                    className="topbar-icon material-icons"
                  >
                    timer
                  </i>
                  <span
                    className="topbar-badge animated rubberBand"
                  >
                    4
                  </span>
                  <span
                    className="topbar-item-name"
                  >
                    Notifications
                  </span>
                </a>
                <ul
                    className="ultima-menu animated fadeInDown"
                >
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        bug_report
                      </i>
                      <span>
                        Pending tasks
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        event
                      </i>
                      <span>
                        Meeting today at 3pm
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        file_download
                      </i>
                      <span>
                        Download documents
                      </span>
                    </a>
                  </li>
                  <li
                      role="menuitem"
                  >
                    <a>
                      <i
                          className="material-icons"
                      >
                        flight
                      </i>
                      <span>
                        Book flight
                      </span>
                    </a>
                  </li>
                </ul>
              </li>
              <li
                  className={classNames('search-item',
                                        {'active-top-menu': this.props.layout.activeTopbarItem === 'search'})}
                  onClick={(e) => this.onTopbarItemClick(e, 'search')}
              >
                <span
                    className="md-inputfield"
                >
                  <input
                      type="text"
                  />
                  <label>
                    Search
                  </label>
                  <i
                      className="topbar-icon material-icons"
                  >
                    search
                  </i>
                </span>
              </li>
            </ul>
          </div>
        </div>
    );
  }
}
