import * as React from 'react';
import {Route, Switch} from 'react-router';
import {Services} from './services';
import {Link} from 'react-router-dom';
import {CounterContainer} from "./counter";

export const Home = () => {
    return (
        <section>
            <br/>
            <h3>
                <Link to={'/counter'}>Counter</Link>
                <Link to={'/services'}>Services</Link>
            </h3>
            <Switch>
                <Route exact path='/counter' component={CounterContainer}/>
                <Route exact path='/services' component={Services}/>
            </Switch>
        </section>
    );
};