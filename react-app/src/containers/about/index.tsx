import * as React from 'react';
import { RouteComponentProps } from 'react-router-dom';

export namespace About {
  export interface Props extends RouteComponentProps<void> {
  }

  export interface State {
  }
}

export class About extends React.Component<About.Props, About.State> {
  public render() {

    return (
        <div>
          <h1>About</h1>
        </div>
    );
  }
}
