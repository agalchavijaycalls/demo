import * as React from 'react';
import {render} from 'react-dom';
import {Provider} from 'react-redux';
import registerServiceWorker from './registerServiceWorker';

import './rxjs-imports';


import store from './store';
import {history} from './store';

import {
    Home,
} from './containers';
import {Router} from 'react-router-dom';

const Root = (
    <Provider store={store}>
        <Router history={history}>
            <Home/>
        </Router>
    </Provider>
);


render(Root, document.getElementById('root') as HTMLElement);

registerServiceWorker();

