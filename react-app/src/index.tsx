import * as React from 'react';
import {render} from 'react-dom';
import {Provider} from 'react-redux';
import {Router} from 'react-router-dom';
import registerServiceWorker from './registerServiceWorker';

import './rxjs-imports';
import {history, store} from './store';
import {Home} from './containers';

const Root = (
    <Provider store={store}>
        <Router history={history}>
            <Home/>
        </Router>
    </Provider>
);

render(Root, document.getElementById('root') as HTMLElement);

registerServiceWorker();