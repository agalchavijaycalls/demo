import * as classNames from 'classnames';
import * as React from 'react';

export namespace SubMenu {
  export interface Props {
    className?: string;
    items: Array<any>;
    onMenuItemClick: Function;
    onRootItemClick?: Function;
    root?: boolean;
    layoutMode: string;
    menuActive: boolean;
  }

  export interface State {
    activeIndex: number;
  }
}

export class SubMenu extends React.Component<SubMenu.Props, SubMenu.State> {
  public static defaultProps: Partial<SubMenu.Props> = {
    root: false,
    menuActive: false
  };

  constructor(props: SubMenu.Props) {
    super(props);
    this.state = {activeIndex: -1};
  }

  onMenuItemClick(event: any, item: any, index: number) {
    if (item.disabled) {
      event.preventDefault();
      return;
    }

    if (this.props.root && this.props.onRootItemClick) {
      this.props.onRootItemClick({
        originalEvent: event,
        item: item
      });
    }

    if (item.command) {
      item.command({originalEvent: event, item: item});
    }

    if (item.items || !item.url) {
      event.preventDefault();
    }

    if (index === this.state.activeIndex) {
      this.setState({activeIndex: -1});
    } else {
      this.setState({activeIndex: index});
    }

    if (this.props.onMenuItemClick) {
      this.props.onMenuItemClick({
        originalEvent: event,
        item: item
      });
    }
  }

  onMenuItemMouseEnter(index: number) {
    if (this.props.root && this.props.menuActive && this.isHorizontalOrSlim()) {
      this.setState({activeIndex: index});
    }
  }

  componentWillReceiveProps(nextProps: SubMenu.Props, nextState: SubMenu.Props) {
    if (this.isHorizontalOrSlim() && this.props.menuActive && !nextProps.menuActive) {
      this.setState({activeIndex: -1});
    }
  }

  isHorizontalOrSlim() {
    return (this.props.layoutMode === 'horizontal' || this.props.layoutMode === 'slim');
  }

  public render(): JSX.Element {
    let itemsT = this.props.items.map((item, i) => {
      let active = this.state.activeIndex === i;
      let styleClass = classNames(item.badgeStyleClass, {'active-menuitem': active});
      let badge = null;
      if (item.badge) {
        badge = <span className="menuitem-badge">{item.badge}</span>;
      }
      let submenuIcon = null;
      if (item.items) {
        submenuIcon = <i className="material-icons submenu-icon">keyboard_arrow_down</i>;
      }

      let tooltip = null;
      if (this.props.root) {
        tooltip = (
            <div className="layout-menu-tooltip">
              <div className="layout-menu-tooltip-arrow"/>
              <div className="layout-menu-tooltip-text">{item.label}</div>
            </div>
        );
      }
      let subMenu = null;
      if (item.items) {
        subMenu = (
            <SubMenu
              items={item.items}
              onMenuItemClick={this.props.onMenuItemClick}
              layoutMode={this.props.layoutMode}
              menuActive={this.props.menuActive}
            />
        );
      }
      return (
          <li className={styleClass} key={i}>
            <a
                className="ripplelink"
                href={item.url || '#'}
                onClick={(e) => this.onMenuItemClick(e, item, i)}
                target={item.target}
                onMouseEnter={(e) => this.onMenuItemMouseEnter(i)}
            >
              <i className="material-icons">
                {item.icon}
              </i>
              <span>{item.label}</span> {badge} {submenuIcon}
            </a> {tooltip} {subMenu}
          </li>
      );
    });

    return (
        <ul className={this.props.className}>{itemsT}</ul>
    );
  }
}
