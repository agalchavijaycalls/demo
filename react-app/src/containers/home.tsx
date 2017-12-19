import * as React from 'react';
import {Counter} from './../components';
import {Route, Switch} from 'react-router';
import {About} from './about';
import {Services} from './services';
import {Link} from 'react-router-dom';

export const Home = () => {
    return (
        <section>
            <Counter label={'Counter'}/>
            <br/>
            <h3>
                <Link to={'/about'}>About</Link>
                <Link to={'/services'}>Services</Link>
            </h3>
            <Switch>
                <Route exact path='/about' component={About}/>
                <Route exact path='/services' component={Services}/>
            </Switch>
        </section>
    );
};