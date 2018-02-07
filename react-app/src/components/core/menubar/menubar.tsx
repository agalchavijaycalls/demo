import * as React from 'react';
import { SubMenu } from './submenu';

export namespace Menubar {
  export interface Props {
    model: Array<any>;
    layoutMode: string;
    onMenuItemClick: Function;
    onRootMenuItemClick: Function;
    active: boolean;
  }

  export interface State {
  }
}

export class Menubar extends React.Component<Menubar.Props, Menubar.State> {
  public static defaultProps: Partial<Menubar.Props> = {};

  constructor(props: Menubar.Props) {
    super(props);
  }

  public render() {

    return (
        <SubMenu
            items={this.props.model}
            className="ultima-menu ultima-main-menu clearfix"
            menuActive={this.props.active}
            onRootItemClick={this.props.onRootMenuItemClick}
            onMenuItemClick={this.props.onMenuItemClick}
            root={true}
            layoutMode={this.props.layoutMode}
        />
    );
  }
}
