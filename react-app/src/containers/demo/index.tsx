import * as React from 'react';
import { RouteComponentProps } from 'react-router-dom';
import { Demo } from '../demo/DemoComponent';

export namespace DemoContainer {
  export interface Props extends RouteComponentProps<void> {
  }

  export interface State {
  }
}

export class DemoContainer extends React.Component<DemoContainer.Props, DemoContainer.State> {
  public render() {

    return (
        <div>
          <h1>DemoContainer</h1>
          <Demo text="TEst" style="no" />
        </div>
    );
  }
}
