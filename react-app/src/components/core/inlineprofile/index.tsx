import * as React from 'react';
import * as classNames from 'classnames';

export namespace InlineProfile {
  export interface Props {
  }
  
  export interface State {
    expanded: boolean;
  }
}

export class InlineProfile extends React.Component<InlineProfile.Props, InlineProfile.State> {
  constructor(args: InlineProfile.Props) {
    super(args);
    this.state = {
      expanded: false
    };
    this.onClick = this.onClick.bind(this);
  }
  
  onClick(event: any) {
    this.setState({expanded: !this.state.expanded});
    event.preventDefault();
  }
  
  public render() {
    
    return (
        <div>
          <div className={classNames('profile', {'profile-expanded': this.state.expanded})}>
            <a onClick={this.onClick}>
              <img className="profile-image" src="assets/images/avatar.png" alt="Profile"/>
              <span className="profile-name">Jane Williams</span>
              <i className="material-icons">keyboard_arrow_down</i>
            </a>
          </div>
          <ul className="ultima-menu profile-menu">
            <li role="menuitem">
              <a className="ripplelink" tabIndex={this.state.expanded ? 0 : -1}>
                <i className="material-icons">person</i>
                <span>Profile</span>
              </a>
            </li>
            <li role="menuitem">
              <a className="ripplelink" tabIndex={this.state.expanded ? 0 : -1}>
                <i className="material-icons">security</i>
                <span>Privacy</span>
              </a>
            </li>
            <li role="menuitem">
              <a className="ripplelink" tabIndex={this.state.expanded ? 0 : -1}>
                <i className="material-icons">settings_application</i>
                <span>Settings</span>
              </a>
            </li>
            <li role="menuitem">
              <a className="ripplelink" tabIndex={this.state.expanded ? 0 : -1}>
                <i className="material-icons">power_settings_new</i>
                <span>Logout</span>
              </a>
            </li>
          </ul>
        </div>
    );
  }
}
