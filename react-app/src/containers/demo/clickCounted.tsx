import * as React from 'react';
import { Home } from '../home';
import { InjectedProps, Options, ExternalProps, State } from './ExternalProps';
import { history as asd } from './../../redux/store';

export const clickCounted = ({debug = false, historya = asd }: Options = {}) =>
    <TOriginalProps extends {}>(Component: (
        React.ComponentClass<TOriginalProps & InjectedProps>
        | React.StatelessComponent<TOriginalProps & InjectedProps>)
    ) => {
      type ResultProps = TOriginalProps & ExternalProps;
      const result = class ClickCounted extends React.Component<ResultProps, State> {
        static displayName = `ClickCounted(${Component.displayName || Component.name})`;

        constructor(props: ResultProps) {
          super(props);
          this.state = {
            clickCount: 0,
          };
        }

        handleClick = (e: React.MouseEvent<HTMLDivElement>) => {
          if (debug) {
            // console.log('Clicked');
          }
          this.setState(state => ({ clickCount: state.clickCount + 1 }));
        }

        render(): JSX.Element {
          let component;
          if ( this.state.clickCount <= 5 ) {
            component = <Component {...this.props} {...this.state} />;
          } else {
           component = <Home/>;
          }
          return (
              <div onClick={this.handleClick}>
                <span>Clicked {this.state.clickCount} times</span>
                {component}
              </div>
          );
        }
      };

      return result;
};