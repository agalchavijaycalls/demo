import * as React from 'react';
import * as classNames from 'classnames';

import * as jQuery from 'jquery';
import 'nanoscroller';

export namespace RightPanel {
  export interface Props {
    onContentClick: Function;
    expanded: boolean;
  }
  
  export interface State {
  }
}

export class RightPanel extends React.Component<RightPanel.Props, RightPanel.State> {
  public static defaultProps: Partial<RightPanel.Props> = {
    expanded: false
  };

  rightPanelMenuScroller: any | null;

  constructor(args: RightPanel.Props) {
    super(args);
    this.onContentClick = this.onContentClick.bind(this);
  }
  
  componentDidMount() {
    jQuery(this.rightPanelMenuScroller).nanoScroller({flash: true});
  }
  
  componentWillUnmount() {
    jQuery(this.rightPanelMenuScroller).nanoScroller({destroy: true});
  }
  
  onContentClick(event: any) {
    this.props.onContentClick(event);
  }
  
  public render(): JSX.Element {
    let className = classNames('layout-rightpanel', {'layout-rightpanel-active': this.props.expanded});
    
    return (
        <div className={className} onClick={(e) => this.onContentClick(e)}>
          <div ref={(el) => this.rightPanelMenuScroller = el} className="nano">
            <div className="nano-content right-panel-scroll-content">
              <div className="layout-rightpanel-header">
                <div className="weather-day">Wednesday</div>
                <div className="weather-date">Jan 26</div>
              </div>
              
              <div className="layout-rightpanel-content">
                <h1>Weather</h1>
                <h2>San Francisco, USA</h2>
                
                <div className="weather-today">
                  <span className="weather-today-value">21&#8451;</span>
                  <img src="assets/images/weather-icon-2.svg" width="60" alt="weather2"/>
                </div>
                
                <ul className="weekly-weather">
                  <li>
                    Thursday
                    <img src="assets/images/weather-icon-1.svg" alt="weather1"/>
                    <span className="weekly-weather-value">24</span>
                  </li>
                  <li>
                    Friday
                    <img src="assets/images/weather-icon-3.svg" alt="weather3"/>
                    <span className="weekly-weather-value">19</span>
                  </li>
                  <li>
                    Saturday
                    <img src="assets/images/weather-icon-4.svg" alt="weather4"/>
                    <span className="weekly-weather-value">15</span>
                  </li>
                  <li>
                    Sunday
                    <img src="assets/images/weather-icon-1.svg" alt="weather1"/>
                    <span className="weekly-weather-value">24</span>
                  </li>
                  <li>
                    Monday
                    <img src="assets/images/weather-icon-2.svg" alt="weather2"/>
                    <span className="weekly-weather-value">21</span>
                  </li>
                  <li>
                    Tuesday
                    <img src="assets/images/weather-icon-3.svg" alt="weather3"/>
                    <span className="weekly-weather-value">20</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        
        </div>
    );
  }
}
