import * as React from 'react';

export namespace Footer {
  export interface Props {
  }

  export interface State {
  }
}

export class Footer extends React.Component<Footer.Props, Footer.State> {
  constructor(args: Footer.Props) {
    super(args);
  }

  public render() {

    return (
        <div className="footer">
          <div className="card clearfix">
            <span className="footer-text-left">PrimeReact ULTIMA for React</span>

            <span className="footer-text-right">
              <span className="ui-icon ui-icon-copyright">Footer</span>
              <span>All Rights Reserved</span>
            </span>
          </div>
        </div>
    );
  }
}
