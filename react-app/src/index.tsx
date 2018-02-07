import * as React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { Route, Router, Switch } from 'react-router-dom';
import { App } from './containers/app';

import { history, store } from './redux/store';
import registerServiceWorker from './registerServiceWorker';
import './rxjs-imports';

import 'fullcalendar/dist/fullcalendar.css';
import 'primereact/resources/primereact.css';
import './assets/sass/indigo/theme.css';
import './assets/sass/indigo/layout.css';
import './assets/css/lib/ripple.css';
render((
        <Provider store={store} key="provider">
          <Router history={history}>
            <Switch>
              <Route path="/" component={App}/>
            </Switch>
          </Router>
        </Provider>),
       document.getElementById('root') as HTMLElement
);
registerServiceWorker();
